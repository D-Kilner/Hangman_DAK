import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.Scanner;
public class Dictionary {
	private String[] wordList;
	private int currentWord;
	private SecureRandom randomNumbers;
	
	public Dictionary(String fileName) {
		wordList = new String[200];
		readFile(fileName);
	}
	
	private void readFile(String fileName) {
		try {
		      File file = new File(fileName);
		      Scanner fileReader = new Scanner(file);
		      int i = 0;
		      while (fileReader.hasNextLine()) {
		        String data = fileReader.nextLine();
		        wordList[i] = data.toString();
		        i++;
		      }
		      fileReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("File not found");
		    }
	}
	
	public String chooseWord() {
		randomNumbers = new SecureRandom();
		currentWord= randomNumbers.nextInt(wordList.length - 1);
		return wordList[currentWord] ; 
	}
	
}// close class
