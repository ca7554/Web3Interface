package crypto.contracts;

import crypto.blockChain.Blockchain;

/**
 * Contract class stores the blockchain, name, and address of a blockchain contract
 */
public class Contract {
    private String name;
    private Blockchain blockChain;
    private String address;

    public Contract(String name, Blockchain blockChain, String address) {
        this.name = name;
        this.blockChain = blockChain;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Blockchain getBlockChain() {
        return blockChain;
    }
    public void setBlockChain(Blockchain blockChain) {
        this.blockChain = blockChain;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "name='" + name + '\'' +
                ", blockChain=" + blockChain.getBlockchainName() +
                ", address='" + address + '\'' +
                '}';
    }
}
