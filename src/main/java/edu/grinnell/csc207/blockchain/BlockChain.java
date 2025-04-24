package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

/**
 * A linked list of hash-consistent blocks representing a ledger of
 * monetary transactions.
 */
public class BlockChain {

    /**
     * A node in the linked list.
     */
    private class Node {
        Block block;
        Node next;

        Node(Block block) {
            this.block = block;
            this.next = null;
        }
    }

    private Node first;
    private Node last;
    private int size;
    private int balance;
    private int initial;

    /**
     * Constructor for the BlockChain class.
     *
     * @param initial The initial amount of the blockchain.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not found.
     */
    public BlockChain(int initial) throws NoSuchAlgorithmException {
        Block firstBlock = new Block(initial, 0, null);
        this.first = new Node(firstBlock);
        this.last = first;
        this.size = 1;
        this.balance = initial;
        this.initial = initial;
    }

    /**
     * Mines a new block with the given amount.
     *
     * @param amount The amount to be mined.
     * @return The newly mined block.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not found.
     */
    public Block mine(int amount) throws NoSuchAlgorithmException {
        Block lastBlock = last.block;
        Block newBlock = new Block(amount, lastBlock.getNum() + 1, lastBlock.getHash());
        newBlock.mineBlock(amount);
        return newBlock;
    }

    /**
     * Returns the size of the blockchain.
     * 
     * @return The size of the blockchain.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Appends a new block to the end of the blockchain.
     * 
     * @param b The block to be appended.
     */
    public void append(Block b) {
        if (!(b.getPrevHash().equals(last.block.getHash()))) {
            throw new IllegalArgumentException("Invalid block");
        }
        Node newNode = new Node(b);
        last.next = newNode;
        last = newNode;
        size++;
        this.balance += b.getAmount();
    }

    /**
     * Returns the amount of the transaction stored in this block.
     * 
     * @return the transaction amount contained in this block
     */
    public boolean removeLast() {
        if (size == 1) {
            return false;
        }
        Node current = first;
        while (current.next != last) {
            current = current.next;
        }
        this.balance -= last.block.getAmount();
        current.next = null;
        last = current;
        size--;
        return true;
    }

    /**
     * Returns the hash of the last block in the blockchain.
     * 
     * @return The hash of the last block.
     */
    public Hash getHash() {
        return last.block.getHash();
    }

    /**
     * Checks if the blockchain is valid.
     * 
     * @return true if the blockchain is valid, false otherwise.
     */
    public boolean isValidBlockChain() throws NoSuchAlgorithmException {
        Node current = first;
        while (current != null) {
            Block block = current.block;
            String blockData = "" + block.getNum() + block.getAmount() + block.getPrevHash() + block.getNonce();
            Hash expectedHash = new Hash(Hash.calculateHash(blockData));
            if (!block.getHash().toString().equals(expectedHash.toString())) {
                return false;
            }
            if (!block.getHash().isValid()) {
                return false;
            }
            if (current != first && !block.getPrevHash().toString().equals(current.block.getPrevHash().toString())) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    /**
     * Prints the balances of Alice and Bob.
     */
    public void printbalances() {
        System.out.println("balances: " + this.balance);
    }

    /**
     * Returns the balance of the blockchain.
     * 
     * @return The balance of the blockchain.
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Returns the initial amount of the blockchain.
     * 
     * @return The initial amount of the blockchain.
     */
    public int getInitial() {
        return this.initial;
    }

    /**
     * Returns a string representation of the blockchain.
     * 
     * @return A string representation of the blockchain.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = first;
        while (current != null) {
            sb.append(current.block.getHash()).append("\n");
            current = current.next;
        }
        return sb.toString();
    }
}
