package crypto.web3Gateway.PolygonGateway;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import quickUtils.QuickUtils;

/**
 * PolygonMumbaiTestNetHttpGateway class connects to the Polygon Mumbai TestNet blockchain using a commonly used RPC endpoint
 */
public class PolygonMumbaiTestNetHttpGateway extends PolygonHttpGateway {
    private static  PolygonMumbaiTestNetHttpGateway instance;
    private static final String LINK_POLYGON_MUMBAI_TESTNET = "https://rpc-mumbai.maticvigil.com/";

    private PolygonMumbaiTestNetHttpGateway(Web3j web3j) {
        super(web3j, LINK_POLYGON_MUMBAI_TESTNET);
    }

    public static PolygonMumbaiTestNetHttpGateway  getInstance(){
        if(isShowAnonymousWarning())
            QuickUtils.println("WARNING NOT ANONYMOUS HTTP INSTANCE CREATED OR USED");
        if(instance == null){
            instance = new PolygonMumbaiTestNetHttpGateway(Web3j.build(new HttpService(LINK_POLYGON_MUMBAI_TESTNET)));
        }
        return instance;
    }
}
