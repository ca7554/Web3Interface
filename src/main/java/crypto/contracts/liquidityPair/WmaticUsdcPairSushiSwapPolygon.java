package crypto.contracts.liquidityPair;

import crypto.blockChain.PolygonBlockchain;
import crypto.contracts.cryptoCurrency.UsdCoinPolygon;
import crypto.contracts.cryptoCurrency.WrappedPolygon;

public class WmaticUsdcPairSushiSwapPolygon extends UniSwapV2Pair {
    public static final String PAIR_ADDRESS = "0xcd353f79d9fade311fc3119b841e1f456b54e858";

    public WmaticUsdcPairSushiSwapPolygon() {
        super("WmaticUsdcPairSushiSwapPolygon", new PolygonBlockchain(), PAIR_ADDRESS,
                new WrappedPolygon(), new UsdCoinPolygon());
    }
    public WmaticUsdcPairSushiSwapPolygon(PolygonBlockchain polygonBlockchain) {
        super("WmaticUsdcPairSushiSwapPolygon", polygonBlockchain, PAIR_ADDRESS,
                new WrappedPolygon(), new UsdCoinPolygon());
    }
}
