package crypto.web3Gateway.PolygonGateway;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import quickUtils.QuickUtils;

public class PolygonRpcHttpGateway extends PolygonHttpGateway {
    private static  PolygonRpcHttpGateway instance;
    private static final String LINK_POLYGON_RPC_GATEWAY = "https://polygon-rpc.com/";

    private PolygonRpcHttpGateway(Web3j web3j) {
        super(web3j, LINK_POLYGON_RPC_GATEWAY);
    }

    public static PolygonRpcHttpGateway  getInstance(){
        if(isShowAnonymousWarning())
            QuickUtils.println("WARNING NOT ANONYMOUS HTTP INSTANCE CREATED OR USED");
        if(instance == null){
            instance = new PolygonRpcHttpGateway(Web3j.build(new HttpService(LINK_POLYGON_RPC_GATEWAY)));
            return instance;
        }
        return instance;
    }
}
