
import net.datastructures.*;
import java.util.Comparator;


public class MyAVLTreeMap<K,V> extends TreeMap<K,V> {
	
  /** Constructs an empty map using the natural ordering of keys. */
  public MyAVLTreeMap() { super(); }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public MyAVLTreeMap(Comparator<K> comp) { super(comp); }

  /** Returns the height of the given tree position. */
  protected int height(Position<Entry<K,V>> p) {
    return tree.getAux(p);
  }

  /** Recomputes the height of the given position based on its children's heights. */
  protected void recomputeHeight(Position<Entry<K,V>> p) {
    tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
  }

  /** Returns whether a position has balance factor between -1 and 1 inclusive. */
  protected boolean isBalanced(Position<Entry<K,V>> p) {
    return Math.abs(height(left(p)) - height(right(p))) <= 1;
  }

  /** Returns a child of p with height no smaller than that of the other child. */
  protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
    if (height(left(p)) > height(right(p))) return left(p);     // clear winner
    if (height(left(p)) < height(right(p))) return right(p);    // clear winner
    // equal height children; break tie while matching parent's orientation
    if (isRoot(p)) return left(p);                 // choice is irrelevant
    if (p == left(parent(p))) return left(p);      // return aligned child
    else return right(p);
  }

  /**
   * Utility used to rebalance after an insert or removal operation. This traverses the
   * path upward from p, performing a trinode restructuring when imbalance is found,
   * continuing until balance is restored.
   */
  protected void rebalance(Position<Entry<K,V>> p) {
    int oldHeight, newHeight;
    do {
      oldHeight = height(p);                       // not yet recalculated if internal
      if (!isBalanced(p)) {                        // imbalance detected
        // perform trinode restructuring, setting p to resulting root,
        // and recompute new local heights after the restructuring
        p = restructure(tallerChild(tallerChild(p)));
        recomputeHeight(left(p));
        recomputeHeight(right(p));
      }
      recomputeHeight(p);
      newHeight = height(p);
      p = parent(p);
    } while (oldHeight != newHeight && p != null);
  }

  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
  @Override
  protected void rebalanceInsert(Position<Entry<K,V>> p) {
    rebalance(p);
  }

  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
  @Override
  protected void rebalanceDelete(Position<Entry<K,V>> p) {
    if (!isRoot(p))
      rebalance(parent(p));
  }

  /** Ensure that current tree structure is valid AVL (for debug use only). */
  private boolean sanityCheck() {
    for (Position<Entry<K,V>> p : tree.positions()) {
      if (isInternal(p)) {
        if (p.getElement() == null)
          System.out.println("VIOLATION: Internal node has null entry");
        else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
          System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
          dump();
          return false;
        }
      }
    }
    return true;
  }

  
  public void printTree() {
	  // Put your code to print AVL tree here
	  System.out.println("Print of tree");
	  
	  // Set up a 2 dimensional array , 100 x 100 , should be sufficient, to hold the node keys.
	  String[][] nodeKeys = new String[100][100];
	  
	  for (int i = 0; i < nodeKeys.length; i++) {
		  for (int g = 0; g < nodeKeys[i].length; g++) {
			  nodeKeys[i][g] = "0";
		  }
	  }
	  // call PrintTree1 with starting height, row, column of where to place the root key, and root’s position
	  int startRow = 0;
	  int startCol = 50;
	  int midLen = 25;
	  Position<Entry<K, V>> startHeight = root();
	  
	  printTree1(nodeKeys, startRow, startCol, midLen, startHeight);
	  
	  // Print the array
	  for (int j = 0; j < height(root())*2.5; j++) {
		  for (int k = 0; k < nodeKeys[j].length; k++) {
			  String currLetter = nodeKeys[j][k];
			  if (currLetter != "0") {
				  System.out.print(nodeKeys[j][k]);
			  } else {
				  System.out.print(" ");
			  }
		  }
		  System.out.println();
		  System.out.println();
	  }
  }
  
  // helper recursive method
  public void printTree1(String[][] arr, int row, int col, int len, Position<Entry<K, V>> p) {
	  
	  // Store the  key of the current position in the array
	  K keyValue = p.getElement().getKey();
	  String key = keyValue.toString();
	  
	  arr[row][col] = key;
	  
	  if (p.getElement() == null) {
		  return;
	  }
	  
	  
	  
	  // If current position has a left child , call PrintTree1 with parameters relating to the left child
	  if (left(p).getElement() != null) {
		  arr[row+1][col - (len/2)] = "/";
		  int leftRow = row+2;
		  int leftCol = col-len;
		  Position<Entry<K,V>> leftChild = left(p);
		  printTree1(arr, leftRow, leftCol, len/2, leftChild);
		  
	  }
	  
	  
	  
	  // If current position has a right child , call PrintTree1 with parameters relating to the right child
	  if (right(p).getElement() != null) {
		  arr[row+1][col + (len/2)] = "\\";
		  int rightRow = row+2;
		  int rightCol = col+len;
		  Position<Entry<K,V>> rightChild = right(p);
		  printTree1(arr, rightRow, rightCol, len/2, rightChild);
		  
	  }
  }
   
}