package spell;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class SpellCorrector implements ISpellCorrector {

    Trie myTrie;

    public void useDictionary(String dictionaryFileName) throws IOException {
        try {
            File file = new File(dictionaryFileName);
            FileReader fr = new FileReader(file);
            Scanner input = new Scanner(fr);

            // store the initial value
            myTrie = new Trie();




            while (input.hasNext()) {
                String line = input.next();
                myTrie.add(line);
            }
            input.close();
            fr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFound {
        inputWord = inputWord.toLowerCase();

        if (myTrie.find(inputWord) != null) {
            return inputWord;
        }

        TreeSet<Word> similarWords = new TreeSet<>(); // this set will store possible similar words

        similarWords = editDistanceOne(inputWord);

        if (similarWords.isEmpty()) {
            similarWords = editDistanceTwo(inputWord);
        }

        if (similarWords.isEmpty()) {
            throw new NoSimilarWordFound();
        }

        Word highestFrequency = similarWords.first();
        for (Word i: similarWords) {
            if (i.getFrequency() > highestFrequency.getFrequency()) {
                highestFrequency = i;
            }
        }

        return highestFrequency.getMyWord();

    }

    public TreeSet<Word> editDistanceOne(String inputWord) {
        TreeSet<Word> similarWords = new TreeSet<>();
        // Deletion
        for (int i = 0; i< inputWord.length(); i++) {
            StringBuilder wordDeletion = new StringBuilder(inputWord);
            wordDeletion.deleteCharAt(i);
            if (myTrie.find(wordDeletion.toString()) != null) {
                int frequency = myTrie.find(wordDeletion.toString()).getValue();
                Word myWord = new Word(wordDeletion.toString(), frequency);
                similarWords.add(myWord);
            }
        }

        // Transposition

        for (int i = 0; i < inputWord.length() - 1; i++) {
            StringBuilder wordTransposed = new StringBuilder(inputWord);
            char first = inputWord.charAt(i);
            char second = inputWord.charAt(i + 1);

            // switch adjacent chars
            wordTransposed.setCharAt(i,second);
            wordTransposed.setCharAt(i + 1, first);
            if (myTrie.find(wordTransposed.toString()) != null) {
                int frequency = myTrie.find(wordTransposed.toString()).getValue();
                Word myWord = new Word(wordTransposed.toString(), frequency);
                similarWords.add(myWord);
            }
        }

        // Alteration

        for (int i = 0; i < inputWord.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                StringBuilder wordAlteration = new StringBuilder(inputWord);
                wordAlteration.setCharAt(i, c);
                if (myTrie.find(wordAlteration.toString()) != null) {
                    int frequency = myTrie.find(wordAlteration.toString()).getValue();
                    Word myWord = new Word(wordAlteration.toString(), frequency);
                    similarWords.add(myWord);
                }
            }
        }

        // Insertion
            for (int i = 0; i <= inputWord.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    StringBuilder wordInsertion = new StringBuilder(inputWord);
                    wordInsertion.insert(i, c);
                    if (myTrie.find(wordInsertion.toString()) != null) {
                        int frequency = myTrie.find(wordInsertion.toString()).getValue();
                        Word myWord = new Word(wordInsertion.toString(), frequency);
                        similarWords.add(myWord);
                    }
                }
            }

        return similarWords;


    }

    public TreeSet<Word> editDistanceTwo(String inputWord)
    {
        TreeSet<String> d1Words = editD1forD2(inputWord);
        TreeSet<Word> similarWords = new TreeSet<Word>();

        // Use the set of generated Words

        for (String str : d1Words)
        {
            // Deletion
            for (int i = 0; i < str.length(); i++)
            {
                StringBuilder wordDel = new StringBuilder(str);
                wordDel.deleteCharAt(i);
                if (myTrie.find(wordDel.toString()) != null)
                {
                    int frequency = myTrie.find(wordDel.toString()).getValue();
                    Word myWord = new Word(wordDel.toString(), frequency);
                    similarWords.add(myWord);
                }
            }

            // Transposition
            for (int i = 0; i < str.length() - 1; i++)
            {
                StringBuilder wordTrans = new StringBuilder(str);
                char first = str.charAt(i);
                char second = str.charAt(i + 1);

                wordTrans.setCharAt(i, second);
                wordTrans.setCharAt(i + 1, first);

                if (myTrie.find(wordTrans.toString()) != null)
                {
                    int frequency = myTrie.find(wordTrans.toString()).getValue();
                    Word myWord = new Word(wordTrans.toString(), frequency);
                    similarWords.add(myWord);
                }
            }

            // Alteration
            for (int i = 0; i < str.length(); i++)
            {
                for (char c = 'a'; c <= 'z'; c++)
                {
                    StringBuilder wordAlt = new StringBuilder(str);
                    wordAlt.setCharAt(i, c);
                    if (myTrie.find(wordAlt.toString()) != null)
                    {
                        int frequency = myTrie.find(wordAlt.toString()).getValue();
                        Word myWord = new Word(wordAlt.toString(), frequency);
                        similarWords.add(myWord);
                    }
                }
            }

            // Insertion
            for (int i = 0; i <= str.length(); i++)
            {
                for (char c = 'a'; c <= 'z'; c++)
                {
                    StringBuilder wordIns = new StringBuilder(str);
                    wordIns.insert(i, c);
                    if (myTrie.find(wordIns.toString()) != null)
                    {
                        int frequency = myTrie.find(wordIns.toString()).getValue();
                        Word myWord = new Word(wordIns.toString(), frequency);
                        similarWords.add(myWord);
                    }
                }
            }
        }

        return similarWords;
    }


    public TreeSet<String> editD1forD2(String inputWord)
    {
        TreeSet<String> similarWords = new TreeSet<String>();

 		/* 	The difference in this function is that
 		 * we don't search the Trie for the given words
 		 * because we already know that they DON'T EXIST.
 		 * 	Instead, we just add the word to a Set, and
 		 * use the set for the edit distances a second time.
 		 */

        // Deletion
        for (int i = 0; i < inputWord.length(); i++)
        {
            StringBuilder wordDel = new StringBuilder(inputWord);
            wordDel.deleteCharAt(i);
            String myStr = new String(wordDel.toString());
            similarWords.add(myStr);
        }

        // Transposition
        for (int i = 0; i < inputWord.length() - 1; i++)
        {
            StringBuilder wordTrans = new StringBuilder(inputWord);
            char first = inputWord.charAt(i);
            char second = inputWord.charAt(i + 1);

            wordTrans.setCharAt(i, second);
            wordTrans.setCharAt(i + 1, first);

            String myStr = new String(wordTrans.toString());
            similarWords.add(myStr);
        }

        // Alteration
        for (int i = 0; i < inputWord.length(); i++)
        {
            for (char c = 'a'; c <= 'z'; c++)
            {
                StringBuilder wordAlt = new StringBuilder(inputWord);
                wordAlt.setCharAt(i, c);

                String myStr = new String(wordAlt.toString());
                similarWords.add(myStr);
            }
        }

        // Insertion
        for (int i = 0; i <= inputWord.length(); i++)
        {
            for (char c = 'a'; c <= 'z'; c++)
            {
                StringBuilder wordIns = new StringBuilder(inputWord);
                wordIns.insert(i, c);

                String myStr = new String(wordIns.toString());
                similarWords.add(myStr);
            }
        }


        return similarWords;
    }

    public ITrie getMyTrie() {
        return myTrie;
    }



    public class Word implements Comparable<Object> {
        String myWord;
        int frequency;

        // Constructor
        public Word() {
            myWord = new String();
            frequency = 0;
        }

        public Word(String myWord, int frequency) {
            this.myWord = myWord;
            this.frequency = frequency;
        }

        // Getters
        public String getMyWord() {
            return myWord;
        }

        public int getFrequency() {
            return frequency;
        }



        // Setters

        public void setMyWord(String myWord) {
            this.myWord = myWord;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Object o) {
            return myWord.compareTo(((Word)o).getMyWord());
        }
    }

}
