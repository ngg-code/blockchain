package edu.grinnell.csc207.blockchain;

/**
 * A linked list of hash-consistent blocks representing a ledger of
 * monetary transactions.
 */
public class BlockChain {

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
    private int Balance;
    private int initial;

    public BlockChain(int initial) {
        Block firstBlock = new Block(initial, 0, null);
        this.first = new Node(firstBlock);
        this.last = first;
        this.size = 1;
        this.Balance = initial;
        this.initial = initial;
    }

    public Block mine(int amount) {
        Block lastBlock = last.block;
        lastBlock.mineBlock(amount);
        return lastBlock;
    }

    /**
     * @return the number of elements in the list
     */
    public int getSize() {
        return this.size;
    }

    public void append(Block b) {
        if (!b.getPrevHash().equals(last.block.getHash())) {
            throw new IllegalArgumentException("Invalid block");
        }
        Node newNode = new Node(b);
        last.next = newNode;
        last = newNode;
        size++;
        this.Balance += b.getAmount();
    }

    public boolean removeLast() {
        if (size == 1) {
            return false;
        }
        Node current = first;
        while (current.next != last) {
            current = current.next;
        }
        current.next = null;
        last = current;
        size--;
        this.Balance -= last.block.getAmount();
        return true;
    }

    public Hash getHash() {
        return last.block.getHash();
    }

    public boolean isValidBlockChain() {
        Node current = first;
        while (current.next != null) {
            if (!current.next.block.getPrevHash().equals(current.block.getHash())) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public void printBalances() {
        System.out.println("Balances: " + this.Balance);
    }

    public int getBalance(){
        return this.Balance;
    }

    public int getInitial(){
        return this.initial;
    }

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
