package spell;

public class Trie implements ITrie {



    public void add(String word) {


    }

    public spell.ITrie.INode find(String word) {


    }

    public int getWordCount() {
        int wordCount = 0;

        return wordCount;

    }


    public int getNodeCount() {

    }


    @Override
    public String toString() {
        String s = "";

        return s;

    }

    @Override
    public int hashCode() {

        int hashCode = 0;

        return hashCode;
    }

    @Override
    public boolean equals(Object o) {

        boolean equals = false;

        return equals;

    }


    public interface INode {

        /**
         * Returns the frequency count for the word represented by the node
         *
         * @return The frequency count for the word represented by the node
         */
        public int getValue();
    }
}
