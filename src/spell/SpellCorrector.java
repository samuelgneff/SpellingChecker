package spell;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {

    Trie myTrie;

    public void useDictionary(String dictionaryFileName) throws IOException {
        try {
            File file = new File(dictionaryFileName);
            FileReader fr = new FileReader(file);
            Scanner input = new Scanner(fr);
            input.useDelimiter("(\\s+)(#[^\\n]*\\n)?(\\s*)|(#[^\\n]*\\n)(\\s*)");
            input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                myTrie.add(line);
            }
            input.close();
            fr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String suggestSimilarWord(String inputWord) {
        String  s = "";

        return s;


    }

}
