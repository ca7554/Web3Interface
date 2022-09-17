package crypto.blockChain;

import crypto.contracts.Contract;
import crypto.web3Gateway.Web3Gateway;
import org.web3j.abi.datatypes.Function;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthCall;

import java.util.HashMap;

public class Blockchain {
    private Web3Gateway web3Gateway;
    private HashMap<String, Contract> contracts;
    private String blockchainName;

    public Blockchain(String blockchainName, Web3Gateway web3Gateway, HashMap<String, Contract> contracts){
        this.blockchainName = blockchainName;
        this.web3Gateway = web3Gateway;
        this.contracts = contracts;
    }

    public String sendMainTokenToAddress(String fromAddress, String toAddress, String gas, String maxFeePerGas,
                                         String maxPriorityFeePerGas, String nonce, String valueToSend, String credentials){
        return web3Gateway.sendMainTokenToAddress(fromAddress, toAddress, gas, maxFeePerGas, maxPriorityFeePerGas,
                nonce,valueToSend, credentials);
    }

    public EthCall call(String from, String to, Function function, DefaultBlockParameterName defaultBlockParameterName){
        return web3Gateway.call(from, to, function, defaultBlockParameterName);
    }

    public String getBalanceOf(String tokenAddress, String addressInquiry){
        return web3Gateway.getBalanceOf(tokenAddress, addressInquiry);
    }

    public Contract getContract(String search){
        return contracts.get(search);
    }

    public String getGasPrice(){
        return web3Gateway.getGasPrice();
    }

    public void shutDown(){
        web3Gateway.shutDown();
    }

    public Web3Gateway getWeb3Gateway() {
        return web3Gateway;
    }
    public void setWeb3Gateway(Web3Gateway web3Gateway) {
        this.web3Gateway = web3Gateway;
    }
    public HashMap<String, Contract> getContracts() {
        return contracts;
    }
    public void setContracts(HashMap<String, Contract> contracts) {
        this.contracts = contracts;
    }
    public String getBlockchainName() {
        return blockchainName;
    }
    public void setBlockchainName(String blockchainName) {
        this.blockchainName = blockchainName;
    }
}
