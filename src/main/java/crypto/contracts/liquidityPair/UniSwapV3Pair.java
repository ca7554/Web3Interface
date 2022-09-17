package crypto.contracts.liquidityPair;

import crypto.blockChain.Blockchain;
import crypto.contracts.cryptoCurrency.CryptoCurrency;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.*;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthCall;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * UniSwapV3Pair class interacts with liquidity pairs and implements contract methods that uses the UniSwapV3Pair contract
 */
public class UniSwapV3Pair extends LiquidityPairContract {
    public UniSwapV3Pair(String name, Blockchain blockChain, String address, CryptoCurrency cryptoCurrency0,
                         CryptoCurrency cryptoCurrency1) {
        super(name, blockChain, address, cryptoCurrency0, cryptoCurrency1);
    }

    @Override
    public String getPriceOfAsset(int index) {
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";
        return calculatePriceFromSqrtPriceX96(getSqrtPriceX96(), index, getCryptoCurrency0().getDecimals(), getCryptoCurrency1().getDecimals());
    }

    @Override
    public String sellAsset(String index, String seller) {
        return null;
    }

    @Override
    public String[] getReserves() {
        String reserve0 = getBlockChain().getBalanceOf(getCryptoCurrency0().getAddress(), getAddress());
        String reserve1 = getBlockChain().getBalanceOf(getCryptoCurrency1().getAddress(), getAddress());

        return new String[] {reserve0, reserve1};
    }

    @Override
    public String getCryptoCurrencyAddress(int index) {
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";

        Function function = new Function(index == 0 ? "token0" : "token1",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Address>(){}));

        EthCall response = getBlockChain().call(null, getAddress(), function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        return someTypes.toString().substring(1, someTypes.toString().length() - 1);
    }

    public String getSqrtPriceX96(){
        Function function = new Function("slot0",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint160>(){}, new TypeReference<Int24>(){}, new TypeReference<Uint16>(){},
                        new TypeReference<Uint16>(){}, new TypeReference<Uint16>(){}, new TypeReference<Uint8>(){},
                        new TypeReference<Bool>(){}));

        EthCall response = getBlockChain().call(null, getAddress(), function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());
        return ((Uint160)someTypes.get(0)).getValue().toString();
    }

    public static String getPriceSqrtX96(Blockchain blockchain, String pairAddress) {
        Function function = new Function("slot0",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint160>(){}, new TypeReference<Int24>(){}, new TypeReference<Uint16>(){},
                        new TypeReference<Uint16>(){}, new TypeReference<Uint16>(){}, new TypeReference<Uint8>(){},
                        new TypeReference<Bool>(){}));

        EthCall response = blockchain.call(null, pairAddress, function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());
        return ((Uint160)someTypes.get(0)).getValue().toString();
    }

    public static String getTokenAddressOfPair(Blockchain blockchain, String pairAddress, int index) {
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";

        Function function = new Function(index == 0 ? "token0" : "token1",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Address>(){}));

        EthCall response = blockchain.call(null, pairAddress, function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        return someTypes.toString().substring(1, someTypes.toString().length() - 1);
    }

    public static String calculatePriceFromSqrtPriceX96(String sqrtPriceX96, int index, int decimals0, int decimals1){
        BigDecimal sqrtPriceX96Big = new BigDecimal(sqrtPriceX96);
        BigDecimal price = null;
        int power = 0;

        if(index == 0) {
            price = sqrtPriceX96Big.pow(2).setScale(100, RoundingMode.HALF_UP)
                    .divide(new BigDecimal("2").pow(192), RoundingMode.HALF_UP);
            power =decimals0 - decimals1;
        }else if(index == 1) {
            price = new BigDecimal("2").setScale(100, RoundingMode.HALF_UP)
                    .pow(192).divide(sqrtPriceX96Big.pow(2), RoundingMode.HALF_UP);
            power = decimals1 - decimals0;
        }

        if(power > 0){
            price = price.multiply(BigDecimal.TEN.pow(power));
        }else if(power < 0){
            price = price.divide(BigDecimal.TEN.pow(Math.abs(power)), RoundingMode.HALF_UP);
        }

        if(index == 0)
            price = price.setScale(decimals1, RoundingMode.HALF_UP);
        else if(index == 1)
            price = price.setScale(decimals0, RoundingMode.HALF_UP);

        return price.toPlainString();
    }

    public static UniSwapV3Pair getUniSwapV3Pair(Blockchain blockchain, String pairAddress){
        String tokenAddress0 = getTokenAddressOfPair(blockchain, pairAddress, 0);
        String tokenAddress1 = getTokenAddressOfPair(blockchain, pairAddress, 1);
        int tokenDecimals0 = CryptoCurrency.getDecimalsFromAddress(blockchain, tokenAddress0);
        int tokenDecimals1 = CryptoCurrency.getDecimalsFromAddress(blockchain, tokenAddress1);

        CryptoCurrency cryptoCurrency0 = new CryptoCurrency(tokenAddress0, blockchain, tokenDecimals0);
        CryptoCurrency cryptoCurrency1 = new CryptoCurrency(tokenAddress1, blockchain, tokenDecimals1);

        return new UniSwapV3Pair("UniswapV3Pair " + pairAddress, blockchain, pairAddress, cryptoCurrency0, cryptoCurrency1);
    }
}
