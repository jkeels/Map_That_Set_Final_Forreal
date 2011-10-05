package mapthatset.g3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Collections;

import mapthatset.sim.Guesser;
import mapthatset.sim.GuesserAction;

//if you can see this comment, you have the latest version

public class TeamMegamindGuesser extends Guesser {

	int sizeOfMapping;
	int lastQueryIndex = 0;
	ArrayList<ArrayList<Integer>> startingPairGuesses = new ArrayList<ArrayList<Integer>>();
	String strID = "Team Megamind";
	// possibilityTable
	// unknown value is -1, false is 0, true is 1
	ArrayList<ArrayList<Integer>> possibilityTable = new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> lastQuery = new ArrayList<Integer>();
	ArrayList<Integer> lastResponse = new ArrayList<Integer>();
	ArrayList<Integer> alGuess = new ArrayList<Integer>();

	@Override
	public void startNewMapping(int intMappingLength) {
				
		// initialize the possibilityTable to -1 (unknown everywhere)
		for (int i = 0; i < intMappingLength; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < intMappingLength; j++) {
				row.add(-1);
			}
			possibilityTable.add(row);
		}
		
		sizeOfMapping = intMappingLength;
		lastQueryIndex = 0;
		startingPairGuesses = new ArrayList<ArrayList<Integer>>();
		alGuess = new ArrayList< Integer >();
		fillStartingPairGuesses();
	}
	
	public void fillStartingPairGuesses() {
		ArrayList<Integer>randomList = new ArrayList<Integer>();
		//fill list with 1-n
		for(int i = 1; i <= sizeOfMapping; i ++)
			randomList.add(i);
		//randomize n values
		Collections.shuffle(randomList);
		ArrayList<Integer>pair = new ArrayList<Integer>();
		startingPairGuesses = new ArrayList<ArrayList<Integer>>();
		//insert random guesses 
		int i = 1;
		while(i <= sizeOfMapping) {
			pair = new ArrayList<Integer>();
			for(int j = 0; j < 2; j++) {
				pair.add(randomList.get(i-1));
				if(i == sizeOfMapping) //in case of odd size
					j = 3;
				i++;
			}
			System.out.print("pair: " + pair.size() + "\n");
			//add the set to the arraylist
			startingPairGuesses.add(pair);
		}
	}

	@Override
	public GuesserAction nextAction() {
		//guess initial starting pairs
		lastQueryIndex ++;
		GuesserAction gscReturn = null;
		if (lastQueryIndex-1 >= startingPairGuesses.size())
		{
			String strGuessing = "";
			//for ( int intGuessingElement : alGuess )
			for (int intGuessingElement : alGuess )
			{
					strGuessing += intGuessingElement + ", ";
			}
			//System.out.println( "Guessing: " + strGuessing.substring( 0, strGuessing.length() - 1 ) );
			gscReturn = new GuesserAction( "g", alGuess );
		}
		else
		{
			ArrayList< Integer > alQueryContent = new ArrayList< Integer >();
			alQueryContent = startingPairGuesses.get(lastQueryIndex-1);
			gscReturn = new GuesserAction( "q", alQueryContent);
		}
		return gscReturn;
	}

	@Override
	public void setResult(ArrayList<Integer> alResult) {
		
		lastResponse.clear();
		for (int i = 0; i < alResult.size(); i++) {
			lastResponse.add(alResult.get(i));
		}
		// TODO Auto-generated method stub
		for(int i = 0; i < alResult.size(); i++)
			alGuess.add( alResult.get( i ) );

	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return strID;
	}

	private void updatePossibilityTable() {
		
		// for every number X queried, update possibilityTable with '1's
		// at every location in row X, place a '1' in the columns that do not appear in lastResponse
		for (int i = 0; i < lastQuery.size(); i++) {
			int row = lastQuery.get(i);
			for (int j = 0; j < sizeOfMapping; j++){
				if (!lastResponse.contains(j)) possibilityTable.get(row).set(j, 0);
			}
		}
	}
	
	private void printPossibilityTable() {
		
		for (int row = 0; row < sizeOfMapping; row++) {
			for (int col = 0; col < sizeOfMapping; col++) {
				System.out.print(possibilityTable.get(row).get(col) + " ");
			}
			System.out.println();
		}
	}
	
}
