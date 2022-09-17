package crypto.contracts.factory;

import crypto.blockChain.Blockchain;
import crypto.contracts.Contract;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthCall;

import java.util.Arrays;
import java.util.List;

/**
 * UniSwapV2Factory class implements contract methods for the UniSwapV2Factory contract used by some DEX's
 */
public class UniSwapV2Factory extends Contract {
    public UniSwapV2Factory(String contractName, Blockchain blockChain, String address) {
        super(contractName, blockChain, address);
    }

    public String getPairAddress(String tokenAddress0, String tokenAddress1) {
        Function function = new Function("getPair",
                Arrays.asList(new Address(tokenAddress0), new Address(tokenAddress1)),
                Arrays.asList(new TypeReference<Address>(){}));

        EthCall response = getBlockChain().call(null, getAddress(), function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        return someTypes.toString().substring(1, someTypes.toString().length() - 1);
    }
}
