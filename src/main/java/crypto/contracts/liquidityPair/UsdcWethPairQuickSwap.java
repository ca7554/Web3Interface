package crypto.contracts.liquidityPair;

import crypto.blockChain.PolygonBlockchain;
import crypto.contracts.cryptoCurrency.UsdCoinPolygon;
import crypto.contracts.cryptoCurrency.WrappedEthereumPolygon;

public class UsdcWethPairQuickSwap extends UniSwapV2Pair {
    public static final String PAIR_ADDRESS = "0x853ee4b2a13f8a742d64c8f088be7ba2131f670d";

    public UsdcWethPairQuickSwap() {
        super("UsdcWethPairQuickSwap", new PolygonBlockchain(), PAIR_ADDRESS,
                new UsdCoinPolygon(), new WrappedEthereumPolygon());
    }
    public UsdcWethPairQuickSwap(PolygonBlockchain polygonBlockchain) {
        super("UsdcWethPairQuickSwap", polygonBlockchain, PAIR_ADDRESS,
                new UsdCoinPolygon(), new WrappedEthereumPolygon());
    }
}
