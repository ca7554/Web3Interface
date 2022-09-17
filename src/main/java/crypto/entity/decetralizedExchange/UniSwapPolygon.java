package crypto.entity.decetralizedExchange;

import crypto.blockChain.PolygonBlockchain;
import crypto.contracts.factory.UniSwapV3Factory;

/**
 * UniSwapPolygon class gets prices and interacts with liquidity pairs from the UniSwap Polygon DEX
 */
public class UniSwapPolygon extends UniSwapV3Exchange {
    private static final String UNI_SWAP_FACTORY_CONTRACT_ADDRESS = "0x1F98431c8aD98523631AE4a59f267346ea31F984";

    public UniSwapPolygon() {
        super(new PolygonBlockchain(), new UniSwapV3Factory("UniswapV3Factory Uniswap Polygon",
                new PolygonBlockchain(), UNI_SWAP_FACTORY_CONTRACT_ADDRESS));
    }
    public UniSwapPolygon(PolygonBlockchain polygonBlockchain) {
        super(polygonBlockchain, new UniSwapV3Factory("UniswapV3Factory Uniswap Polygon",
                polygonBlockchain, UNI_SWAP_FACTORY_CONTRACT_ADDRESS));
    }
}
