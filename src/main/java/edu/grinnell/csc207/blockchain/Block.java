package edu.grinnell.csc207.blockchain;

/**
 * A single block of a blockchain.
 */
public class Block {
    private int num;
    private int Amount;
    private Hash hash;
    private Hash PrevHash;
    private long nonce;

    public Block(int Amount, int num, Hash PrevHash) {
        this.num = num;
        this.Amount = Amount;
        this.PrevHash = PrevHash;
        this.hash = Hash.calculateHash(num + "" + Amount + PrevHash);
    }

    public Block(int Amount, int num, Hash PrevHash, long nonce) {
        this.Amount = Amount;
        this.num = num;
        this.PrevHash = PrevHash;
        this.nonce = nonce;
        this.hash = Hash.calculateHash("" + num + Amount + PrevHash + nonce + "");
    }

    public int getAmount() {
        return this.Amount;
    }

    public int getNum() {
        return this.num;
    }

    public long getNonce() {
        return this.nonce;
    }

    public Hash getHash() {
        return this.hash;
    }

    public Hash getPrevHash() {
        return this.PrevHash;
    }

    public String toString() {
        return "Block " + num + " (Amount: " + Amount + ", Nonce: " + nonce + ", prevHash: " + PrevHash + ", hash: "
                + hash + ")";
    }
    

    public void mineBlock(int amount) {
        num++;
        while (!hash.isValid()) {
            nonce++;
            byte[] hash = Hash.calculateHash("" + num + amount + PrevHash + nonce + "");
        }
        System.out.println("Amount: " + amount + "nonce:" + nonce);
    }
}
