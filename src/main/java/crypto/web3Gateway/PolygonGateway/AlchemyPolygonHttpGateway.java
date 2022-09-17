package crypto.web3Gateway.PolygonGateway;


import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import quickUtils.QuickUtils;

public class AlchemyPolygonHttpGateway extends PolygonHttpGateway {
    private static  AlchemyPolygonHttpGateway instance;
    private static final String LINK_POLYGON_ALCHEMY_URL = "ADD CUSTOM API KEY";

    private AlchemyPolygonHttpGateway(Web3j web3j) {
        super(web3j, LINK_POLYGON_ALCHEMY_URL);
    }

    public static AlchemyPolygonHttpGateway  getInstance(){
        if(isShowAnonymousWarning())
            QuickUtils.println("WARNING NOT ANONYMOUS HTTP INSTANCE CREATED OR USED");
        if(instance == null){
            instance = new AlchemyPolygonHttpGateway(Web3j.build(new HttpService(LINK_POLYGON_ALCHEMY_URL)));
        }
        return instance;
    }
}
