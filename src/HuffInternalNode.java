package pas.huffman.src;

/**
 * Given Internal Node Class.
 */
public class HuffInternalNode extends HuffBaseNode {

  private HuffBaseNode left;
  private HuffBaseNode right;

  /**
   * Ctor for HuffInternalNode.
   *
   * @param left left of the internal node
   * @param right right of the internal node
   * @param weight weight of the internal node
   */
  public HuffInternalNode(HuffBaseNode left, HuffBaseNode right, int weight) {
    super(weight);
    this.left = left;
    this.right = right;
  }

  /**
   * Getter for the left node.
   *
   * @return The left node of this internal node.
   */
  public HuffBaseNode getLeft() {
    return left;
  }

  /**
   * Getter for the right node.
   *
   * @return The right node of this internal node.
   */
  public HuffBaseNode getRight() {
    return right;
  }

  @Override
  public boolean isLeaf() {
    return false;
  }

}
