import java.util.Random;
import java.util.Scanner;
//Jason Lin & Tyler Taylor

public class Hangman extends Game{
	
	protected final static int wrong = 7;
	protected char letter;
	protected int guess;
	protected char [] guessed;
	protected char [] state;
	protected String answer;
	protected String state2;
	protected String [] words = 
		{"geography", "cat", "today", "java", 
		"truck", "opportunity",  "fish", "token", 
		"transportation", "bottom", "apple", "cake",  
		"remote", "pocket", "terminology", "arm", "cranberry", 
		"implement",  "caterpillar", "fork", "watermelon", 
		"laptop", "toe", "toad",  "fundamental", "capitol", 
		"garbage", "anticipate", "apple"};
	
	public Hangman() {
		guess=0;
		guessed=new char[1];
		state = new char[1];
		answer = "";
	}
	
	protected void initialize() {
		System.out.printf("\n\tWelcome to Hangman!\n\n\tGuess The Word: ");
		
		Random rnd = new Random();
		//Random word from list
		int random = rnd.nextInt(words.length-1);
		answer = words[random];
		//Initialize wordstate
		state = new char[answer.length()];
		//Initialize guessed(number guess+word length)
		guessed = new char[wrong+state.length];
		guess = wrong;//Gets number of attempt, 0 is none left
	}
	
	protected void loadContent() {
		//Print wordstate
		for(int i = 0; i<state.length; i++) {
			state[i]= '_';
			System.out.print(state[i]+" ");
			
		}
		System.out.printf("\n\tWord is: %x char long\n", state.length);
		
	}
	
	
	protected void draw() {
		System.out.printf("\n\t");
		for(int i = 0; i<state.length; i++) {
			System.out.print(state[i]+" ");
		}
		System.out.printf("\n\tGuess remaining: %x/%x\n",guess,wrong);
		
	}
	
	protected void userAction() {
		System.out.printf("\tEnter your guess: ");
		Scanner in = new Scanner(System.in);
		
		//Check for duplicates
		boolean loop = true;
		while(loop) {
			loop=false;
			letter = in.next().charAt(0);
			for(int i =0; i < guessed.length; i++) {
				if (guessed[i]==letter) {
					loop=true;
					System.out.print("\n\tAlready been guessed. Guess another.\n");
					draw();
				}
			}
			//Add guess if not duplicate
			if (!loop) {
				for(int i =0; i < guessed.length; i++) {
					if (guessed[i]==0) {
						guessed[i]=letter;
						break;
					}
				}
			}
		}
		
		//Check letter & update state
		boolean update = true;
		for(int i =0; i < answer.length(); i++) {
			if(letter==answer.charAt(i)) {
				state[i]=letter;
				update = false;
				
			}
		}
		state2 = String.valueOf(state);
		//decrease guess if wrong
		if(update)guess--;
	
	}
	
	protected boolean evaluateState() {
		//TODO: Fix the winning state
		
		boolean win = false;
		
		for(int i =0; i < answer.length(); i++) {
			
			if(state2.equals(answer)) {
				win = true;
				break;
			}	
			else if (answer.charAt(i)==0) {
				win = false;
				break;
			}
			
			
		}
		
		if(guess==0) {
			System.out.printf("\n\t\t <<You were hanged>>");
			return false;
		}
		else if (win == true) {
			System.out.printf("\n\t\t<<Winner Winner Chicken Dinner>>\n");
			return false;
		}
		return true;
	}
	
	protected void report() {
		System.out.printf("\n\tThe word is: ");
		for(int i =0; i < answer.length(); i++) {
			System.out.print(answer.charAt(i));
		}
	}
}
