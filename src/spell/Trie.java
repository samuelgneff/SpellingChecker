package spell;

public class Trie implements ITrie {

    TrieNode root;
    int wordCount = 0;
    int nodeCount = 0;



    public void add(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (p.letters[index] == null) {
                TrieNode temp = new TrieNode();
                p.letters[index] = temp;
                p = temp;
            }
            else {
                p = p.letters[index];
            }
        }
        p.increaseCount();
        p.isEnd = true;
    }

    public INode find(String word) {

        TrieNode p = root;

        for (int i = 0; i < word.length(); i++) {

            char c = word.charAt(i);
            int index = c - 'a';
            if (p.letters[index] != null) {

                p = p.letters[index];

            }
            else {
                return null;
            }
        }
        if (p == root) {
            return null;
        }

        return p;


    }


    public int getWordCount() {


        return wordCount;

    }


    public int getNodeCount() {


        return nodeCount;

    }


    @Override
    public String toString() {
        StringBuilder output;
        StringBuilder currentWord;
        toStringHelper(root,output,currentWord);



        return s;

    }

    public String toStringHelper(TrieNode n, StringBuilder output, StringBuilder currentWord) {

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

    public boolean equalsHelper(TrieNode t1, TrieNode t2) {
        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.getValue() == t2.getValue()) {
            if (t1.)
        }


    }



}