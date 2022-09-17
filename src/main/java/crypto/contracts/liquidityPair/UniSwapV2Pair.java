package crypto.contracts.liquidityPair;

import crypto.blockChain.Blockchain;
import crypto.contracts.cryptoCurrency.CryptoCurrency;
import crypto.tools.CryptoConverter;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint112;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthCall;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class UniSwapV2Pair extends LiquidityPairContract {
    public UniSwapV2Pair(String name, Blockchain blockChain, String address, CryptoCurrency cryptoCurrency0,
                         CryptoCurrency cryptoCurrency1) {
        super(name, blockChain, address, cryptoCurrency0, cryptoCurrency1);
    }

    @Override
    public String getPriceOfAsset(int index) {
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";

        String[] reservesResult = getReserves();

        int token0Decimals = getCryptoCurrency0().getDecimals();
        int token1Decimals = getCryptoCurrency1().getDecimals();

        BigDecimal tokenReserves0 = new BigDecimal(CryptoConverter.integerToDecimal(reservesResult[0], token0Decimals));
        BigDecimal tokenReserves1 = new BigDecimal(CryptoConverter.integerToDecimal(reservesResult[1], token1Decimals));

        return CryptoConverter.reservesToTokenPrice(index, tokenReserves0, tokenReserves1);
    }

    @Override
    public String sellAsset(String index, String seller) {
        return null;
    }

    @Override
    public String[] getReserves() {
        Function function = new Function("getReserves",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint112>(){}, new TypeReference<Uint112>(){},
                        new TypeReference<Uint32>(){}));

        EthCall response = getBlockChain().call(null, getAddress(), function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        String[] reserves = {((Uint112)someTypes.get(0)).getValue().toString(), ((Uint112)someTypes.get(1)).getValue().toString()};

        return reserves;
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

    public static String[] getReserves(Blockchain blockchain, String pairAddress) {
        Function function = new Function("getReserves",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint112>(){}, new TypeReference<Uint112>(){},
                        new TypeReference<Uint32>(){}));

        EthCall response = blockchain.call(null, pairAddress, function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        String[] reserves = {((Uint112)someTypes.get(0)).getValue().toString(), ((Uint112)someTypes.get(1)).getValue().toString()};

        return reserves;
    }

    public static String getCryptoCurrencyAddress(Blockchain blockchain, String pairAddress, int index) {
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

    public static UniSwapV2Pair getUniSwapV2Pair(Blockchain blockchain, String pairAddress){
        String tokenAddress0 = getCryptoCurrencyAddress(blockchain, pairAddress, 0);
        String tokenAddress1 = getCryptoCurrencyAddress(blockchain, pairAddress, 1);
        int tokenDecimals0 = CryptoCurrency.getDecimalsFromAddress(blockchain, tokenAddress0);
        int tokenDecimals1 = CryptoCurrency.getDecimalsFromAddress(blockchain, tokenAddress1);

        CryptoCurrency cryptoCurrency0 = new CryptoCurrency(pairAddress, blockchain, tokenDecimals0);
        CryptoCurrency cryptoCurrency1 = new CryptoCurrency(pairAddress, blockchain, tokenDecimals1);

        return new UniSwapV2Pair("UniswapV2Pair " + pairAddress, blockchain, pairAddress, cryptoCurrency0, cryptoCurrency1);
    }
}
