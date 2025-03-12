package edu.grinnell.csc207.blockchain;

/**
 * A single block of a blockchain.
 */
public class Block {
    private int num;
    private String Amount;
    private Hash Hash;
    private Hash PrevHash;
    private long nonce;

    public Block(int Amount, int num, Hash PrevHash) {
        this.num = num;
        this.Amount = Amount;
        this.PrevHash = PrevHash;
        this.Hash = Hash.calculateHash("" + num + value + PrevHash.toString + "");
        mineBlock();
    }

    public Block(int Amount, int num, Hash PrevHash, long nonce) {
        this.Amount = Amount;
        this.num = num;
        this.PrevHash = PrevHash;
        this.nonce = nonce;
        this.Hash = Hash.calculateHash("" + num + Amount + PrevHash.toString + nonce + "");
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
        return this.Hash;
    }

    public Hash getPrevHash() {
        return this.PrevHash;
    }

    public String toString() {
        return "Block " + num + " (Amount: " + Amount + ", Nonce: " + nonce + ", prevHash: " + prevHash + ", hash: "
                + Hash + ")";
    }

    public void mineBlock(int amount) {
        num++;
        while (!Hash.isValid()) {
            nonce++;
            Hash hash = calculateHash("" + num + amount + PrevHash + nonce + "");
        }
        System.out.println("Amount: " + amount + "nonce:" + nonce);
    }
}
