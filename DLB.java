import java.util.ArrayList;
import java.util.List;

public class DLB {
	private Node head;
	public ArrayList<String> words = new ArrayList<String>();

	// this function is used to recommend ten password which share longest
	// prefix with the one user entered
	public List<String> recommendPassword(String word) {
		ArrayList<String> ret = recommendPassword(head, word, "");
		if (ret.size() > 10)
			return ret.subList(0, 10);
		return ret;
	}

	public ArrayList<String> recommendPassword(Node root, String word, String prefix) {
		if (root == null)
			return new ArrayList<String>();
		if (root.value == word.charAt(0)) {
			ArrayList<String> ret = recommendPassword(root.child, word.substring(1), prefix + root.value);
			if (ret.size() < 10) {
				// do something here
				ArrayList<String> a = getAllWords(root.rightSib, prefix);
				for (String s : a) {
					if (ret.size() >= 10)
						break;
					ret.add(s);
				}
			}
			return ret;
		} else {
			ArrayList<String> ret = recommendPassword(root.rightSib, word, prefix);
			if (ret.size() < 10) {
				ret.addAll(getAllWords(root.child, prefix + root.value));
			}
			if (ret.size() < 10 && root.isEnd) {
				ret.add(prefix + root.value);
			}
			return ret;
		}
	}

	public ArrayList<String> getAllWords(Node root, String prefix) {
		if (root == null)
			return new ArrayList<String>();
		if (root.isEnd) {
			ArrayList<String> ret = new ArrayList<String>();
			ret.add(prefix + root.value);
			ret.addAll(getAllWords(root.rightSib, prefix));
			return ret;
		}

		ArrayList<String> child = getAllWords(root.child, prefix + root.value);
		ArrayList<String> sib = getAllWords(root.rightSib, prefix);
		child.addAll(sib);
		return child;
	}

	// this function is used to build a trie when given a file full of words
	public void buildTrie(String fileName) {
		ReadFile input = new ReadFile();
		words = input.readLineByLine(fileName);
		for (String word : words) {
			insert(word);
		}
	}

	// this function is used to insert word into trie
	public void insert(String word) {
		if (head == null) {
			head = new Node(word.charAt(0));
			insert(head, word);
		} else {
			insert(head, word);
		}
	}

	private void insert(Node root, String word) {
		if (word.length() == 0) {
			return;
		}
		char c = word.charAt(0);
		if (root == null) {
			Node node = new Node(word.charAt(0));
			root = node;
			insert(root, word);
			return;
		} else if (root.value == c && word.length() == 1) {
			root.isEnd = true;
			return;
		} else {
			if (root.value == c) {
				if (root.child != null) {
					root = root.child;
					word = word.substring(1);
					insert(root, word);
					return;
				} else {
					root.child = new Node(word.charAt(1));
					root = root.child;
					word = word.substring(1);
					insert(root, word);
					return;
				}
			} else {
				if (root.rightSib != null) {
					root = root.rightSib;
					insert(root, word);
					return;
				} else {
					root.rightSib = new Node(word.charAt(0));
					if (word.length() == 1) {
						root.rightSib.isEnd = true;
						return;
					} else {
						insert(root, word);
						return;
					}
				}

			}

		}
	}

	// this function is used to search whether a word is in the trie
	public boolean search(String word) {
		return search(head, word);
	}

	private boolean search(Node root, String word) {
		if (word.length() == 0) {
			return false;
		}
		char c = word.charAt(0);
		if (root == null) {
			return false;
		}
		if (root.child == null && c == root.value && word.length() == 1) {
			return true;
		}
		if (root.rightSib == null && root.value != c) {
			return false;
		}
		if (c == root.value && root.child != null && word.length() == 1) {
			if (root.isEnd == false) {
				return false;
			} else {
				return true;
			}
		} else {
			if (root.value == c) {
				root = root.child;
				word = word.substring(1);
				return search(root, word);
			} else {
				root = root.rightSib;
				return search(root, word);
			}
		}
	}

	// this function is used for test trie building
	public void printTrie() {
		printTrieHelper(head, "");
	}

	public void printTrieHelper(Node cur, String prefix) {
		if (cur == null)
			return;
		if (cur.isEnd)
			System.out.println(prefix + cur.value);
		printTrieHelper(cur.child, prefix + cur.value);
		printTrieHelper(cur.rightSib, prefix);
	}
}
