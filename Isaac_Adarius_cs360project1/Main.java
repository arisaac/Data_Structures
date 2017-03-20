import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;



public class Main {
	public static int n;
	public static void main (String[] args){
		//reading from the command line
		Scanner scan = new Scanner(System.in);
		Strassen s = new Strassen();
		Scanner file;
		
		//running a try and catch to read valid integer
		try{
		System.out.println("enter order n");
		String orderN = scan.next();
		 n = scan.nextInt();
		}
		catch (Exception e) {
			System.out.println("invalid integer");
			System.exit(1);
		}
		
		//running a try and catch to read the file "datafile"
		try{
			//System.out.println("enter the datafile");
			//String input = scan.next();
			file = new Scanner(new File("datafile"));
			String[] nvalue = file.nextLine().split("=");
			int N = Integer.parseInt(nvalue[1]);
			int M = nextPowerOfTwo(N);
			int[][] A = new int[M][M];
			int[][] B = new int[M][M];
			System.out.println(N);
			if(N < M){
				for(int i = 0; i < N; i++){
					String values = file.nextLine().trim();
					String[] split = values.split(",");
					for(int z = 0; z < N; z++){
						int value = Integer.parseInt(split[z]);
						A[i][z] = value;	
					}
				}
				
				for(int i = N; i < M; i++)
					for(int j = N; j < M; j++ )
						A[i][j] = 0;
					
			}
			else if(N == M){
				for(int i = 0; i < N; i++){
					String values = file.nextLine().trim();
					String[] split = values.split(",");
					for(int z = 0; z < N; z++){
						int value = Integer.parseInt(split[z]);
						A[i][z] = value;		
					}
				}
			}
			
			if(N < M){
				for(int i = 0; i < N; i++){
					String values = file.nextLine().trim();
					String[] split = values.split(",");
					for(int z = 0; z < N; z++){
						int value = Integer.parseInt(split[z]);
						B[i][z] = value;		
					}
				}
				
				for(int i = N; i < M; i++)
					for(int j = N; j < M; j++ )
						B[i][j] = 0;
					
			}
			else if(N == M){
				for(int i = 0; i < N; i++){
					String values = file.nextLine().trim();
					String[] split = values.split(",");
					for(int z = 0; z < N; z++){
						int value = Integer.parseInt(split[z]);
						B[i][z] = value;		
					}
				}
			}
			int[][] C = s.multiply(A, B);
			int countmult = s.getCountmult();
			int countadd = s.getCountAdd();
			System.out.println("\nN=" + N);
			System.out.println("\nInput matrix A : ");
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					System.out.print("   ");
					System.out.format("%2d", A[i][j] );
				}
				System.out.println();
			}
			
			System.out.println("\nInput matrix B : ");
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					System.out.print("   ");
					System.out.format("%2d" , B[i][j] );
				}
				System.out.println();
			}
			
			System.out.println("\nOutput matrix C : ");
			System.out.println();
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					System.out.print("   ");
					System.out.format("%4d" , C[i][j] );
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("Number of multiplications: " + countmult);
			System.out.println();
			System.out.println("Number of additions: " + countadd);
			file.close();
		}
		catch (IOException e) {
			System.out.println("file not found");
			System.exit(1);
		}
	}

	public static int nextPowerOfTwo(int size){
		int log2 = (int) Math.ceil(Math.log(size) / Math.log(2));
		return (int) Math.pow(2, log2);

	}

}
