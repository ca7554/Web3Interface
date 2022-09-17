package crypto.contracts.liquidityPair;

/**
 * LiquidityPair interface interacts with LiquidityPair assets from any LiquidityPair when implemented
 */
public interface LiquidityPair {
    String getAsset(int index);
    String getPriceOfAsset(int index);
    String sellAsset(String index, String seller);
}
