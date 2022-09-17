package crypto.contracts.liquidityPair;

import crypto.blockChain.PolygonBlockchain;
import crypto.contracts.cryptoCurrency.UsdCoinPolygon;
import crypto.contracts.cryptoCurrency.WrappedEthereumPolygon;

/**
 * UsdcWethPairSushiSwapPolygon class is the Contract for USDC/WETH Pair on SushiSwap Polygon
 */
public class UsdcWethPairSushiSwapPolygon extends UniSwapV2Pair {
    public static final String PAIR_ADDRESS = "0x34965ba0ac2451A34a0471F04CCa3F990b8dea27";

    public UsdcWethPairSushiSwapPolygon() {
        super("UsdcWethPairSushiSwapPolygon", new PolygonBlockchain(), PAIR_ADDRESS,
                new UsdCoinPolygon(), new WrappedEthereumPolygon());
    }
    public UsdcWethPairSushiSwapPolygon(PolygonBlockchain polygonBlockchain) {
        super("UsdcWethPairSushiSwapPolygon", polygonBlockchain, PAIR_ADDRESS,
                new UsdCoinPolygon(), new WrappedEthereumPolygon());
    }
}
