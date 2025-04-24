package edu.grinnell.csc207.blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {

    public byte[] hash;

    /**
     * Constructor for the Hash class.
     * 
     * @param hash The byte array representing the hash value.
     */
    public Hash(byte[] hash) {
        this.hash = hash;
    }

    /**
     * Returns the byte array representing the hash value.
     * 
     * @return The byte array representing the hash value.
     */
    public byte[] getData() {
        return this.hash;
    }

    /**
     * Checks if the hash is valid.
     * A hash is considered valid if it starts with "000".
     * 
     * @return true if the hash is valid, false otherwise.
     */
    public boolean isValid() {
        String hexString = toString();
        return hexString.startsWith("000");
    }

    /**
     * Calculates the SHA-256 hash of a given message.
     * 
     * @param msg The message to hash.
     * @return The byte array representing the hash value.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not found.
     */
    public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(msg.getBytes());
        byte[] hash = md.digest();
        return hash; // Remaining implementation below...
    }

    /**
     * Returns the string representation of the hash value in hexadecimal format.
     * 
     * @return The string representation of the hash value.
     */
    public String toString() {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < this.hash.length; i++) {
            hexString.append(String.format("%02x", Byte.toUnsignedInt(this.hash[i])));
        }
        return hexString.toString();
    }
}
