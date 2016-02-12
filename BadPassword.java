import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BadPassword {
	public ArrayList<String> words = new ArrayList<String>();
	public ArrayList<String> addWords = new ArrayList<String>();

	// this function is used to generate a text file which stores 500+ most used
	// words and their transformations
	public void outputDic() {
		ReadFile rf = new ReadFile();
		String fileName = "My_dictionary.txt";
		rf.output(replace(), fileName);
	}

	// this function is used to generate an arrayList which contains most used
	// words and their transformations
	private ArrayList<String> replace() {
		ReadFile rf = new ReadFile();
		String fileName = "dictionary.txt";
		words = rf.readLineByLine(fileName);
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		for (String word : words) {
			map.put('t', '7');
			map.put('a', '4');
			map.put('o', '0');
			map.put('e', '3');
			map.put('i', '1');
			map.put('l', '1');
			map.put('s', '5');
			for (String password : printAll(map, word, 0)) {
				addWords.add(password);
			}
		}
		return addWords;
	}

	public static ArrayList<String> printAll(HashMap<Character, Character> map, String word, int cur) {
		if (cur == word.length()) {
			ArrayList<String> ret = new ArrayList<String>();
			ret.add(word);
			return ret;
		}

		if (map.containsKey(word.charAt(cur))) {
			char[] chars = word.toCharArray();
			chars[cur] = map.get(word.charAt(cur));
			String newWord = new String(chars);
			ArrayList<String> a = printAll(map, newWord, cur + 1);
			ArrayList<String> b = printAll(map, word, cur + 1);
			a.addAll(b);
			return a;
		} else {
			return printAll(map, word, cur + 1);
		}
	}
}
