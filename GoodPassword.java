import java.io.File;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GoodPassword {
	public ArrayList<String> goodPass = new ArrayList<String>();
	DLB trie = new DLB();
	File gp = new File("good_passwords.txt");

	// this function is used to generate a text file which stores all the good
	// passwords
	public void outputPass() {
		String triefileName = "My_dictionary.txt";
		trie.buildTrie(triefileName);
		listPass();
	}

	// this function is used to find all the good passwords
	public void listPass() {
		findPass("", 5, 3, 2, 2);
	}

	private void findPass(String prefix, int k, int avlet, int avnum, int avsym) {
		String letter = "abcdefghijklmnopqrstuvwxyz";
		String number = "1234567890";
		String symbol = "!@$%&*";

		int in = 0;
		for (int i = 0; i < prefix.length(); i++) {
			String sub = prefix.substring(i);
			boolean inTrie = trie.search(sub);
			if (inTrie) {
				in++;
			}
		}
		if (in == 0) {
			// Base case: k is 0, print prefix
			if (k == 0) {
				if (avlet < 3 && avnum < 2 && avsym < 2) {
					try {
						String filename = "good_passwords.txt";
						FileWriter fw = new FileWriter(filename, true);
						fw.write(prefix + "\n");
						fw.close();
					} catch (IOException ioe) {
						System.err.println("IOException: " + ioe.getMessage());
					}
				}
			}

			// pruning by check the number of letters/numbers/symbols
			if (avlet > 0) {
				for (int i = 0; i < letter.length(); i++) {
					String newPrefix = prefix + letter.charAt(i);
					findPass(newPrefix, k - 1, avlet - 1, avnum, avsym);
				}
			}
			if (avnum > 0) {
				for (int i = 0; i < number.length(); i++) {
					String newPrefix = prefix + number.charAt(i);
					findPass(newPrefix, k - 1, avlet, avnum - 1, avsym);
				}
			}
			if (avsym > 0) {
				for (int i = 0; i < symbol.length(); i++) {
					String newPrefix = prefix + symbol.charAt(i);
					findPass(newPrefix, k - 1, avlet, avnum, avsym - 1);
				}
			}
		}
	}
}
