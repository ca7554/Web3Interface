package crypto.web3Gateway.BinanceSmartChainGateWay;

import crypto.web3Gateway.EthHttpGateway;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

/**
 * PocketNetworkBscHttpGateWay class connects to the BSC blockchain using a decentralized RPC endpoint
 */
public class PocketNetworkBscHttpGateWay extends EthHttpGateway implements BinanceSmartChainGateWay{
    private static PocketNetworkBscHttpGateWay instance;
    private static final String LINK_BSC_POCKET_NETWORK_URL = "https://bsc-mainnet.gateway.pokt.network/v1/lb/6136201a7bad1500343e248d";

    private PocketNetworkBscHttpGateWay(Web3j web3j) {
        super(web3j, LINK_BSC_POCKET_NETWORK_URL);
    }

    public static PocketNetworkBscHttpGateWay getInstance(){
        if(instance == null){
            instance = new PocketNetworkBscHttpGateWay(Web3j.build(new HttpService(LINK_BSC_POCKET_NETWORK_URL)));
        }
        return instance;
    }
}
