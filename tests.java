
package data2;

/**
 *
 * @author justinriley
 */
public class tests {
    
    public static void main(String[] args) {
 
	FiniteBag<Integer> t1 = new Node<Integer>();
	t1 = t1.add(9, 1);
	t1 = t1.add(2, 7);
	t1 = t1.add(6, 1);
        
        FiniteBag<Integer> t2 = Node.<Integer>empty();
	t2 = t2.add(1, 3);
	t2 = t2.add(2, 1);
	t2 = t2.add(6, 2);
	t2 = t2.add(5, 3);
        
        FiniteBag<Integer> subby = new Node<Integer>();
        subby = subby.add(2, 3);
        subby = subby.add(6, 1);
	
        System.out.println("t1 = " + t1);
	System.out.println("t2 = " + t2);
        System.out.println("subby =" + subby);
        System.out.println("t1.cardinality should be 9\n and is " + t1.cardinality());
        System.out.println("t2.cardinality should be 9\n and is " + t1.cardinality());
        System.out.println("t1.numkeys should be 3\n and is " + t1.numkeys());
        System.out.println("t2.numkeys should be 4\n and is " + t2.numkeys());
        System.out.println("t1.multiplicity(9) should be 1\n and is " + t1.multiplicity(9));
        System.out.println("t2.multiplicity(5) should be 3\n and is " + t2.multiplicity(5));
	System.out.println("t1.inter(t2) should be {2,1},{6,1}\n and is " + t1.inter(t2));
	System.out.println("t1.union(t2) should be {1,3},{2,8},{5,3},{6,3},{9,1}\n and is " + t1.union(t2));
	System.out.println("t1.diff(t2) should be {1,3},{5,3}\n and is " + t1.diff(t2));
        System.out.println("t1.subset(t2) should be false\n and is " + t1.subset(t2));
        System.out.println("subby.subset(t1) should be true\n and is " + subby.subset(t1));
        
    }
}
