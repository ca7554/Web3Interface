package crypto.entity.decetralizedExchange;

import crypto.blockChain.PolygonBlockchain;
import crypto.contracts.factory.UniSwapV2Factory;

/**
 * QuickSwap class gets prices and interacts with liquidity pairs from the QuickSwap DEX
 */
public class QuickSwap extends UniSwapV2Exchange {
    private static final String QUICK_SWAP_FACTORY_CONTRACT_ADDRESS = "0x5757371414417b8C6CAad45bAeF941aBc7d3Ab32";

    public QuickSwap() {
        super(new PolygonBlockchain(), new UniSwapV2Factory("UniswapV2Factory QuickSwap Polygon",
                new PolygonBlockchain() , QUICK_SWAP_FACTORY_CONTRACT_ADDRESS));
    }
    public QuickSwap(PolygonBlockchain polygonBlockchain) {
        super(polygonBlockchain, new UniSwapV2Factory("UniswapV2Factory QuickSwap Polygon",
                polygonBlockchain, QUICK_SWAP_FACTORY_CONTRACT_ADDRESS));
    }
}
