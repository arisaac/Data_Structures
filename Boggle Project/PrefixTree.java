public class PrefixTree {
	public PrefixTreeNode root;

	public PrefixTree() {
		this.root = new PrefixTreeNode();
	}
	
	//adds the word
	public void addWord(String x) {
		//System.out.println(x);
		PrefixTreeNode node = this.root;
		//loop through the string by each character to see if it is a valid word 
		for(int i = 0; i < x.length(); i++){
			char c = x.charAt(i);
			node = node.addChild(c);
			if(node == null)
				return;
		}

		//set the word to true if found in the dictionary
		node.setWord(true);
	}

	//matching the word by character
	public PrefixTreeNode match(String s) {
		PrefixTreeNode node = this.root;
		//loop through the string to get the match of every character of the word in dictionary
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			node = node.get(c);
			if(node == null)
				return null;
		}
		return node;
	}
}
