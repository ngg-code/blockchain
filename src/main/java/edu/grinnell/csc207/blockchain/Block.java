package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

/**
 * A single block of a blockchain.
 */
public class Block {
    private int num;
    private int amount;
    private Hash hash;
    private Hash prevHash;
    private long nonce;

    /**
     * Constructor for the Block class.
     *
     * @param amount   The amount of the transaction.
     * @param num      The block number.
     * @param prevHash The hash of the previous block.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not found.
     */
    public Block(int amount, int num, Hash prevHash) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.hash = new Hash(Hash.calculateHash("" + num + amount + prevHash + ""));
    }

    /**
     * Constructor for the Block class with nonce.
     *
     * @param amount   The amount of the transaction.
     * @param num      The block number.
     * @param prevHash The hash of the previous block.
     * @param nonce    The nonce value for mining.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not found.
     */
    public Block(int amount, int num, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        this.amount = amount;
        this.num = num;
        this.prevHash = prevHash;
        this.nonce = nonce;
        this.hash = new Hash(Hash.calculateHash("" + num + amount + prevHash + nonce + ""));
    }

    /**
     * Returns the amount of the transaction stored in this block.
     * 
     * @return the transaction amount contained in this block
     */
    public int getAmount() {
        return this.amount;
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
        return this.prevHash;
    }

    /**
     * Returns a string representation of the block.
     * 
     * @return a string representation of the block
     */
    public String toString() {
        return "Block " + num
                + "(amount: " + amount
                + ", Nonce: " + nonce
                + ", prevHash: " + prevHash
                + ", hash: " + hash + ")";
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
            hash.hash = Hash.calculateHash("" + num + amount + prevHash + nonce + "");
        }
        System.out.println("amount: " + amount + "nonce:" + nonce);
    }
}
