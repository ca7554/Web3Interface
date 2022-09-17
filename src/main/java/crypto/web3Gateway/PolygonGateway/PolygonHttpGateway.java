package crypto.web3Gateway.PolygonGateway;

import crypto.web3Gateway.EthHttpGateway;
import org.web3j.protocol.Web3j;

/**
 * PolygonHttpGateway class connects to the Polygon blockchain using an HTTP rpc endpoint
 */
public class PolygonHttpGateway extends EthHttpGateway implements PolygonGateway {
    public PolygonHttpGateway(Web3j polygonGateway, String linkPolygonURL){
        super(polygonGateway, linkPolygonURL);
    }
}
