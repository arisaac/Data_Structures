public class PrefixTreeNode {
	public PrefixTreeNode[] children;
	public boolean isWord = false;

	public PrefixTreeNode() {
		this.children = new PrefixTreeNode[26];
	}

	//add child nodes to children array
	public PrefixTreeNode addChild(char child) {
		//offset to see how far into the array to get character in children array and then add it
		int offset = child - 'a';
		//create a new prefixTree Node for every child
		if (this.children[offset] == null) {
			this.children[offset] = new PrefixTreeNode();
		}
		return this.children[offset];
	}
	//the word is truely in the dictionary
	public boolean isWord() {
		return isWord;
	}
    
	//setting the flag of the word
	public void setWord(boolean word) {
		this.isWord = word;
	}
	
	//getting the Prefix
	public PrefixTreeNode get(char c) {
		//offset to see how far in children array to get the character
		int offset = c - 'a';
		return this.children[offset];
	}
}
