package crypto.contracts.liquidityPair;

import crypto.blockChain.Blockchain;
import crypto.contracts.Contract;
import crypto.contracts.cryptoCurrency.CryptoCurrency;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LiquidityPairContract extends Contract implements LiquidityPair {
    private CryptoCurrency cryptoCurrency0;
    private CryptoCurrency cryptoCurrency1;

    public LiquidityPairContract(String name, Blockchain blockChain, String address, CryptoCurrency cryptoCurrency0,
                                 CryptoCurrency cryptoCurrency1) {
        super(name, blockChain, address);
        this.cryptoCurrency0 = cryptoCurrency0;
        this.cryptoCurrency1 = cryptoCurrency1;
    }

    public static LiquidityPairContract getLiquidityPairContractFromString(Blockchain blockchain, String objectString){
        Pattern pattern = Pattern.compile("name='([^',]+)'" +
                ".*address='(0x[0-9a-fA-F]+)'" +
                ".*cryptoCurrency0=(CryptoCurrency\\{[^}]+})" +
                ".*cryptoCurrency1=(CryptoCurrency\\{[^}]+})");
        Matcher matcher = pattern.matcher(objectString);
        matcher.find();

        String name = matcher.group(1);
        String address = matcher.group(2);
        String crypto0 = matcher.group(3);
        String crypto1 = matcher.group(4);

        CryptoCurrency cryptoCurrency0 = CryptoCurrency.getCryptoCurrencyFromString(blockchain, crypto0);
        CryptoCurrency cryptoCurrency1 = CryptoCurrency.getCryptoCurrencyFromString(blockchain, crypto1);

        LiquidityPairContract liquidityPairContract = null;

        if(name.contains("UniswapV2Pair")){
            liquidityPairContract = new UniSwapV2Pair(name, blockchain, address, cryptoCurrency0, cryptoCurrency1);
        }else if(name.contains("UniswapV3Pair")){
            liquidityPairContract = new UniSwapV3Pair(name, blockchain, address, cryptoCurrency0, cryptoCurrency1);
        }

        return liquidityPairContract;
    }

    @Override
    public String getAsset(int index){
        if(index != 0 && index != 1)
            return "Error Index Must Be 0 or 1";
        return index == 0 ? cryptoCurrency0.getName() : cryptoCurrency1.getName();
    }

    public abstract String[] getReserves();
    public abstract String getCryptoCurrencyAddress(int tokenIndex);

    public CryptoCurrency getCryptoCurrency0() {
        return cryptoCurrency0;
    }
    public void setCryptoCurrency0(CryptoCurrency cryptoCurrency0) {
        this.cryptoCurrency0 = cryptoCurrency0;
    }
    public CryptoCurrency getCryptoCurrency1() {
        return cryptoCurrency1;
    }
    public void setCryptoCurrency1(CryptoCurrency cryptoCurrency1) {
        this.cryptoCurrency1 = cryptoCurrency1;
    }
    @Override
    public String toString() {
        return "LiquidityPairContract{" +
                "name='" + getName() + '\'' +
                ", blockChain='" + getBlockChain().getBlockchainName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", cryptoCurrency0=" + cryptoCurrency0 +
                ", cryptoCurrency1=" + cryptoCurrency1 +
                '}';
    }
}
