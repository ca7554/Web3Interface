package crypto.contracts.cryptoCurrency;

import crypto.blockChain.BinanceSmartChain;
import crypto.tools.CryptoTags;

/**
 * EthereumBsc class is the CryptoCurrency Contract for Ethereum on the Binance Smart Chain
 */
public final class EthereumBsc extends CryptoCurrency{
    public static final String MAIN_NAME = CryptoTags.WRAPPED_ETHEREUM_CRYPTO_SYMBOL;
    public static final String MAIN_SYMBOL = CryptoTags.ETHEREUM_CRYPTO_SYMBOL;
    public static final String ADDRESS = "0x2170ed0880ac9a755fd29b2688956bd959f933f8";
    public static final int DECIMALS = 18;

    public EthereumBsc(){
        super(MAIN_NAME, new BinanceSmartChain(), ADDRESS, MAIN_SYMBOL, DECIMALS);
    }

    public EthereumBsc(BinanceSmartChain binanceSmartChain){
        super(MAIN_NAME, binanceSmartChain, ADDRESS, MAIN_SYMBOL, DECIMALS);
    }
}
