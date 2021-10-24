import java.util.ArrayList;
public class Chain {
    private ArrayList<Block> chain;

    public Chain(ArrayList<Block> chain) {
        this.chain = new ArrayList<Block>();
        for (Block block : chain) {
            addBlock(block);
        }
    } 

    public Chain(Block block) {
        this.chain = new ArrayList<Block>();
        addBlock(block);
    }
    
    public Chain() {
        chain = new ArrayList<Block>();
        
    }

    public void addBlock(Block newBlock) {
        newBlock.mineHash(4);
        this.chain.add(newBlock);
    }

    public boolean checkValidity() {
        for (int i = 0; i < chain.size(); i++) {
            Block compare = chain.get(i);
            String prevHash;
            if (i == 0) {
                prevHash = "0";
            } else {
                prevHash = chain.get(i-1).getCurrentHash();
            }

            //System.out.println(compare.getCurrentHash());
            //System.out.println(compare.getCurrentHash().equals(compare.calculateBlockHash()));
            //System.out.println(prevHash.equals(compare.getPreviousHash()));
            //System.out.println(prevHash);
            //System.out.println(compare.getPreviousHash());
            //System.out.println(compare.getCurrentHash().substring(0,2).equals("00"));

            if (!(compare.getCurrentHash().equals(compare.calculateBlockHash()) && // stored hash == one it calculates
                prevHash.equals(compare.getPreviousHash()) && // previous hashes are actually equal.
                compare.getCurrentHash().substring(0,4).equals("0000"))) { // Checks that the hash is a valid hash
                return false;
            }            
        }
        return true;
    }
    
    public int size() {
        return chain.size();
    }
    
    public Block get(int i) {
        return chain.get(i);
    }
}
