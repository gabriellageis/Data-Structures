
public class QuadruplyLinkedList<E> {

  
  private static class Node<E> {

    /** The element stored at this node */
    private E element;               // reference to the element stored at this node

    /** A reference to the direction of node in the list */
    private Node<E> west;           

    private Node<E> east;            
    
    private Node<E> north;
    
    private Node<E> south;

    //Creates a node with the given element and next node.

    public Node(E el, Node<E> w, Node<E> e, Node<E> n, Node<E> s) {
      element = el;
      west = w;
      east = e;
      north = n;
      south = s;
    }

    // public accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    public Node<E> getWest() { return west; }

    public Node<E> getEast() { return east; }
    
    public Node<E> getNorth() { return north; }
    
    public Node<E> getSouth() { return south; }

 
    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
   // public void setNext(Node<E> n) { east = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the DoublyLinkedList
  /** Sentinel node at the beginning of the list */
  private Node<E> header;                    // header sentinel
  
  private Node<E> currNode;

  /** Sentinel node at the end of the list */
  private Node<E> trailer;                   // trailer sentinel

  /** Constructs a new empty list. */
  public QuadruplyLinkedList() {
    header = new Node<>(null, null, null, null, null);      // create header
    trailer = new Node<>(null, header, null, null, null);   // trailer is preceded by header
    currNode = header;
                      
  }
  
  // Included Methods
  public void add(E letter, E direction) {
	  Node tempNode = new Node(letter, null, null, null, null);
	  if (direction.equals("north")) {
		  if(currNode.north != null) {
			  currNode.north = tempNode;
			  tempNode.south = currNode;
		  }
		  
	  }else if (direction.equals("south")) {
		  if(currNode.south != null) {
			  currNode.south = tempNode;
			  tempNode.north = currNode;
		  }
		  
	  }else if (direction.equals("west")) {
		  if(currNode.west != null) {
			  currNode.west = tempNode;
			  tempNode.east = currNode;
		  }
		  
	  }else if (direction.equals("east")) {
		  if(currNode.east != null) {
			  currNode.east = tempNode;
			  tempNode.west = currNode;
		  }
	  }
  }
  
  public void root(E letter) {
	  Node root = new Node<>(letter, null, null, null, null);
	  header.east = root;
	  currNode = root;
  }
  
  public void move(E direction) {
	  if(direction.equals("north")) {
		  if(currNode.north != null) {
			  currNode = currNode.north;  
		  }else {
			  System.out.println("invalid direction");
		  }
		  
	  }else if(direction.equals("south")) {
		  if(currNode.south != null) {
			  currNode = currNode.south;  
		  }else {
			  System.out.println("invalid direction");
		  }
	  }else if(direction.equals("east")) {
		  if(currNode.east != null) {
			  currNode = currNode.east;  
		  }else {
			  System.out.println("invalid direction");
		  }
	  }else if(direction.equals("west")) {
		  if(currNode.west != null) {
			  currNode = currNode.west;  
		  }else {
			  System.out.println("invalid direction");
		  }
	  }
  }
	  
	  public void printer(Node curr, int depth) {
	        //if the depth is zero we want to print and make a recursive call
	        // if the depth is one we do not make a recursive call and just print
	        if (depth == 0 && curr.east == null){
	            System.out.println("This is the end of the lewis dot structure");
	        }else {
	            if(depth == 1){
	                System.out.println(curr.element);
	            }else if (depth == 0){
	                if(curr != null)
	                if(curr.north != null){

	                }
	    
	            }
	        }
	  
  }
  
}
