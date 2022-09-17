package crypto.web3Gateway.BinanceSmartChainGateWay;

import crypto.web3Gateway.EthHttpGateway;
import org.web3j.protocol.Web3j;

public class BinanceSmartChainHttpGateWay extends EthHttpGateway {
    public BinanceSmartChainHttpGateWay(Web3j binanceSmartChainGateway, String linkBinanceSmartChainURL){
        super(binanceSmartChainGateway, linkBinanceSmartChainURL);
    }
}
