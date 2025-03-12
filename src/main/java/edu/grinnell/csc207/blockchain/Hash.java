package edu.grinnell.csc207.blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {

    public byte[] Hash;

    public Hash(byte[] hash) {
        this.Hash = hash;
    }

    public byte[] getData() {
        return this.Hash;
    }

    public boolean isValid() {
        return this.hash.substring(0, 3).equals("000");
    }

    public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(msg.getBytes());
        byte[] hash = md.digest();
        return hash; // Remaining implementation below...
    }

    public string toString() {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < this.Hash.length; i++) {
            hexString.append(String.format("%02x", Byte.toUnsignedInt(this.Hash[i])));
        }
        return hexString.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Hash) {
            return Arrays.equals(this.hash, ((Hash) other).hash);
        }
        return false;
    }
}
