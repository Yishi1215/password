import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
	public ArrayList<String> ln = new ArrayList<String>();

	// this function is used to generate a text file and put the arraylist words
	// into the file by one word per line
	public void output(ArrayList<String> arraylist, String fileName) {

		try {
			FileWriter writer = new FileWriter(fileName, true);
			writer.write("");
			for (String word : arraylist) {
				writer.write(word + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// this function is used to read text file into arraylist
	public ArrayList<String> readLineByLine(String fileName) {
		FileReader fr;
		try {
			fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			try {
				while ((line = br.readLine()) != null) {
					ln.add(line);
				}
				br.close();
				fr.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Unable to find data file");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to find data file");
		}
		return ln;
	}

}
