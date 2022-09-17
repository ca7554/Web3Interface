package crypto.entity.decetralizedExchange;

import crypto.blockChain.Blockchain;
import crypto.contracts.cryptoCurrency.CryptoCurrency;
import crypto.contracts.factory.UniSwapV2Factory;
import crypto.contracts.liquidityPair.LiquidityPairContract;
import crypto.contracts.liquidityPair.UniSwapV2Pair;
import crypto.tools.CryptoConverter;

import java.math.BigDecimal;

public class UniSwapV2Exchange extends DecentralizedExchange {
    private UniSwapV2Factory uniSwapV2Factory;

    public UniSwapV2Exchange(Blockchain blockchain, UniSwapV2Factory uniSwapV2Factory) {
        super(blockchain);
        this.uniSwapV2Factory = uniSwapV2Factory;
    }

    @Override
    public String getPriceFromPair(String search0, String search1, int tokenIndex) {
        CryptoCurrency cryptoCurrency0 = (CryptoCurrency) getBlockchain().getContract(search0);
        CryptoCurrency cryptoCurrency1 = (CryptoCurrency) getBlockchain().getContract(search1);

        return getPriceOfCryptoCurrencyFromLiquidityPair(cryptoCurrency0, cryptoCurrency1, tokenIndex);
    }

    @Override
    public String sellFromPair(String search0, String search1, String seller, int index) {
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";
        return null;
    }

    @Override
    public String getLiquidityPairAddress(String tokenAddress0, String tokenAddress1){
        return uniSwapV2Factory.getPairAddress(tokenAddress0, tokenAddress1);
    }

    @Override
    public LiquidityPairContract getLiquidityPairContract(String tokenAddress0, String tokenAddress1) {
        String pairAddress = getLiquidityPairAddress(tokenAddress0, tokenAddress1);

        if(pairAddress.equals("0x0000000000000000000000000000000000000000"))
            return null;

        return UniSwapV2Pair.getUniSwapV2Pair(getBlockchain(), pairAddress);
    }

    public String getPriceOfCryptoCurrencyFromLiquidityPair(String address0, String address1, int index){
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";

        String pairAddress = getLiquidityPairAddress(address0, address1);
        String[] reservesResult = UniSwapV2Pair.getReserves(getBlockchain(), pairAddress);
        String token0Address = UniSwapV2Pair.getCryptoCurrencyAddress(getBlockchain(), pairAddress, 0);

        int cryptoDecimals0 = CryptoCurrency.getDecimalsFromAddress(getBlockchain(), address0);
        int cryptoDecimal1 = CryptoCurrency.getDecimalsFromAddress(getBlockchain(), address1);
        int tokenReserves0Decimals;
        int tokenReserves1Decimals;

        if(token0Address.equals(address0)){
            tokenReserves0Decimals = cryptoDecimals0;
            tokenReserves1Decimals = cryptoDecimal1;
        }else{
            tokenReserves0Decimals = cryptoDecimal1;
            tokenReserves1Decimals = cryptoDecimals0;
            index ^= 1;
        }

        BigDecimal tokenReserves0 = new BigDecimal(CryptoConverter.integerToDecimal(reservesResult[0], tokenReserves0Decimals));
        BigDecimal tokenReserves1 = new BigDecimal(CryptoConverter.integerToDecimal(reservesResult[1], tokenReserves1Decimals));

        return CryptoConverter.reservesToTokenPrice(index, tokenReserves0, tokenReserves1);
    }

    public String getPriceOfCryptoCurrencyFromLiquidityPair(CryptoCurrency crypto0, CryptoCurrency crypto1, int index) {
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";

        String pairAddress = getLiquidityPairAddress(crypto0.getAddress(), crypto1.getAddress());
        String[] reservesResult = UniSwapV2Pair.getReserves(getBlockchain(), pairAddress);
        String token0Address = UniSwapV2Pair.getCryptoCurrencyAddress(getBlockchain(), pairAddress, 0);

        int tokenReserves0Decimals;
        int tokenReserves1Decimals;

        if(token0Address.equals(crypto0.getAddress())){
            tokenReserves0Decimals = crypto0.getDecimals();
            tokenReserves1Decimals = crypto1.getDecimals();
        }else{
            tokenReserves0Decimals = crypto1.getDecimals();
            tokenReserves1Decimals = crypto0.getDecimals();
            index ^= 1;
        }

        BigDecimal tokenReserves0 = new BigDecimal(CryptoConverter.integerToDecimal(reservesResult[0], tokenReserves0Decimals));
        BigDecimal tokenReserves1 = new BigDecimal(CryptoConverter.integerToDecimal(reservesResult[1], tokenReserves1Decimals));

        return CryptoConverter.reservesToTokenPrice(index, tokenReserves0, tokenReserves1);
    }

    public UniSwapV2Factory getUniSwapV2Factory() {
        return uniSwapV2Factory;
    }
    public void setUniSwapV2Factory(UniSwapV2Factory uniSwapV2Factory) {
        this.uniSwapV2Factory = uniSwapV2Factory;
    }
}
