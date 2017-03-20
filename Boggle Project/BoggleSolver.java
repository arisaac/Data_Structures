import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.TreeSet;



public class BoggleSolver {


	private static BufferedReader in = null;
	private static final String INPUT = "dictionary.txt";
	Set<String> foundWords = new TreeSet<String>();
	
	//reading in the dictionary file return error if file not found
	public static void beginFileReader() {
		try {
			in = new BufferedReader(new FileReader(new File(BoggleSolver.class
					.getResource(INPUT).toURI())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public PrefixTree buildPrefixTree() {
		PrefixTree tree = new PrefixTree();
		beginFileReader();
		String line;
		try {
			//building the Prefix tree
			while ((line = in.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					word = word.trim().toLowerCase();
					tree.addWord(word);
				}
			}
			if (in != null) {
				in.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return tree;
	}
	
	//finding words using DFS approach also a helper function for DFS
	public Set<String> findWords(char[][] BoggleBoard, PrefixTree dictionary) {
		boolean[][] visited = new boolean[BoggleBoard.length][BoggleBoard[0].length];
		for (int i = 0; i < BoggleBoard.length; i++) {
			for (int j = 0; j < BoggleBoard[0].length; j++) {
				DFS(dictionary, BoggleBoard, visited, new BoggleTiles(i, j, ""));
			}
		}
		return foundWords;
	}
	
	public void DFS(PrefixTree dictionary, char[][] BoggleBoard,boolean[][] visited, BoggleTiles Tile) {
		//the row and column the tile is on
		int x = Tile.row;
		int y = Tile.column;
		String word;
		String QU = "qu";
		if (visited[x][y]) {
			return;
		}
		// if statement to handle the character q
		if(BoggleBoard[x][y] == 'q'){
			word = Tile.prefix + QU;
		}
		//adding the char to the word as it goes through the boggle board
		else{
		word = Tile.prefix + BoggleBoard[x][y];
		}
		//see if the word is in the dictionary
		PrefixTreeNode findWord = dictionary.match(word);
		if (findWord == null) {
			return;
		}
		//adding word to list of words that has been found
		if (findWord.isWord()) {
			foundWords.add(word);
		}
		
		visited[x][y] = true;
		//do DFS at every possible point on the board
		if (x > 0) {
			DFS(dictionary,BoggleBoard,visited,new BoggleTiles( x - 1, y,word));
			if (y > 0) {
				DFS(dictionary, BoggleBoard, visited, new BoggleTiles(x - 1, y - 1, word));
			}
			
			if (y < BoggleBoard[0].length - 1) {
				DFS(dictionary, BoggleBoard, visited, new  BoggleTiles(x - 1, y + 1, word));
			}
		}
		
		if (y > 0) {
			DFS(dictionary, BoggleBoard, visited, new BoggleTiles(x, y - 1, word));
		}
		
		if (y < BoggleBoard[0].length - 1) {
			DFS(dictionary, BoggleBoard, visited, new BoggleTiles(x, y + 1, word));
		}
		
		if (x < BoggleBoard.length - 1) {
			if (y > 0) {
				DFS(dictionary, BoggleBoard, visited, new BoggleTiles(x +1, y - 1, word));
			}
			if (y < BoggleBoard[0].length - 1) {
				DFS(dictionary, BoggleBoard, visited, new BoggleTiles(x + 1, y + 1, word));
			}
			DFS(dictionary, BoggleBoard, visited, new BoggleTiles(x + 1, y, word));
		}
		
		visited[x][y] = false;
	}
}

