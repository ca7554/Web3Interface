package crypto.web3Gateway.PolygonGateway;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

//Anonymous Gateway
public class PocketNetworkPolygonHttpGateway extends PolygonHttpGateway {
    private static  PocketNetworkPolygonHttpGateway instance;
    private static final String LINK_POLYGON_POCKET_NETWORK_URL = "https://poly-rpc.gateway.pokt.network/";

    private PocketNetworkPolygonHttpGateway(Web3j web3j) {
        super(web3j, LINK_POLYGON_POCKET_NETWORK_URL);
    }

    public static PocketNetworkPolygonHttpGateway  getInstance(){
        if(instance == null){
            instance = new PocketNetworkPolygonHttpGateway(Web3j.build(new HttpService(LINK_POLYGON_POCKET_NETWORK_URL)));
        }
        return instance;
    }
}
