import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner file = new Scanner(new File("board.txt"));
		//getting the rows and columns size
		int row = file.nextInt();
		int col = file.nextInt();
		//creating the board
		char[][] board = new char[row][col];
		//filling in the board 
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				board[i][j] = file.next().toLowerCase().charAt(0);
			}
		}
		file.close();
		//the test
		BoggleSolver solver = new BoggleSolver();
		//build Prefix Tree
		PrefixTree dictionary = solver.buildPrefixTree();
		//find the words
		Set<String> set = solver.findWords(board, dictionary);
		//the result
		System.out.println(set.size() + " words were found, the words are: ");
		for (String str : set) {
			System.out.println(str);
		}
	}
}
