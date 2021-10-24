import java.io.*;
import java.util.Scanner;

public class BlockRunner {
    private static Chain chain;
    
    public static void main(String[] args) {
        chain = new Chain();
        try {
            importChain();
        } catch(IOException e) {
            System.out.println("corrupted file");
        }
        
        addData("the climate is changing");
        addData("climate change is real");
        addData("reduce emissions");
        addData("this is ironic. blockchain is often criticized for its environmental impact. lol");
        addData("the science deniers will never be able to mess with our data");
        addData("truth.");
                
        addData("correct scientific info");
        
        System.out.println(chain.checkValidity());
    }
    
    public static void importChain() throws IOException {
		Scanner input = new Scanner(new File("chain.txt"));
		input.nextLine();
		while (input.hasNextLine()) {
			Block block = new Block(input.nextLine(), input.nextLine());
            chain.addBlock(block);
		}
	}
    
    public static void addData(String data) {
        Block b = new Block(data, chain.get(chain.size() - 1).getCurrentHash());
        chain.addBlock(b);
        try(FileWriter fw = new FileWriter("chain.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print("\n" + b.getData());
            out.print("\n" + b.getPreviousHash());
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}