package spell;

import java.io.*;

/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws ISpellCorrector.NoSimilarWordFound, IOException {

		//read in dictionary
		// USAGE: inputfile1 inputfile2(for comparison) word
		String dictionaryFileName = args[0];
		String dictionaryFileName2 = args[1];
		String inputWord = args[3];

		/**
		 * Create an instance of your corrector here
		 */

		// Make two spellCorrectors to compare
		SpellCorrector corrector = new SpellCorrector();
		SpellCorrector corrector2 = new SpellCorrector();

		System.out.println("Reading in Dictionaries"); // Output to show steps
		corrector.useDictionary(dictionaryFileName);
		corrector2.useDictionary(dictionaryFileName2);

		// Get both tries for comparison's sake.
		ITrie myTrie = corrector.getMyTrie();
		ITrie myTrie2 = corrector2.getMyTrie();

		//Test Equals functions
		System.out.println("Testing Equals");
		if (myTrie.equals(myTrie2))
		{
			System.out.println("Tries are equal"); // output for testing
		}
		else
		{
			System.out.println("Tries are NOT equal");
		}

		// Test toString() function,
		// Print to file
		File myFile = new File(args[2]);
		FileWriter fw = new FileWriter(myFile);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);

		pw.println(myTrie2.toString());

		// Compare WordCounts & NodeCounts
		System.out.println("Node Count = " + myTrie.getNodeCount());
		System.out.println("Word Count = " + myTrie.getWordCount());

		System.out.println("Node Count = " + myTrie2.getNodeCount());
		System.out.println("Word Count = " + myTrie2.getWordCount());


		TrieNode myNode = (TrieNode) myTrie.find(inputWord);

		if (myNode == null)
		{
			System.out.println("find doesn't work!!");
		}
		else
		{
			System.out.println("Hooray!! Find Works");
		}



		String suggestion = corrector.suggestSimilarWord(inputWord);

		System.out.println("Suggestion is: " + suggestion);

		pw.close();

	}

}
