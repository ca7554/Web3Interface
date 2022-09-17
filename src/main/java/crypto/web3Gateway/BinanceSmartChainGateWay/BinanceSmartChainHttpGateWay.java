package crypto.web3Gateway.BinanceSmartChainGateWay;

import crypto.web3Gateway.EthHttpGateway;
import org.web3j.protocol.Web3j;

/**
 * BinanceSmartChainHttpGateWay class connects to the BSC blockchain using an HTTP rpc endpoint
 */
public class BinanceSmartChainHttpGateWay extends EthHttpGateway {
    public BinanceSmartChainHttpGateWay(Web3j binanceSmartChainGateway, String linkBinanceSmartChainURL){
        super(binanceSmartChainGateway, linkBinanceSmartChainURL);
    }
}
