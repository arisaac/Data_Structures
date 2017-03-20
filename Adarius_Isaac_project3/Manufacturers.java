import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Manufacturers {


	ManufacturersBST BST = new ManufacturersBST();
	ManufacturersHashTable t = new ManufacturersHashTable();
	static ArrayList<Manufacturer> Manlist = new ArrayList<>();
	//setting BST
	public Manufacturers(ManufacturersBST T){
		this.BST = T;
	}
	//setting HashTable
	public Manufacturers(ManufacturersHashTable T){
		this.t = T;
	}
	//converting Hashtable to an Array
	public void convertHashtoArray(ArrayList<Manufacturer> manu){
		for(Manufacturer d: manu){
		Manlist.add(t.get(d.code));
		}
	}
	//converting BST to an array doing the inorder traversal
	public void convertBSTtoArray(){
		convertBSTtoArray(BST.root) ;
	}
	
	public void convertBSTtoArray(Manufacturer p) {
		if (p==null) return;
		if(p.left != null){
			convertBSTtoArray(p.left);
		}
		Manlist.add(p);
		if(p.right != null){
			convertBSTtoArray(p.right);
		}
	}
	//the Manufacturer list alphabetically
	public void sortManlist(){
		Collections.sort(Manlist, new MySort());
	}
}


	

