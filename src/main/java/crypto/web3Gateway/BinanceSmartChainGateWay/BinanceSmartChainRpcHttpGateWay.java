package crypto.web3Gateway.BinanceSmartChainGateWay;

import crypto.web3Gateway.EthHttpGateway;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import quickUtils.QuickUtils;

public class BinanceSmartChainRpcHttpGateWay extends EthHttpGateway implements BinanceSmartChainGateWay {
    private static BinanceSmartChainRpcHttpGateWay instance;
    private static final String LINK_BINANCE_SMART_CHAIN_RPC_GATEWAY = "https://bsc-dataseed.binance.org/";

    private BinanceSmartChainRpcHttpGateWay(Web3j web3j) {
        super(web3j, LINK_BINANCE_SMART_CHAIN_RPC_GATEWAY);
    }

    public static BinanceSmartChainRpcHttpGateWay  getInstance(){
        if(isShowAnonymousWarning())
            QuickUtils.println("WARNING NOT ANONYMOUS HTTP INSTANCE CREATED OR USED");
        if(instance == null){
            instance = new BinanceSmartChainRpcHttpGateWay(Web3j.build(
                    new HttpService(LINK_BINANCE_SMART_CHAIN_RPC_GATEWAY)));
        }
        return instance;
    }
}
