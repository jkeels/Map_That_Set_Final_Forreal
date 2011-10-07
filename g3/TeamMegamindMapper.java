package mapthatset.g3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import mapthatset.sim.*;

public class TeamMegamindMapper extends Mapper {

	int intMappingLength;
	String strID = "MegamindMapper";
	
	private ArrayList< Integer > getNewMapping() {
		
		
		ArrayList< Integer > alNewMapping = new ArrayList< Integer >();
		Random rdmGenerator = new Random();
		int randomChoice = rdmGenerator.nextInt(2); //creates a random choice of either 0 or 1
		
		switch(randomChoice) {
		
		case 0:
			System.out.print("All unique map\n");
			for ( int intIndex = 0; intIndex < intMappingLength; intIndex ++ ) {
				alNewMapping.add( intIndex+1 );
				Collections.shuffle(alNewMapping);
			}
			System.out.println( "The mapping is: " + alNewMapping );
			break;
		case 1:
			System.out.print("Majority Duplicates\n");
			for ( int intIndex = 0; intIndex < intMappingLength; intIndex ++ ) {
				alNewMapping.add( rdmGenerator.nextInt( intMappingLength ) + 1 );
			}
			System.out.println( "The mapping is: " + alNewMapping );
			break;
		}

		return alNewMapping;
	}

	@Override
	public void updateGuesserAction(GuesserAction gsaGA) {
		// dumb mapper do nothing here
	}

	@Override
	public ArrayList<Integer> startNewMapping(int intMappingLength) {
		// TODO Auto-generated method stub
		this.intMappingLength = intMappingLength;
		return getNewMapping();
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return strID;
	}
}
