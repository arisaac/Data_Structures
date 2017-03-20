import java.math.BigInteger;
public class Product {
	int code;
	String upc;
	String description;
	int quantity = 1;
	
	
	//creating the instance of a Product
	public Product(int c, String u, String d){
		this.code = c;
		this.upc = u;
		this.description = d;
	}
	
	
	//setting upc code
	public void setupc(String x){
		this.upc = x;
	}
	
	
	//getting upc code
	public String getupc(){
		return upc;
	}
	
	//incrementing Quantity
	public void incrementQuantity(){
		quantity++;
	}
	
	
	//getting Manufacturer code
	public int getManufacturerCode(Manufacturer manufacture){
		return manufacture.code;
	}
}
