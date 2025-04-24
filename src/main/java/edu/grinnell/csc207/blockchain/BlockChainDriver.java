package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * The main driver for the block chain program.
 */
public class BlockChainDriver {

    /**
     * The main entry point for the program.
     * 
     * @param args the command-line arguments
     * @throws NoSuchAlgorithmException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, NoSuchAlgorithmException {
        BlockChain chain = new BlockChain(Integer.parseInt(args[0]));
        System.out.println("Comand?");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("quit")) {
            if (command.equals("Help")) {
                System.out.println("mine: discovers the nonce for a given transaction\n" +
                        "append: appends a new block onto the end of the chain\n" +
                        "remove: removes the last block from the end of the chain\n" +
                        "check: checks that the block chain is valid\n" +
                        "report: reports the balances of Alice and Bob\n" +
                        "help: prints this list of commands\n" +
                        "quit: quits the program");
            }
            if (command.equals("mine")) {
                System.out.println("Amount transferred?");
                int amount = scanner.nextInt();
                scanner.nextLine();
                chain.mine(amount);
            }
            if (command.equals("append")) {
                System.out.println("Amount transferred?");
                int amount = scanner.nextInt();
                scanner.nextLine();
                chain.append(chain.mine(amount));
            }
            if (command.equals("remove")) {
                chain.removeLast();
            }
            if (command.equals("check")) {
                chain.isValidBlockChain();
            }
            if (command.equals("report")) {
                System.out.println(
                        "Alice: " + chain.getBalance() +
                                "Bob: " + (chain.getInitial() - chain.getBalance()));
            }
            if (command.equals("quit")) {
                scanner.close();
                break;
            }
        }
    }
}
