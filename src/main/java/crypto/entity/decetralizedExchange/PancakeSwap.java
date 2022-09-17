package crypto.entity.decetralizedExchange;

import crypto.blockChain.BinanceSmartChain;
import crypto.contracts.factory.UniSwapV2Factory;

public class PancakeSwap extends UniSwapV2Exchange{
    private static final String PANCAKE_SWAP_FACTORY_CONTRACT_ADDRESS = "0xcA143Ce32Fe78f1f7019d7d551a6402fC5350c73";

    public PancakeSwap() {
        super(new BinanceSmartChain(), new UniSwapV2Factory("UniswapV2Factory PancakeSwap BSC",
                new BinanceSmartChain(), PANCAKE_SWAP_FACTORY_CONTRACT_ADDRESS));
    }
    public PancakeSwap(BinanceSmartChain binanceSmartChain) {
        super(binanceSmartChain, new UniSwapV2Factory("UniswapV2Factory PancakeSwap BSC",
                binanceSmartChain, PANCAKE_SWAP_FACTORY_CONTRACT_ADDRESS));
    }
}
