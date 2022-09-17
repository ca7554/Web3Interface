package crypto.entity.decetralizedExchange;

import crypto.blockChain.Blockchain;
import crypto.contracts.cryptoCurrency.CryptoCurrency;
import crypto.contracts.factory.UniSwapV3Factory;
import crypto.contracts.liquidityPair.LiquidityPairContract;
import crypto.contracts.liquidityPair.UniSwapV3Pair;

import java.math.BigDecimal;

public class UniSwapV3Exchange extends DecentralizedExchange {
    private UniSwapV3Factory uniSwapV3Factory;
    private final long LOWEST_FEE = 3000L;

    public UniSwapV3Exchange(Blockchain blockchain, UniSwapV3Factory uniSwapV3Factory) {
        super(blockchain);
        this.uniSwapV3Factory = uniSwapV3Factory;
    }

    @Override
    public String getPriceFromPair(String search0, String search1, int index) {
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";

        CryptoCurrency cryptoCurrency0 = (CryptoCurrency) getBlockchain().getContract(search0);
        CryptoCurrency cryptoCurrency1 = (CryptoCurrency) getBlockchain().getContract(search1);

        return getPriceOfCryptoCurrencyFromLiquidityPair(cryptoCurrency0, cryptoCurrency1, LOWEST_FEE, index);
    }

    @Override
    public String sellFromPair(String search0, String search1, String seller, int tokenIndex) {
        return null;
    }

    @Override
    public String getLiquidityPairAddress(String tokenAddress0, String tokenAddress1){
        return uniSwapV3Factory.getPairAddress(tokenAddress0, tokenAddress1, LOWEST_FEE);
    }

    @Override
    public LiquidityPairContract getLiquidityPairContract(String tokenAddress0, String tokenAddress1) {
        String pairAddress100 = uniSwapV3Factory.getPairAddress(tokenAddress0, tokenAddress1, 100);
        String pairAddress500 = uniSwapV3Factory.getPairAddress(tokenAddress0, tokenAddress1, 500);
        String pairAddress3000 = uniSwapV3Factory.getPairAddress(tokenAddress0, tokenAddress1, 3000);

        UniSwapV3Pair uniSwapV3Pair100 = null;
        UniSwapV3Pair uniSwapV3Pair500 = null;
        UniSwapV3Pair uniSwapV3Pair3000 = null;

        BigDecimal pair100Total = new BigDecimal("-1");
        BigDecimal pair500Total = new BigDecimal("-1");
        BigDecimal pair3000Total = new BigDecimal("-1");

        if(!pairAddress100.equals("0x0000000000000000000000000000000000000000")) {
            uniSwapV3Pair100 = UniSwapV3Pair.getUniSwapV3Pair(getBlockchain(), pairAddress100);
            String[] reserves = uniSwapV3Pair100.getReserves();
            String price0 = uniSwapV3Pair100.getPriceOfAsset(0);
            String price1 = uniSwapV3Pair100.getPriceOfAsset(1);
            pair100Total = new BigDecimal(reserves[0]).multiply(new BigDecimal(price0)).add(new BigDecimal(reserves[1]).multiply(new BigDecimal(price1)));
        }
        if(!pairAddress500.equals("0x0000000000000000000000000000000000000000")) {
            uniSwapV3Pair500 = UniSwapV3Pair.getUniSwapV3Pair(getBlockchain(), pairAddress500);
            String[] reserves = uniSwapV3Pair500.getReserves();
            String price0 = uniSwapV3Pair500.getPriceOfAsset(0);
            String price1 = uniSwapV3Pair500.getPriceOfAsset(1);
            pair500Total = new BigDecimal(reserves[0]).multiply(new BigDecimal(price0)).add(new BigDecimal(reserves[1]).multiply(new BigDecimal(price1)));
        }
        if(!pairAddress3000.equals("0x0000000000000000000000000000000000000000")) {
            uniSwapV3Pair3000 = UniSwapV3Pair.getUniSwapV3Pair(getBlockchain(), pairAddress3000);
            String[] reserves = uniSwapV3Pair3000.getReserves();
            String price0 = uniSwapV3Pair3000.getPriceOfAsset(0);
            String price1 = uniSwapV3Pair3000.getPriceOfAsset(1);
            pair3000Total = new BigDecimal(reserves[0]).multiply(new BigDecimal(price0)).add(new BigDecimal(reserves[1]).multiply(new BigDecimal(price1)));
        }

        BigDecimal max = pair3000Total.max(pair500Total).max(pair100Total);
        if(max.compareTo(new BigDecimal("-1")) == 0)
            return null;
        if(max.compareTo(pair3000Total) == 0)
            return uniSwapV3Pair3000;
        if(max.compareTo(pair500Total) == 0)
            return uniSwapV3Pair500;
        return uniSwapV3Pair100;
    }

    public String getPriceOfCryptoCurrencyFromLiquidityPair(String cryptoCurrencyAddress0, String cryptoCurrencyAddress1,
                                                            long fee, int index) {
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";

        String pairAddress = getPairAddress(cryptoCurrencyAddress0, cryptoCurrencyAddress1, fee);
        String priceX96 = UniSwapV3Pair.getPriceSqrtX96(getBlockchain(), pairAddress);

        String token0Address = UniSwapV3Pair.getTokenAddressOfPair(getBlockchain(), pairAddress, 0);

        int cryptoDecimals0 = CryptoCurrency.getDecimalsFromAddress(getBlockchain(), cryptoCurrencyAddress0);
        int cryptoDecimal1 = CryptoCurrency.getDecimalsFromAddress(getBlockchain(), cryptoCurrencyAddress1);

        if(!token0Address.equals(cryptoCurrencyAddress0)){
            int temp = cryptoDecimals0;
            cryptoDecimals0 = cryptoDecimal1;
            cryptoDecimal1 = temp;
            index ^= 1;
        }

        return UniSwapV3Pair.calculatePriceFromSqrtPriceX96(priceX96, index, cryptoDecimals0, cryptoDecimal1);
    }

    public String getPriceOfCryptoCurrencyFromLiquidityPair(CryptoCurrency cryptoCurrency0, CryptoCurrency cryptoCurrency1,
                                                            long fee, int index) {
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";

        String pairAddress = getPairAddress(cryptoCurrency0.getAddress(), cryptoCurrency1.getAddress(), fee);
        String priceX96 = UniSwapV3Pair.getPriceSqrtX96(getBlockchain(), pairAddress);

        String token0Address = UniSwapV3Pair.getTokenAddressOfPair(getBlockchain(), pairAddress, 0);

        int cryptoDecimals0 = CryptoCurrency.getDecimalsFromAddress(getBlockchain(), cryptoCurrency0.getAddress());
        int cryptoDecimal1 = CryptoCurrency.getDecimalsFromAddress(getBlockchain(), cryptoCurrency1.getAddress());

        if(!token0Address.equals(cryptoCurrency0.getAddress())){
            int temp = cryptoDecimals0;
            cryptoDecimals0 = cryptoDecimal1;
            cryptoDecimal1 = temp;
            index ^= 1;
        }

        return UniSwapV3Pair.calculatePriceFromSqrtPriceX96(priceX96, index, cryptoDecimals0, cryptoDecimal1);
    }

    public String getPairAddress(String tokenAddress0, String tokenAddress1, long fee){
        return uniSwapV3Factory.getPairAddress(tokenAddress0, tokenAddress1, fee);
    }
}
