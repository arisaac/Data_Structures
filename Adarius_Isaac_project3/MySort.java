import java.util.Comparator;

//Sort for a comparative Manufacturer
public class MySort implements Comparator<Manufacturer> {
	 
	    public int compare(Manufacturer m1, Manufacturer m2) {
	        if(m1.getDescription().compareToIgnoreCase(m2.getDescription()) > 0){
	            return 1;
	        } else {
	            return -1;
	        }
	    }
}
