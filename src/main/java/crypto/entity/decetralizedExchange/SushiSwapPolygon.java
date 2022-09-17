package crypto.entity.decetralizedExchange;


import crypto.blockChain.PolygonBlockchain;
import crypto.contracts.factory.UniSwapV2Factory;

public class SushiSwapPolygon extends UniSwapV2Exchange {
    private static final String SUSHI_SWAP_FACTORY_CONTRACT_ADDRESS = "0xc35DADB65012eC5796536bD9864eD8773aBc74C4";

    public SushiSwapPolygon() {
        super(new PolygonBlockchain(), new UniSwapV2Factory("UniswapV2Factory SushiSwap Polygon",
                new PolygonBlockchain(), SUSHI_SWAP_FACTORY_CONTRACT_ADDRESS));
    }
    public SushiSwapPolygon(PolygonBlockchain polygonBlockchain) {
        super(polygonBlockchain, new UniSwapV2Factory("UniswapV2Factory SushiSwap Polygon",
                polygonBlockchain, SUSHI_SWAP_FACTORY_CONTRACT_ADDRESS));
    }
}
