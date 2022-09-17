package crypto.web3Gateway;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.utils.Numeric;
import quickUtils.QuickUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EthHttpGateway implements Web3Gateway{
    private String linkURL;
    private Web3j web3Gateway;
    private static boolean showAnonymousWarning = true;

    public EthHttpGateway(Web3j web3Gateway, String linkURL) {
        this.linkURL = linkURL;
        this.web3Gateway = web3Gateway;
    }

    @Override
    public String sendMainTokenToAddress(String fromAddress, String toAddress, String gas, String maxFeePerGas,
                                         String maxPriorityFeePerGas, String nonce, String valueToSend, String credentials){
        BigDecimal valueToSendInWei = new BigDecimal(valueToSend).divide(new BigDecimal("1.0E-18"));

        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(80001L, new BigInteger(nonce), new BigInteger(gas),
                toAddress, new BigInteger(valueToSendInWei.toPlainString()), new BigInteger(maxPriorityFeePerGas),
                new BigInteger(maxFeePerGas));

        byte[] hexValue = TransactionEncoder.signMessage(rawTransaction, 80001L , Credentials.create(credentials));

        Object obj = web3Gateway.ethSendRawTransaction(Numeric.toHexString(hexValue));

        return obj.toString();
    }

    @Override
    public EthCall call(String from, String to, Function function, DefaultBlockParameterName defaultBlockParameterName) {
        String encodedFunction = FunctionEncoder.encode(function);
        EthCall response = null;

        try {
            response = web3Gateway.ethCall(
                            Transaction.createEthCallTransaction(from, to, encodedFunction),
                            defaultBlockParameterName)
                    .sendAsync().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public String getBalanceOf(String tokenAddress, String addressInquiry) {
        Function function = new Function("balanceOf",
                Arrays.asList(new Address(addressInquiry)),
                Arrays.asList(new TypeReference<Uint256>(){}));

        EthCall response = call(null, tokenAddress, function, DefaultBlockParameterName.LATEST);

        List<Type> someTypes = FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters());

        return ((Uint256)someTypes.get(0)).getValue().toString();
    }

    @Override
    public String getGasPrice(){
        return null;
    }

    @Override
    public void shutDown(){
        QuickUtils.println("Shutting Down Blockchain Gateway");
        web3Gateway.shutdown();
    }

    public static boolean isShowAnonymousWarning() {
        return showAnonymousWarning;
    }

    public static void setShowAnonymousWarning(boolean showAnonymousWarning) {
        EthHttpGateway.showAnonymousWarning = showAnonymousWarning;
    }
}
