package crypto.entity;

public interface Exchange {
    String getPriceFromPair(String search0, String search1, int getIndex);
    String sellFromPair(String search0, String search1, String seller, int sellIndex);
}
