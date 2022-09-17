package crypto.contracts.cryptoCurrency;

import crypto.blockChain.PolygonBlockchain;
import crypto.tools.CryptoTags;

public final class UsdCoinPolygon extends CryptoCurrency {
    public static final String MAIN_NAME = CryptoTags.USD_COIN_CRYPTO_NAME;
    public static final String MAIN_SYMBOL = CryptoTags.USD_COIN_CRYPTO_SYMBOL;
    public static final String ADDRESS = "0x2791bca1f2de4661ed88a30c99a7a9449aa84174";
    public static final int DECIMALS = 6;

    public UsdCoinPolygon(){
        super(MAIN_NAME, new PolygonBlockchain(), ADDRESS, MAIN_SYMBOL, DECIMALS);
    }

    public UsdCoinPolygon(PolygonBlockchain polygonBlockchain){
        super(MAIN_NAME, polygonBlockchain, ADDRESS, MAIN_SYMBOL, DECIMALS);
    }
}
