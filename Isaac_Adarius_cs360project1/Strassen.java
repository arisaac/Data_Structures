import java.util.Scanner;

public class Strassen{
	public int countadd;
	public int countmult;
	
	public int getCountAdd(){
		return countadd;
	}
	
	public int getCountmult(){
		return countmult;
	}
	public int[][] multiply(int[][] A, int[][] B){        
		countmult++;
		int n = A.length;
		int[][] C = new int[n][n];
		if(n == 1){
			C[0][0] = A[0][0] * B[0][0];
		}
		else{
			int newsize = n/2;

			// create the sub-matrices of A and B
			int[][] a11 = new int[newsize][newsize];
			int[][] a12 = new int[newsize][newsize];
			int[][] a21 = new int[newsize][newsize];
			int[][] a22 = new int[newsize][newsize];
			int[][] b11 = new int[newsize][newsize];
			int[][] b12 = new int[newsize][newsize];
			int[][] b21 = new int[newsize][newsize];
			int[][] b22 = new int[newsize][newsize];

			//Dividing matrix A and B into 4 using the sub-matrices
			for (int i = 0; i < newsize; i++) {
				for (int j = 0; j < newsize; j++) {
					a11[i][j] = A[i][j]; // top left of A
					a12[i][j] = A[i][j + newsize]; // top right of A
					a21[i][j] = A[i + newsize][j]; // bottom left of A
					a22[i][j] = A[i + newsize][j + newsize]; // bottom right of A

					b11[i][j] = B[i][j]; // top left of B
					b12[i][j] = B[i][j + newsize]; // top right of B
					b21[i][j] = B[i + newsize][j]; // bottom left of B
					b22[i][j] = B[i + newsize][j + newsize]; // bottom right of B
				}
			}
			
			//recursive multiplication calls of Strassen algorithm
			int [][] M1 = multiply(add(a11, a22), add(b11, b22));
			int [][] M2 = multiply(add(a21, a22), b11);
			int [][] M3 = multiply(a11, sub(b12, b22));
			int [][] M4 = multiply(a22, sub(b21, b11));
			int [][] M5 = multiply(add(a11, a12), b22);
			int [][] M6 = multiply(sub(a21, a11), add(b11, b12));
			int [][] M7 = multiply(sub(a12, a22), add(b21, b22));
			
			//evaluating the 4 parts of the sub-matrices of C
			int [][] C11 = add(sub(add(M1, M4), M5), M7);//top left of C
			int [][] C12 = add(M3, M5);//top right of C
			int [][] C21 = add(M2, M4);//bottom left of C
			int [][] C22 = add(sub(add(M1, M3), M2), M6);//bottom right of C        
		    
			//joining the 4 parts to the C matrices 
			for(int i = 0; i < n/2; i++)  
				for(int j = 0; j < n/2; j++)  
					C[i][j] = C11[i][j];  
			for(int i = 0; i < n/2; i++)  
				for(int j = n/2; j < n; j++)  
					C[i][j] = C12[i][j - (n/2)];  
			for(int i = n/2; i < n; i++)  
				for(int j = 0; j < n/2; j++)  
					C[i][j] = C21[i - (n/2)][j];  
			for(int i = n/2; i < n; i++)  
				for(int j = n/2; j < n; j++)  
					C[i][j] = C22[i - (n/2)][j - (n/2)]; 

		}
		//return the resulting C
		return C;
	}

	
	
	//function to subtract matrices
	public int[][] sub(int[][] A, int[][] B){
		int n = A.length;
		int[][] C = new int[n][n];
		//loop through both matrices of A and B to do the subtraction
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] - B[i][j];
		//the result is going to be C
		return C;

	}

	//function to add matrices
	public int[][] add(int[][] A, int[][] B){
		countadd++;
		int n = A.length;
		int[][] C = new int[n][n];
		//loop through both matrices of A and B to do the addition
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] + B[i][j];
		//the result is going to be C
		return C;
	}
}


