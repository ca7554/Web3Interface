package crypto.contracts.cryptoCurrency;

import crypto.blockChain.PolygonBlockchain;
import crypto.tools.CryptoTags;

public final class WrappedEthereumPolygon extends CryptoCurrency {
    public static final String MAIN_NAME = CryptoTags.WRAPPED_ETHEREUM_CRYPTO_SYMBOL;
    public static final String MAIN_SYMBOL = CryptoTags.ETHEREUM_CRYPTO_SYMBOL;
    public static final String ADDRESS = "0x7ceb23fd6bc0add59e62ac25578270cff1b9f619";
    public static final int DECIMALS = 18;

    public WrappedEthereumPolygon(){
        super(MAIN_NAME, new PolygonBlockchain(), ADDRESS, MAIN_SYMBOL, DECIMALS);
    }

    public WrappedEthereumPolygon(PolygonBlockchain polygonBlockchain){
        super(MAIN_NAME, polygonBlockchain, ADDRESS, MAIN_SYMBOL, DECIMALS);
    }
}
