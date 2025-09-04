package pas.huffman.src;

/**
 * Given Leaf Node Class.
 */
public class HuffLeafNode extends HuffBaseNode {
  private byte symbol;

  /**
   * Ctor for a leaf node.
   *
   * @param symbol Symbol saved in the node.
   * @param weight Weight of this node.
   */
  public HuffLeafNode(byte symbol, int weight) {
    super(weight);
    this.symbol = symbol;
  }

  /**
   * Getter for symbol.
   *
   * @return The symbol of this node.
   */
  public byte getSymbol() {
    return symbol;
  }

  @Override
  public boolean isLeaf() {
    return true;
  }

}
