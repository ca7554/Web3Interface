package crypto.web3Gateway;

import org.web3j.abi.datatypes.Function;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthCall;

/**
 * Web3Gateway interface connects to any blockchain when implemented.
 */
public interface Web3Gateway {
    String sendMainTokenToAddress(String fromAddress, String toAddress, String gas, String maxFeePerGas,
                                                  String maxPriorityFeePerGas, String nonce,
                                                  String valueToSend, String credentials);
    EthCall call(String from, String to, Function function, DefaultBlockParameterName defaultBlockParameterName);
    String getBalanceOf(String tokenAddress, String addressInquiry);
    String getGasPrice();
    void shutDown();
}
