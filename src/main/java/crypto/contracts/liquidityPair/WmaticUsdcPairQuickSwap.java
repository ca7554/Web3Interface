package crypto.contracts.liquidityPair;

import crypto.blockChain.PolygonBlockchain;
import crypto.contracts.cryptoCurrency.UsdCoinPolygon;
import crypto.contracts.cryptoCurrency.WrappedPolygon;

public class WmaticUsdcPairQuickSwap extends UniSwapV2Pair {
    public static final String PAIR_ADDRESS = "0x6e7a5fafcec6bb1e78bae2a1f0b612012bf14827";

    public WmaticUsdcPairQuickSwap() {
        super("WmaticUsdcPairQuickSwap", new PolygonBlockchain(), PAIR_ADDRESS,
                new WrappedPolygon(), new UsdCoinPolygon());
    }
    public WmaticUsdcPairQuickSwap(PolygonBlockchain polygonBlockchain) {
        super("WmaticUsdcPairQuickSwap", polygonBlockchain, PAIR_ADDRESS,
                new WrappedPolygon(), new UsdCoinPolygon());
    }
}
