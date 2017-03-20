import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanner = new Scanner(System.in);
		String firstLine = scanner.nextLine();
		String[] setup = firstLine.split(",");
		int endTime = Integer.parseInt(setup[0]);
		int numOfClasses = Integer.parseInt(setup[1]);
		Class[] classes = new Class[numOfClasses];
		for(int i = 0; i < numOfClasses; i++){
			//setting up the class object
			String ClassAndTimeString = scanner.nextLine(); 
			String[] ClassAndTimeArray = ClassAndTimeString.split(",");
			Class ClassAndTimes = new Class(ClassAndTimeArray[0], Integer.parseInt(ClassAndTimeArray[1]), Integer.parseInt(ClassAndTimeArray[2]));
			//adding the class to the Array of classes
			classes[i] = ClassAndTimes;
		}
		scanner.close();
		//sort the classes by finish times in ascending order
		QuickSort(classes, classes.length);
		//find the optimal schedule
		SelectionOfClasses(classes);
	}
	
	//Sorting algorithm
	public static Class[] QuickSort(Class[] A, int length){
		if(length <= 1) return A;
		long pivot = median(A[0].FinishTime,A[(length - 1)/2].FinishTime,A[length - 1].FinishTime);
		Class[] Less = new Class[length];
		Class[] Equal = new Class[length];
		Class[] Greater = new Class[length];
		int countless = 0;
		int countequal = 0;
		int countGreater = 0;
		for(int x = 0; x < length; x++){
			if(A[x].FinishTime < pivot){
				Less[countless] = A[x];
				countless++;

			}
			else if(A[x].FinishTime == pivot){
				Equal[countequal] = A[x];
				countequal++;

			}
			else{
				Greater[countGreater] = A[x];
				countGreater++;

			}

		}
		QuickSort(Less,countless);
		QuickSort(Greater,countGreater);
		for(int i = 0; i < countless; ++i){
			A[i] = Less[i];

		}
		for(int i = 0; i < countequal; ++i){
			A[i + countless] = Equal[i];

		}
		for(int i = 0; i < countGreater; ++i){
			A[i + countless + countequal] = Greater[i];

		}
		return A;
	}
	
	public static int median(int x, int y, int z){
		if(x > y){
			if(y > z)return y;
			else if(x > z)return z;
			else return x;
			
		}
		else{
			if(x > z)return x;
			else if(y > z)return z;
			else return y;
			
		}
	}
	
	//Greedy algorithm to find the optimal set of classes
	public static void SelectionOfClasses(Class[] A){
		int n = A.length;
		int i = 0;
		System.out.println("Schedule");
		//first class always get printed out first
		System.out.println(A[i].ClassName + " from " + A[i].StartTime + " to " + A[i].FinishTime);
		for (int j = 1; j < n; j++){
			//print out the classes that do not have any conflicts
			if (A[j].StartTime >= A[i].FinishTime){
				System.out.println(A[j].ClassName + " from " + A[j].StartTime + " to " + A[j].FinishTime);
				i = j;
			}
		}
	}
}
