import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class Hangman {
	private int wins;
	private int losses;
	private String currentWord;
	private Dictionary dictionary;
	
	public Hangman() {
		String fileName = "C:\\Users\\dalton kilner\\eclipse-workspace\\Hangman_DAK\\CS 131\\Dictionary.txt";
		 dictionary = new Dictionary(fileName); 
		 wins = 0;
		 losses = 0;
	}//close constructor
	
	private void loadWL() {
		String fileName = "C:\\Users\\dalton kilner\\eclipse-workspace\\Hangman_DAK\\CS 131\\winLoss.txt";
		String[] values = new String[] {"0","0"};
		try {
		      File file = new File(fileName);
		      Scanner fileReader = new Scanner(file);
		      while (fileReader.hasNextLine()) {
		        String data = fileReader.nextLine();
		        values = data.split(",");
		      }
		      fileReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("File not found");
		    }
		wins += Integer.parseInt(values[0]); 
		losses += Integer.parseInt(values[1]); 
	}
	private void writeWL() {
		String fileName = "C:\\Users\\dalton kilner\\eclipse-workspace\\Hangman_DAK\\CS 131\\winLoss.txt";
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			fileWriter.write(wins + "," + losses );
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("File write error");
		}
	
	
	}
	public void playGame() {
		Scanner inputReader = new Scanner(System.in);
		String input;
		System.out.print("Would you like to play hangman? ");
		input = inputReader.next();
		while (input.toUpperCase().compareTo("Y")== 0) {
			int guesses = 5;
			String currentWord = dictionary.chooseWord();
			String[] lettersGuessed = new String[currentWord.length()]; 
			boolean hasWon = false;
			for (int i = 0; i < lettersGuessed.length; i++) {
				lettersGuessed[i] = "_";
			}
			while(guesses > 0 && !hasWon) {
			

				boolean correctGuess = false;
				System.out.println("you have " + guesses + " incorrect guesses left.");
				for  (int i = 0; i < lettersGuessed.length; i++) {
					System.out.print(lettersGuessed[i] + " "); 
				}
				System.out.println("");
				System.out.print("What is your guess? ");
				char guess = inputReader.next().charAt(0);
				for (int i = 0; i < currentWord.length(); i++) {
					if (Character.compare(guess,currentWord.charAt(i))==0) {
						lettersGuessed[i] = String.valueOf(guess);
						correctGuess = true;
					}
				}
				if (!correctGuess) {
					guesses--;
				}
				String wordGuessed = "";
				 for (int i = 0; i < lettersGuessed.length; i++) {
					 wordGuessed += lettersGuessed[i];
				 }
				 if (wordGuessed.compareTo(currentWord)==0) {
					 hasWon = true;
					 System.out.println();
					 for  (int i = 0; i < lettersGuessed.length; i++) {
							System.out.print(lettersGuessed[i] + " "); 
						}
					 System.out.println();
					 System.out.println("You won!");
					 wins++;
				 }
				 if (guesses <= 0) {
					 losses++;
					 System.out.println();
					 for  (int i = 0; i < lettersGuessed.length; i++) {
							System.out.print(lettersGuessed[i] + " "); 
						}
					 System.out.println();
					 System.out.println("You lost");
				 }
			}
			System.out.println("Would you like to play again Y/N? ");
			input = inputReader.next();
		}
		
		System.out.println("You had " + wins + " win(s) and " + losses + " losses this round.");
		loadWL();
		System.out.println("You have a total of " + wins + " win(s) and " + losses + " losses all time.");
		writeWL();
	
	}
}// close class
