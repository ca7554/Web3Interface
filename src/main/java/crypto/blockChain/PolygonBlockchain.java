package crypto.blockChain;

import crypto.contracts.Contract;
import crypto.web3Gateway.PolygonGateway.PolygonGateway;
import crypto.web3Gateway.PolygonGateway.PolygonRpcHttpGateway;

import java.util.HashMap;

/**
 * PolygonBlockchain class stores the contracts and blockchain name and connects to the Polygon blockchain using a PolygonGateway
 */
public class PolygonBlockchain extends Blockchain {
    public static final String BLOCK_CHAIN_NAME = "Polygon";
    public static final HashMap<String, Contract> CONTRACTS = new HashMap<>();

    public PolygonBlockchain(){
        super(BLOCK_CHAIN_NAME, PolygonRpcHttpGateway.getInstance(), CONTRACTS);
    }

    public PolygonBlockchain(PolygonGateway polygonGateway){
        super(BLOCK_CHAIN_NAME, polygonGateway, CONTRACTS);
    }
}
