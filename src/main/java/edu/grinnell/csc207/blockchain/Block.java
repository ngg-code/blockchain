package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

/**
 * A single block of a blockchain.
 */
public class Block {
    private int num;
    private int Amount;
    private Hash hash;
    private Hash PrevHash;
    private long nonce;

    /**
     * Constructor for the Block class.
     *
     * @param Amount   The amount of the transaction.
     * @param num      The block number.
     * @param PrevHash The hash of the previous block.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not found.
     */
    public Block(int Amount, int num, Hash PrevHash) throws NoSuchAlgorithmException {
        this.num = num;
        this.Amount = Amount;
        this.PrevHash = PrevHash;
        this.hash = new Hash(Hash.calculateHash("" + num + Amount + PrevHash + ""));
    }

    /**
     * Constructor for the Block class with nonce.
     *
     * @param Amount   The amount of the transaction.
     * @param num      The block number.
     * @param PrevHash The hash of the previous block.
     * @param nonce    The nonce value for mining.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not found.
     */
    public Block(int Amount, int num, Hash PrevHash, long nonce) throws NoSuchAlgorithmException {
        this.Amount = Amount;
        this.num = num;
        this.PrevHash = PrevHash;
        this.nonce = nonce;
        this.hash = new Hash(Hash.calculateHash("" + num + Amount + PrevHash + nonce + ""));
    }

    /**
     * Returns the amount of the transaction stored in this block.
     * 
     * @return the transaction amount contained in this block
     */
    public int getAmount() {
        return this.Amount;
    }

    /**
     * Returns the block number.
     * 
     * @return the block number
     */
    public int getNum() {
        return this.num;
    }

    /**
     * Returns the nonce value.
     * 
     * @return the nonce value
     */
    public long getNonce() {
        return this.nonce;
    }

    /**
     * Returns the hash of this block.
     * 
     * @return the hash of this block
     */
    public Hash getHash() {
        return this.hash;
    }

    /**
     * Returns the hash of the previous block.
     * 
     * @return the hash of the previous block
     */
    public Hash getPrevHash() {
        return this.PrevHash;
    }

    /**
     * Returns a string representation of the block.
     * 
     * @return a string representation of the block
     */
    public String toString() {
        return "Block " + num + " (Amount: " + Amount + ", Nonce: " + nonce + ", prevHash: " + PrevHash + ", hash: "
                + hash + ")";
    }

    /**
     * Mines the block by finding a valid nonce.
     * 
     * @param amount The amount to be mined.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not found.
     */
    public void mineBlock(int amount) throws NoSuchAlgorithmException {
        num++;
        while (!hash.isValid()) {
            nonce++;
            hash.hash = Hash.calculateHash("" + num + amount + PrevHash + nonce + "");
        }
        System.out.println("Amount: " + amount + "nonce:" + nonce);
    }
}
