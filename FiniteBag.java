package data2;

/**
 *
 * @author justinriley
 */
public interface FiniteBag<L extends Comparable> {
    /*
     * Counts the number of total items in the FiniteBag
     * Returns the number of elements in t.
     */

    int cardinality();

    /*
     * Counts the number of nodes in the tree representing the FiniteBag
     * returns an int representing the number of nodes
     */
    int numkeys();

    /*
     * counts the number of times an element appears in a node
     * takes the element it is counting
     * returns an int representing the number fo times the element 
     * occurs in the node. 
     */
    int multiplicity(L elt);

    /*
     * Determines if the FiniteBag is empty
     * returns true if the FiniteBag is empty, false otherwise
     */
    boolean isEmptyHuh();

    /*
     * Determines if an element is in the FiniteBag
     * Takes an integer 
     * returns true if the integer is in the FiniteBag, false otherwise     */
    boolean member(L elt);

    /*
     * Adds n instances of element L to a FiniteBag.
     * Takes the element L and int n 
     * returns a new FiniteBag containg the added element instances and the 
     * old nodes
     */
    FiniteBag<L> add(L elt, int n);

    /*
     * Removes n instances of element L from a FiniteBag. Takes the element L
     * and int n returns a new FiniteBag with the old nodes and n less L
     */
    FiniteBag<L> remove(L elt, int n);

    /*
     * Unions two FiniteBags takes a FiniteBag u returns a new FiniteBag
     * containing all the elements of both the FiniteBag it is called on and u
     */
    FiniteBag<L> union(FiniteBag<L> u);

    /*
     * Finds the intersection of two FiniteBagS takes a FiniteBag u returns a
     * new FiniteBag with only the elements that appear in both the FiniteBag it
     * was called on and u.
     */
    FiniteBag<L> inter(FiniteBag<L> u);

    /*
     * Determines the difference between two FiniteBags 
     * takes a FiniteBag u
     * returns a new FiniteBag that contains all the elements in u,
     * that aren't in the FiniteBag this method is called on
     */
    FiniteBag<L> diff(FiniteBag<L> u);

    /*
     * Determines if two FiniteBags are equal. 
     * takes a FiniteBag u 
     * returns true if both u and the FiniteBag this is called on contain
     * all of the same elements, false otherwise
     */
    boolean equal(FiniteBag<L> u);

    /*
     * Determines if one FiniteBag is the subset of another 
     * takes a FiniteBag u
     * returns true if all the elements in the FiniteBag this is called on are
     * also in u, false otherwise
     */
    boolean subset(FiniteBag<L> u);

}

public class Subtree<L extends Comparable> implements FiniteBag<L> {
    
    L key;
    int multiplicity;
    FiniteBag<L> leftree;
    FiniteBag<L> rightree;
  
    
    
    //Constructor for Subtree. 
    public Subtree(FiniteBag left, L key, FiniteBag right, int multiplicity) {
        this.leftree = left;
        this.rightree = right;
        this.key = key;
        this.multiplicity = multiplicity;
       
    }

    public int cardinality() {
	return this.multiplicity + leftree.cardinality() + rightree.cardinality();
    }
    
    public int numkeys(){
        return 1 + leftree.numkeys() + rightree.numkeys();
    }
    
    public int multiplicity(L elt) {
        if(elt.compareTo(key) == 0) {
	    return this.multiplicity;
	} else if(elt.compareTo(key) < 0) {
	    return leftree.multiplicity(elt);
	} else {
	    return rightree.multiplicity(elt);
	}
    }

    public boolean isEmptyHuh() {
        if (this == Node.empty())
	return true; 
        return false; }

    public boolean member(L elt) {
	if(elt.compareTo(key) == 0 && this.multiplicity != 0) {
	    return true;} 
        else if(elt.compareTo(key) < 0) {
	    return leftree.member(elt);}
        else {
            return rightree.member(elt); }  
    }

    public FiniteBag<L> add(L elt, int n) {
	if(elt.compareTo(key) == 0) {
	    return new Subtree<L>(leftree, elt, rightree, multiplicity + n);}
        else if(elt.compareTo(key) < 0) {
	    return new Subtree<L>(leftree.add(elt, n), key, rightree, multiplicity); }
        else {
	    return new Subtree<L>(leftree, key, rightree.add(elt, n), multiplicity); }
    }

    public FiniteBag<L> remove(L elt,int n) {
	if(elt.compareTo(key) < 0) {
	    return new Subtree<L>(leftree.remove(elt, n), key, rightree, multiplicity); }
        else if(elt.compareTo(key) > 0) {
	    return new Subtree<L>(leftree, key, rightree.remove(elt, n), multiplicity); }
        else {
	    return leftree.union(rightree); }
    }

    public FiniteBag<L> union(FiniteBag<L> u) {
	return u.union(leftree).union(rightree).add(key, multiplicity); 
    }
    
    public FiniteBag<L> inter(FiniteBag<L> u) {
	if(u.member(key)) {
	    return leftree.inter(u).union(rightree.inter(u).add(key, Math.min(multiplicity, u.multiplicity(key))));}
        else {
	    return leftree.union(rightree).inter(u); } 
    }
    
    public FiniteBag<L> diff(FiniteBag<L> u) {
	return leftree.union(rightree).diff(u.remove(key, multiplicity)); }

    public boolean equal(FiniteBag<L> u) {
	return this.subset(u) && u.subset(this); }
    
    public boolean subset(FiniteBag<L> u) {
	return u.member(key) && leftree.subset(u) && rightree.subset(u); }

    public String toString() {
        return  "" +leftree + '{' +
                 key + ","+ multiplicity + "}"
                + rightree ;
    }  
    
}

public class Node<L extends Comparable> implements FiniteBag<L>{
     //Constuctor for Node
    //Initializes the outputs of these operations called on
    //an empty set. 
    public Node() {}
    
    public static <L extends Comparable> FiniteBag<L> empty() {
	return new Node(); }

    public int cardinality() {
	return 0; }
    
    public int numkeys() {
        return 0;
    }
    
    public int multiplicity(L elt){
        return 0;
    }

    public boolean isEmptyHuh() {
        return true; }

    public boolean member(L elt) {
	return false; }

    public FiniteBag<L> add(L elt, int n) {
	return new Subtree<L>(this, elt, this, n); 
    }

    public FiniteBag<L> remove(L elt, int n) {
	return this; }
    
    public FiniteBag<L> union(FiniteBag<L> u) {
	return u; }

    public FiniteBag inter(FiniteBag<L> u) {
	return this; }

    public FiniteBag<L> diff(FiniteBag<L> u) {
	return u; }
    
    public boolean equal(FiniteBag<L> u) {
	return u.isEmptyHuh(); }

    public boolean subset(FiniteBag<L> u) {
	return true; }
    
    public String toString() {
	return ""; }
}

