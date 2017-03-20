import java.util.ArrayList;


public class Manufacturer {
	int code;
	String description;
	ArrayList<Product> productlist = new ArrayList<Product>();
	Manufacturer left, right;
	int size;
	
	//creating instance of Manufacturer
	public Manufacturer(int c, String d){
		this.code = c;
		this.description = d;
	}

	//setting code
	public void setCode(int c){
		this.code = c;
	}

	
	//getting code
	public int getCode(){
		return code;
	}
	
	
	//setting desciption
	public void setDescription(String d){
		this.description = d;
	}
	
	
	//getting description
	public String getDescription(){
		return description;
	}

	
	//adding product to list or increment count otherwise
	public void addProduct(Product p){
		for(Product e: productlist){
			if(e.description.equals(p.description)){
				p = e;
			}
		}
			if (productlist.contains(p)){
				p.incrementQuantity();
			}
			else{
				productlist.add(p);
			}
		
	}
	
	//returning produclist
	public ArrayList<Product> getProductList(){
		return productlist;
	}
}
