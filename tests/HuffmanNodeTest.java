package pas.huffman.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import pas.huffman.src.HuffInternalNode;
import pas.huffman.src.HuffLeafNode;

public class HuffmanNodeTest {

  @Test
  public void testCompareTo() {
    HuffLeafNode hlnL = new HuffLeafNode((byte)30, 2);
    HuffLeafNode hlnR = new HuffLeafNode((byte)27, 2);
    HuffInternalNode hin = new HuffInternalNode(hlnL, hlnR, 2);

    HuffLeafNode hlnL2 = new HuffLeafNode((byte) 15, 2);
    HuffLeafNode hlnR2 = new HuffLeafNode((byte) 27, 2);
    HuffInternalNode hin2 = new HuffInternalNode(hlnL2, hlnR2, 2);

    assertEquals(hlnL.compareTo(hlnR), 1);

    assertEquals(hlnL.compareTo(hin), 1);
    assertEquals(hin.compareTo(hlnL), -1);

    assertEquals(hin.compareTo(hlnR), 0);


    assertEquals(hin2.compareTo(hin), -1);
    assertEquals(hin2.compareTo(hlnR), -1);

  }
}
