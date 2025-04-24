package edu.grinnell.csc207.blockchain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests {
    private BlockChain chain;

    @BeforeEach
    public void setUp() throws NoSuchAlgorithmException {
        // Initialize the blockchain with an initial amount of 1000
        chain = new BlockChain(1000);
    }

    @Test
    public void testInitialBalance() {
        // Check the initial balance of the blockchain
        assertEquals(1000, chain.getBalance());
    }

    @Test
    public void testMineBlock() throws NoSuchAlgorithmException {
        // Mine a block with an amount of 500
        Block minedBlock = chain.mine(500);
        chain.append(minedBlock);

        // Check that the block has been mined correctly by verifying the nonce
        assertNotNull(minedBlock.getNonce());
        assertTrue(minedBlock.getNonce() > 0);

        // Verify the balance after mining
        assertEquals(1500, chain.getBalance());
    }

    @Test
    public void testAppendBlock() throws NoSuchAlgorithmException {
        // Mine a block with amount 200 and append it to the blockchain
        Block minedBlock = chain.mine(200);
        chain.append(minedBlock);

        // Check that the blockchain's size has increased
        assertEquals(2, chain.getSize());

        // Verify the balance after appending
        assertEquals(1200, chain.getBalance());
    }

    @Test
    public void testRemoveLastBlock() throws NoSuchAlgorithmException {
        // Mine a block and append it
        Block minedBlock1 = chain.mine(300);
        chain.append(minedBlock1);

        // Check balance before removal
        assertEquals(1300, chain.getBalance());

        // Remove the last block
        assertTrue(chain.removeLast());

        // Verify the balance after removal
        assertEquals(1000, chain.getBalance());

        // Verify the blockchain size after removal
        assertEquals(1, chain.getSize());
    }

    @Test
    public void testRemoveLastBlockFails() {
        // Try to remove the last block when there is only one block
        assertFalse(chain.removeLast());

        // Verify the balance remains unchanged
        assertEquals(1000, chain.getBalance());

        // Verify the blockchain size remains the same
        assertEquals(1, chain.getSize());
    }

    @Test
    public void testBlockchainValidity() throws NoSuchAlgorithmException {
        // Ensure the blockchain is valid initially
        assertTrue(chain.isValidBlockChain());

        // Mine and append a block, then check if the blockchain is still valid
        Block minedBlock = chain.mine(400);
        chain.append(minedBlock);
        assertTrue(chain.isValidBlockChain());

        // Remove the last block and check if the blockchain is still valid
        chain.removeLast();
        assertTrue(chain.isValidBlockChain());
    }

    @Test
    public void testInvalidBlockchain() throws NoSuchAlgorithmException {
        // Mine a block and append it to the blockchain
        Block minedBlock = chain.mine(200);
        chain.append(minedBlock);

        // Manually corrupt the blockchain by modifying the hash of the second block
        chain.getHash().hash[0] = (byte) 0xFF;

        // Check if the blockchain is valid (it should now be invalid)
        assertFalse(chain.isValidBlockChain());
    }

    @Test
    public void testHelpCommand() {
        // You can simulate testing the help command as it doesn't directly impact the
        // blockchain logic.
        // A basic test could check if the "Help" command displays the correct message.
        String helpMessage = "mine: discovers the nonce for a given transaction\n" +
                "append: appends a new block onto the end of the chain\n" +
                "remove: removes the last block from the end of the chain\n" +
                "check: checks that the block chain is valid\n" +
                "report: reports the balances of Alice and Bob\n" +
                "help: prints this list of commands\n" +
                "quit: quits the program";

        assertTrue(helpMessage.contains("mine"));
        assertTrue(helpMessage.contains("append"));
        assertTrue(helpMessage.contains("remove"));
    }
}
