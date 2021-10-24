import java.util.Date;
import java.security.MessageDigest;

public class Block {
    // the Hash code belonging to this Block
    private String hash;

    // The hash code of the previous block
    private String previousHash;

    // The Climate Change Info
    private String data;

    // The timestamp of the creation of this block
    private long timeStamp;

    // The arbritrary number to help mine the hash
    private int mineHelper;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime(); // time of creation
        this.hash = calculateBlockHash();
    }

    // method that calculates the hash for this Block.
    public String calculateBlockHash() {
        // Input to determine the hash.
       // String input = this.previousHash + Long.toString(timeStamp) + Integer.toString(mineHelper) + data;
        String input = this.previousHash + Integer.toString(mineHelper) + data;
        return getHash(input);
    }

    // getHash helper method!
    private String getHash(String input) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256"); 
            byte[] hashBytes = sha.digest(input.getBytes("UTF-8"));  
            StringBuffer hexHash = new StringBuffer();

            for (int i = 0; i < hashBytes.length; i++) {
                String hex = Integer.toHexString(0xff & hashBytes[i]);
                if (hex.length() == 1)
                    hexHash.append('0');
                hexHash.append(hex);
            }
  
            return hexHash.toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String mineHash(int flag) {
        String flagStr = new String(new char[flag]).replace('\0', '0');
        while (!hash.substring(0, flag).equals(flagStr)) {
            mineHelper++;
            hash = calculateBlockHash();
        }
        return hash;
    }

    // Getters /////////////////////////////////////////////////////////////
    public String getCurrentHash() {
        return this.hash;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public String getData() {
        return this.data;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }
    ////////////////////////////////////////////////////////////////////////
}
