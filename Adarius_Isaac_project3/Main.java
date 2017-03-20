import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public abstract class Main {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanner = new Scanner(System.in);
		String n = scanner.next();
		
		
		//read if it is a BST fisrt
		if(n.equals("-b")){
			
			
			//reading in the file and storing the Manufacturers into a BST
			Scanner mcodes = new Scanner(new File("mcodes.csv"));
			ManufacturersBST manList = new ManufacturersBST();
			while(mcodes.hasNextLine()){
				String[] manufactureCode = mcodes.nextLine().split(",");
				int code = Integer.parseInt(manufactureCode[0]);
				String man = manufactureCode[1];
				manList.add(code,man);
			}
			mcodes.close();
		
			
			//converting the BST to array for sorting using inorder traversal
			Manufacturers man = new Manufacturers(manList);
			man.convertBSTtoArray();
			man.sortManlist();
			
			
			//reading in the products from sales.csv file
			ArrayList<Product> prodlist = new ArrayList<>();
			Scanner sales = new Scanner(new File("sales.csv"));
			while(sales.hasNextLine()){
				String[] ProductCode = sales.nextLine().split(",");
				int mancode = Integer.parseInt(ProductCode[0].substring(0,6));
				String prodcode = ProductCode[0];
				String prod = ProductCode[2];
				Product product = new Product(mancode,prodcode,prod);
				prodlist.add(product);

			}
			
			
			//adding products to productslist for Manufacturer
			for(Manufacturer m : man.Manlist){
				for(Product a : prodlist){
					if(m.code == a.code){
						m.addProduct(a);
					}
				}
			}
			
			
			//printing out the output
			for(Manufacturer e: man.Manlist){
				if(e.productlist.size() != 0){	
					System.out.println(e.description.replace("\"",""));
					for(Product d:e.productlist) {
						System.out.println( "Qty" + " " + d.quantity + " " + '-' + " " +d.description.replace("\"",""));
					}
					System.out.println();
					System.out.println();
				}
			}
		}
		
		
		//else do the HashTable 
		else{

			//reading in Manufacturers to fill in the HashTable
			ManufacturersHashTable t = new ManufacturersHashTable();Scanner mcodes = new Scanner(new File("mcodes.csv"));
			ArrayList<Manufacturer> manList = new ArrayList<>();
			while(mcodes.hasNextLine()){
				String[] manufactureCode = mcodes.nextLine().split(",");
				int code = Integer.parseInt(manufactureCode[0]);
				String man = manufactureCode[1];
				Manufacturer manufacturer = new Manufacturer(code,man);
				manList.add(manufacturer);
			}

			t.x = manList.size();
			for(Manufacturer d : manList){
				t.add(d.code, d.description);
			}
			
			
			//converting HashTable to a dynamic array
			Manufacturers table = new Manufacturers(t);
			table.convertHashtoArray(manList);
			table.sortManlist();
		
			
			//reading in the products for productlist for manufacturers
			ArrayList<Product> prodlist = new ArrayList<>();
			Scanner sales = new Scanner(new File("sales.csv"));
			while(sales.hasNextLine()){
				String[] ProductCode = sales.nextLine().split(",");
				int mancode = Integer.parseInt(ProductCode[0].substring(0,6));
				String prodcode = ProductCode[0];
				String prod = ProductCode[2];
				Product product = new Product(mancode,prodcode,prod);
				prodlist.add(product);

			}
			
			for(Manufacturer m : table.Manlist){
				for(Product a : prodlist){
					if(m.code == a.code){
						m.addProduct(a);
					}
				}
			}
			
			//printing out the output
			for(Manufacturer e: table.Manlist){
				if(e.productlist.size() != 0){	
					System.out.println(e.description);
					for(Product d:e.productlist) {
						System.out.println( "Qty" + " " + d.quantity + " " + '-' + " " +d.description);
					}
					System.out.println();
					System.out.println();
				}
			}
		}
	}
}
