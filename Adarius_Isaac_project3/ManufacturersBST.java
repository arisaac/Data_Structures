import java.awt.RenderingHints.Key;
import java.util.NoSuchElementException;


public class ManufacturersBST {
	
	Manufacturer root;
	int s;
	public ManufacturersBST(){
		root = null;
	}
	public boolean isEmpty() {
		return size() == 0;
	}

	// return number of key-value pairs in BST
	public int size() {
		return size(root);
	}

	// return number of key-value pairs in BST rooted at x
	public int size(Manufacturer x) {
		if (x == null) return 0;
		else return x.size;
	}

	public boolean contains(Manufacturer M) {
		return get(M) != null;
	}

	// geting value of Manufacture
	public String get(Manufacturer x) {
		return get(root, x.code);
	}
	
	// getting value of Manufacturer helper method
	public String get(Manufacturer x, int c) {
		if (x == null) return null;
		if      (c < x.code) return get(x.left, c);
		else if (c > x.code) return get(x.right, c);
		else              return x.description;
	}

    //insert into BST
	public void add(int c, String d) {
		if (d == null) { delete(c); return; }
		root = add(root, c, d);
		assert check();
	}

	public Manufacturer add(Manufacturer x, int c, String d) {
		if (x == null) return new Manufacturer(c, d);
		//int cmp = c.compareTo(x.code);
		if      (c < x.code) x.left  = add(x.left,  c, d);
		else if (c > x.code) x.right = add(x.right, c, d);
		else              x.description.equals(d);
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}
	//deleting minimun Manufacturer
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
		assert check();
	}
	
	//delete mininum Manufacturer helper method
	public Manufacturer deleteMin(Manufacturer x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	//delete Manufacturer
	public void delete(int key) {
		root = delete(root, key);
		assert check();
	}

	//delet Manufacturer helper method
	public Manufacturer delete(Manufacturer x, int c) {
		if (x == null) return null;
		//int cmp = key.compareTo(x.key);
		if      (c < x.code) x.left  = delete(x.left,  c);
		else if (c > x.code) x.right = delete(x.right, c);
		else { 
			if (x.right == null) return x.left;
			if (x.left  == null) return x.right;
			Manufacturer t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		} 
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	} 


	//checking if it is a BST
	public boolean check() {
		return isBST();
	}
	
	//mininum Manufacturer code
	public Manufacturer min() {
		if (isEmpty()) return null;
		return min(root);
	} 
	
	//minimum manufacturer code
	public Manufacturer min(Manufacturer x) { 
		if (x.left == null) return x; 
		else                return min(x.left); 
	} 

	//BST exist
	public boolean isBST() {
		return isBST(root, 0, 0);
	}

	//BST exist helper method
	public boolean isBST(Manufacturer x, int min, int max) {
		if (x == null) return true;
		if (min != 0 && x.code <= min) return false;
		if (max != 0 && x.code >= max) return false;
		return isBST(x.left, min, x.code) && isBST(x.right, x.code, max);
	} 
}
