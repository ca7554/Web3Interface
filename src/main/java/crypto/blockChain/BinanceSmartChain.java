package crypto.blockChain;

import crypto.contracts.Contract;
import crypto.web3Gateway.BinanceSmartChainGateWay.BinanceSmartChainGateWay;
import crypto.web3Gateway.BinanceSmartChainGateWay.BinanceSmartChainRpcHttpGateWay;

import java.util.HashMap;

public class BinanceSmartChain extends Blockchain{
    public static final String BLOCK_CHAIN_NAME = "Binance Smart Chain";
    public static final HashMap<String, Contract> CONTRACTS = new HashMap<>();

    public BinanceSmartChain(){
        super(BLOCK_CHAIN_NAME, BinanceSmartChainRpcHttpGateWay.getInstance(), CONTRACTS);
    }

    public BinanceSmartChain(BinanceSmartChainGateWay binanceSmartChainGateWay){
        super(BLOCK_CHAIN_NAME, binanceSmartChainGateWay, CONTRACTS);
    }
}
