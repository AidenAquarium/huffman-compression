package pas.huffman.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * MadZip class.
 *
 * @author Aiden Aquino
 * @version 11/18/2024
 *
 */
public class MadZip {

  /**
   * Compresses a file using a Huffman Tree.
   *
   * @param compressFrom Location of file to be compressed
   * @param saveTo Location for the file to be saved to
   * @throws IOException if file cannot be read of destination file cannot be written to
   */
  public static void zip(final File compressFrom, final File saveTo) throws IOException {

    // Create the huffTree
    HuffTree huffTree = new HuffTree(compressFrom);

    // Get the encoding
    BitSequence encoding = huffTree.encode(compressFrom);

    // Create the save object
    HuffmanSave huffSave = new HuffmanSave(
        encoding, huffTree.getFrequencies());

    /*
    * Create Compressed version of file and save it
    */
    FileOutputStream fos = new FileOutputStream(saveTo);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(huffSave);
    oos.close();
  }

  /**
   * Uncompress a file using a Hufftree.
   *
   * @param uncompressFrom file to uncompress
   * @param saveTo where to save the uncompressed file
   * @throws IOException IOException for file errors
   * @throws ClassNotFoundException class not found
   */
  public static void unzip(final File uncompressFrom,
          final File saveTo) throws IOException, ClassNotFoundException {
    /*
     * Reconstruct Huffman tree from stored.
     */
    FileInputStream fis = new FileInputStream(uncompressFrom);
    ObjectInputStream ois = new ObjectInputStream(fis);

    HuffmanSave huffSave = (HuffmanSave) ois.readObject();

    /*
     * Decode the file
     */
    HuffTree huffTree = new HuffTree(huffSave.getFrequencies());
    huffTree.decode(huffSave.getEncoding(), saveTo);

    ois.close();
  }
}
