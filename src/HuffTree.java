package pas.huffman.src;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Huff Tree Class.
 *
 * @author Aiden Aquino
 * @version 11/20/2024
 */
public class HuffTree {

  private HuffBaseNode root;
  private HashMap<Byte, Integer> frequencies;
  private HashMap<Byte, BitSequence> codes; // Maps from bytes to their Huffman codes.

  /**
   * Create A Huffman Tree Based on the frequencies of bytes in a file.
   *
   * @param inFile File used to create the tree
   * @throws IOException IOException for file reading errors
   */
  public HuffTree(File inFile) throws IOException {
    // Read file
    FileInputStream fis = new FileInputStream(inFile);
    BufferedInputStream bufferedReader = new BufferedInputStream(fis);

    /*
     * Create Frequency map.
     */
    int read = bufferedReader.read();
    frequencies = new HashMap<Byte, Integer>();

    // Run while there are still bytes in the the file
    while (read != -1) {
      // Cast the newly read byte
      byte readCast = (byte) read;

      // Count frequencies
      if (frequencies.containsKey(readCast)) {
        frequencies.put(readCast, frequencies.get(readCast) + 1);
      } else {
        frequencies.put(readCast, 1);
      }
      read = bufferedReader.read();
    }
    bufferedReader.close();

    /*
     * Create the Tree.
     */
    root = buildTree(frequencies);

    // Check for if there is 1 or less byte in the fyle
    if (root == null) {

      // For 0 bytes
      if (frequencies.size() == 0) {
        return;
      }

      // For 1 byte
      HuffLeafNode leaf = null;
      for (Byte key : frequencies.keySet()) {
        // Should only run once
        leaf = new HuffLeafNode(key, frequencies.get(key));
      }
      root = new HuffInternalNode(leaf, null, 1);
    }

    /*
     * Form the codes with recursion
     */
    codes = new HashMap<Byte, BitSequence>();
    createCodes(root, new BitSequence());
  }

  /**
   * Recursive Method to find codes to each byte.
   *
   * @param root Start/Current node
   */
  private void createCodes(final HuffBaseNode root, BitSequence sequence) {
    // If we hit a leaf
    if (root.isLeaf()) {
      HuffLeafNode castedLeafNode = (HuffLeafNode) root;

      // Add the symbol to codes with it's sequence
      codes.put(castedLeafNode.getSymbol(), sequence);
      return;
    }

    HuffInternalNode castedInternalNode = (HuffInternalNode) root;

    // Run Left
    BitSequence newSequence = new BitSequence();
    newSequence.appendBits(sequence.toString() + "0");
    createCodes(castedInternalNode.getLeft(), newSequence);

    // Run Right
    newSequence = new BitSequence();
    newSequence.appendBits(sequence.toString() + "1");
    if (castedInternalNode.getRight() != null) {
      createCodes(castedInternalNode.getRight(), newSequence);
    }
  }

  /**
   * Construct a hufftree with given frequencies.
   *
   * @param frequencies frequencies
   */
  public HuffTree(HashMap<Byte, Integer> frequencies) {
    this.frequencies = frequencies;
    root = buildTree(frequencies);


    if (root == null) {
      if (frequencies.size() == 0) {
        return;
      }

      HuffLeafNode leaf = null;
      for (Byte key : frequencies.keySet()) {
        leaf = new HuffLeafNode(key, frequencies.get(key));
      }

      root = new HuffInternalNode(leaf, null, 1);
    }

    /*
     * Form the codes with recursion
     */
    codes = new HashMap<Byte, BitSequence>();
    createCodes(root, new BitSequence());
  }

  public HashMap<Byte, Integer> getFrequencies() {
    return new HashMap<>(frequencies);
  }

  /**
   * Encoding a given file with the map made in this tree.
   *
   * @param inFile File to encode.
   * @return The bitsequence of the file
   * @throws IOException IOException for file reading errors
   */
  public BitSequence encode(File inFile) throws IOException {

    // Return sequence
    BitSequence bitSequence = new BitSequence();

    // Buffered Input
    FileInputStream fis = new FileInputStream(inFile);
    BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);

    // Run through all the bytes, and add on the bitSequence of them to return sequence
    int read = bufferedInputStream.read();
    while (read != -1) {
      bitSequence.appendBits(codes.get((byte) read));
      read = bufferedInputStream.read();
    }

    bufferedInputStream.close();
    return bitSequence;
  }

  /**
   * Decode a given file with this tree's codes.
   *
   * @param encoding The encoding to decode
   * @param outFile file to save to
   * @throws IOException IOException for file reading errors
   */
  public void decode(BitSequence encoding, File outFile) throws IOException {

    // Output stream for writing out to a file
    FileOutputStream fos = new FileOutputStream(outFile);

    HuffBaseNode currRoot = root; // Placeholder variable for tree traversals

    // Run through every bit
    for (int bit : encoding) {

      // If this node is an internal node
      if (!currRoot.isLeaf()) {
        HuffInternalNode huffCast = (HuffInternalNode) currRoot; // Cast

        // Traverse left and right based on bit 0 or 1 respectively
        if (bit == 0) {
          currRoot = huffCast.getLeft();
        } else {
          currRoot = huffCast.getRight();
        }
      }

      // If it's a leaf
      if (currRoot.isLeaf()) {

        HuffLeafNode huffLeafCast = (HuffLeafNode) currRoot; // Cast

        // Write out the symbol and reset the root
        fos.write((byte) huffLeafCast.getSymbol());
        currRoot = root;
      }
    }
    fos.close();
  }

  private HuffBaseNode buildTree(HashMap<Byte, Integer> frequencies) {
    HuffBaseNode tmp1 = null;
    HuffBaseNode tmp2 = null;
    HuffBaseNode tmp3 = null;

    PriorityQueue<HuffBaseNode> pq = new PriorityQueue<>();

    for (Byte symbol : frequencies.keySet()) {
      pq.add(new HuffLeafNode(symbol, frequencies.get(symbol)));
    }

    while (pq.size() > 1) { // While two items left
      tmp1 = pq.poll();
      tmp2 = pq.poll();
      tmp3 = new HuffInternalNode(tmp1, tmp2, tmp1.getWeight() + tmp2.getWeight());
      pq.add(tmp3); // Return new tree to heap
    }
    return tmp3; // Return the tree
  }

}
