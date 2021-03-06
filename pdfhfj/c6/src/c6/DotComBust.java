package c6;

import java.util.ArrayList;

public class DotComBust {
	private GameHelper helper=new GameHelper();
	private ArrayList<DotCom> dotcomsList=new ArrayList<DotCom>();
	private int numOfGuesses=0;
	
	private void setUpGame() {
		DotCom one=new DotCom();
		one.setName("Pets.com");
		DotCom two=new DotCom();
		two.setName("eToys.com");
		DotCom three=new DotCom();
		three.setName("Go2.com");
		dotcomsList.add(one);
		dotcomsList.add(two);
		dotcomsList.add(three);
		
		System.out.println("You goal is to sink three Dot com.");
		System.out.println("Pets.com,eToys.com,Go2.com");
		System.out.println("Try to sink them all in the fewest numbers of guesses");
		
		for(DotCom dotComToSet:dotcomsList) {
			ArrayList<String> newLocation=helper.placeDotCom(3);
			dotComToSet.setLocationCells(newLocation);
		}
	}
		
	     private void startPlaying() {
			while(!dotcomsList.isEmpty()) {
				String userGuess=helper.getUserInput("enter a guess");
				checkUserGuess(userGuess);
				
			}
			finishGame();
		}


	private void checkUserGuess(String userGuess) {
		numOfGuesses++;
		String result="miss";
		for(DotCom dotComToTest:dotcomsList) {
			result=dotComToTest.checkYourself(userGuess);
			if(result.equals("hit")) {
				break;
			}
			if(result.equals("kill")) {
				dotcomsList.remove(dotComToTest);
				break;
			}
		}
		System.out.print(result);
			
	}
	
	private void finishGame() {
		System.out.print("All Dot Coms all dead.Your Stuck now is worthless");
		if(numOfGuesses<=18) {
			System.out.print("It only took you"+numOfGuesses+"guesses.");
			System.out.print("You got out before yuor options sank");
		}else {
			System.out.print("Took you long enough."+numOfGuesses+"of guesses");
			System.out.print("Fish are dancing with your enough");
		}
		
	}
	
	public static void main(String args[]) {
		DotComBust game=new DotComBust();
		game.setUpGame();
		game.startPlaying();
	}
}
