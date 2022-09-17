import crypto.blockChain.PolygonBlockchain;
import crypto.entity.decetralizedExchange.QuickSwap;
import crypto.web3Gateway.PolygonGateway.PocketNetworkPolygonHttpGateway;
import quickUtils.QuickUtils;

public class Main {
    public static void main(String[] args) {
        /*
            Example for printing price of WETH on QuickSwap on the polygon blockchain using a decentralized rpc endpoint.
         */

        //Creates a polygon Web3GateWay from PocketNetwork's decentralized rpc endpoint.
        PocketNetworkPolygonHttpGateway pocketNetworkPolygonHttpGateway = PocketNetworkPolygonHttpGateway.getInstance();

        //Creates a PolygonBlockchain and passes the Web3GateWay to the PolygonBlockchain.
        PolygonBlockchain polygonBlockchain = new PolygonBlockchain(pocketNetworkPolygonHttpGateway);

        //Creates a QuickSwap object and passes the PolygonBlockchain to the QuickSwap object.
        QuickSwap quickSwap = new QuickSwap(polygonBlockchain);

        //Addresses of assets contracts (case-sensitive)
        String polygonUsdcAddress = "0x2791bca1f2de4661ed88a30c99a7a9449aa84174";
        String polygonWethAddress = "0x7ceb23fd6bc0add59e62ac25578270cff1b9f619";

        //Gets the price on QuickSwap
        String price = quickSwap.getPriceOfCryptoCurrencyFromLiquidityPair(polygonUsdcAddress, polygonWethAddress, 1);

        //Prints price
        QuickUtils.println(price);

        //Shutdown Web3J instance
        polygonBlockchain.shutDown();
    }
}