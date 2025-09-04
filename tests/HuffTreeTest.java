package pas.huffman.tests;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import pas.huffman.src.BitSequence;
import pas.huffman.src.HuffTree;

/**
 * Testing Class for HuffTree.
 */
public class HuffTreeTest {
  File file = new File("C:\\Users\\aaqui\\OneDrive - James Madison University\\CS240_F24\\CS240_F24\\src\\pas\\huffman\\bytes.dat");

  @Test
  public void testCtor() {
    try {
      HuffTree huffTree = new HuffTree(file);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testEncode() {
    try {
      HuffTree huffTree = new HuffTree(file);

      BitSequence bitSequence = huffTree.encode(file);

      File otherFile = new File("C:\\Users\\aaqui\\OneDrive - James Madison University\\CS240_F24\\CS240_F24\\src\\pas\\huffman\\test.dat");
      huffTree.decode(bitSequence, otherFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
