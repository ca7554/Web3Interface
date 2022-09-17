package crypto.contracts.liquidityPair;

public interface LiquidityPair {
    String getAsset(int index);
    String getPriceOfAsset(int index);
    String sellAsset(String index, String seller);
}
