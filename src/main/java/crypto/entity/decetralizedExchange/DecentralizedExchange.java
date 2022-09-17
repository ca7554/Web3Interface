package crypto.entity.decetralizedExchange;

import crypto.blockChain.Blockchain;
import crypto.contracts.liquidityPair.LiquidityPairContract;
import crypto.entity.Exchange;

public abstract class DecentralizedExchange implements Exchange {
    private Blockchain blockchain;

    public DecentralizedExchange(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public abstract String getLiquidityPairAddress(String tokenAddress0, String tokenAddress1);
    public abstract LiquidityPairContract getLiquidityPairContract(String tokenAddress0, String tokenAddress1);

    public Blockchain getBlockchain() {
        return blockchain;
    }
    public void setBlockchain(Blockchain blockchain) {
        this.blockchain = blockchain;
    }
}
