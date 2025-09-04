package pas.huffman.src;

/**
 * Given Base Node Class with Altered CompareTo.
 */
public abstract class HuffBaseNode implements Comparable<HuffBaseNode> {

  private int weight;

  /**
   * Ctor.
   *
   * @param weight weight of the node
   */
  public HuffBaseNode(int weight) {
    this.weight = weight;
  }

  /**
   * Weight Getter.
   *
   * @return weight of this instance of base node.
   */
  public int getWeight() {
    return weight;
  }

  @Override
  public int compareTo(HuffBaseNode other) {

    // If any of the weights are smaller or larger, return -1/1
    if (getWeight() < other.getWeight()) {
      return -1;
    } else if (getWeight() > other.getWeight()) {
      return 1;

    // In the case that it's equal
    } else {
      // Find the minimum byte of each tree
      int thisMin = findMinimum(this);
      int otherMin = findMinimum(other);

      // return accordingly
      if (thisMin < otherMin) {
        return -1;
      } else if (thisMin > otherMin) {
        return 1;
      }

      return 0;
    }

  }

  /**
   * Finds the minimum byte of a tree (Recursively).
   *
   * @param node Parent node of the tree to be searched through
   * @return the minimum byte of the tree
   */
  private int findMinimum(HuffBaseNode node) {

    // If it's a leaf node return its symbol
    if (node instanceof HuffLeafNode) {
      HuffLeafNode hln = (HuffLeafNode) node;
      return hln.getSymbol();
    }

    // If its an internal node
    HuffInternalNode hin = (HuffInternalNode) node;
    int left = 1000;
    int right = 1000;

    // Look for the minimum of it's left and right
    if (hin.getLeft() != null) {
      left = findMinimum(hin.getLeft());
    }
    if (hin.getRight() != null) {
      right = findMinimum(hin.getRight());
    }

    // Return the least
    if (left <= right) {
      return left;
    } else {
      return right;
    }
  }

  /**
   * Returns if this instance of base node is a leaf or not.
   *
   * @return whether the node is a leaf or not
   */
  public abstract boolean isLeaf();
}
