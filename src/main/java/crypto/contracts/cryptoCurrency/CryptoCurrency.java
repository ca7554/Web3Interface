package crypto.contracts.cryptoCurrency;

import crypto.blockChain.Blockchain;
import crypto.contracts.Contract;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthCall;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CryptoCurrency class stores the main symbol and decimals for a CryptoCurrency contract
 */
public class CryptoCurrency extends Contract {
    private String mainSymbol;
    private int decimals;

    public CryptoCurrency(String name, Blockchain blockChain, String address, String mainSymbol, int decimals) {
        super(name, blockChain, address);
        this.mainSymbol = mainSymbol;
        this.decimals = decimals;
    }

    public CryptoCurrency(String address, Blockchain blockChain, int decimals) {
        super(address, blockChain, address);
        this.mainSymbol = "?";
        this.decimals = decimals;
    }

    public int getDecimalsFromCurrency(){
        org.web3j.abi.datatypes.Function function = new Function("decimals",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint8>(){}));

        EthCall response = getBlockChain().call(null, getAddress(), function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        return Integer.parseInt(((Uint8)someTypes.get(0)).getValue().toString());
    }

    public static int getDecimalsFromAddress(Blockchain blockchain, String tokenAddress){
        org.web3j.abi.datatypes.Function function = new Function("decimals",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint8>(){}));

        EthCall response = blockchain.call(null, tokenAddress, function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        return Integer.parseInt(((Uint8)someTypes.get(0)).getValue().toString());
    }

    public static CryptoCurrency getCryptoCurrencyFromString(Blockchain blockchain, String objectString){
        Pattern pattern = Pattern.compile("name='([^',]+)'" +
                ".*address='(0x[0-9a-fA-F]+)'" +
                ".*mainSymbol='([^',]+)'" +
                ".*decimals=([0-9]+)");

        Matcher matcher = pattern.matcher(objectString);
        matcher.find();

        String name = matcher.group(1);
        String address = matcher.group(2);
        String mainSymbol = matcher.group(3);
        int decimals = Integer.parseInt(matcher.group(4));

        return new CryptoCurrency(name, blockchain, address, mainSymbol, decimals);
    }

    public String getMainSymbol() {
        return mainSymbol;
    }
    public void setMainSymbol(String mainSymbol) {
        this.mainSymbol = mainSymbol;
    }
    public int getDecimals() {
        return decimals;
    }
    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "name='" + getName() + '\'' +
                ", blockChain='" + getBlockChain().getBlockchainName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", mainSymbol='" + mainSymbol + '\'' +
                ", decimals=" + decimals +
                '}';
    }
}
