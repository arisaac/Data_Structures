
public class ManufacturersHashTable {
	int x;
	int tableSize = nextPrime(x);
	Manufacturer[] table;
	static final int k = 10; 
	static final int w = 32;
	static final int a = (int) 2654435769L;


	//multiplication method for hashing
	public static int h (int x){ 
		return (x * a) >>> (w - k); 
	}

	ManufacturersHashTable() {
		table = new Manufacturer[tableSize];
		for (int i = 0; i < tableSize; i++)
			table[i] = null;

	}


	//searching and returning Manufacturer
	public Manufacturer get(int c) {
		int hash = h(c) % tableSize;
		while (table[hash] != null && table[hash].getCode() != c){
			hash = (hash + 1) % tableSize;
		}
		if (table[hash] == null)
			return null;
		else
			return table[hash];
	}


	//linear probing
	public void add(int c, String d) {
		int hash = h(c) % tableSize;
		while (table[hash] != null && table[hash].getCode() != c)
			hash = (hash + 1) % tableSize;
		table[hash] = new Manufacturer(c, d);
	}


	//prime method to calculate the size of the table
	public int nextPrime(int x){
		while(true){
			boolean isPrime = true;
			x = x + 1;
			int sqrt = (int)Math.sqrt(x);
			for(int i = 2; i < sqrt; i++){
				if(x%i == 0){
					isPrime = false;
					break;
				}
			}
			if(isPrime){
				return x;
			}
		}
	}
}
