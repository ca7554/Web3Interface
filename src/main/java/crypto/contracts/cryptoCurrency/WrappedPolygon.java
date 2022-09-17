package crypto.contracts.cryptoCurrency;

import crypto.blockChain.PolygonBlockchain;
import crypto.tools.CryptoTags;

/**
 * WrappedPolygon class is the CryptoCurrency Contract for Wrapped Polygon on the Polygon Blockchain
 */
public final class WrappedPolygon extends CryptoCurrency {
    public static final String MAIN_NAME = CryptoTags.POLYGON_CRYPTO_NAME;
    public static final String MAIN_SYMBOL = CryptoTags.POLYGON_CRYPTO_SYMBOL;
    public static final String ADDRESS = "0x0d500b1d8e8ef31e21c99d1db9a6444d3adf1270";
    public static final int DECIMALS = 18;

    public WrappedPolygon() {
        super(MAIN_NAME, new PolygonBlockchain(), ADDRESS, MAIN_SYMBOL, DECIMALS);
    }

    public WrappedPolygon(PolygonBlockchain polygonBlockchain) {
        super(MAIN_NAME, polygonBlockchain, ADDRESS, MAIN_SYMBOL, DECIMALS);
    }
}
