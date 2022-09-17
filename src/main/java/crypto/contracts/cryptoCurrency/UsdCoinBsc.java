package crypto.contracts.cryptoCurrency;

import crypto.blockChain.BinanceSmartChain;
import crypto.tools.CryptoTags;

public final class UsdCoinBsc extends CryptoCurrency{
    public static final String MAIN_NAME = CryptoTags.USD_COIN_CRYPTO_NAME;
    public static final String MAIN_SYMBOL = CryptoTags.USD_COIN_CRYPTO_SYMBOL;
    public static final String ADDRESS = "0x8ac76a51cc950d9822d68b83fe1ad97b32cd580d";
    public static final int DECIMALS = 18;

    public UsdCoinBsc(){
        super(MAIN_NAME, new BinanceSmartChain(), ADDRESS, MAIN_SYMBOL, DECIMALS);
    }

    public UsdCoinBsc(BinanceSmartChain binanceSmartChain){
        super(MAIN_NAME, binanceSmartChain, ADDRESS, MAIN_SYMBOL, DECIMALS);
    }
}
