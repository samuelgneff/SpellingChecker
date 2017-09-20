package spell;

public class Trie implements ITrie {


    // Data Members
    private TrieNode root;
    private int wordCount;
    private int nodeCount;


    // Constructor
    public Trie() {
        nodeCount = 1;
        wordCount = 0;
        root = new TrieNode();
    }

    @Override
    public void add(String word) {
        word = word.toLowerCase();

        // find word to see if it has already been added
        if (find(word) == null) {
            wordCount++;
        }

        int index = 0;

        //recursively add nodes and return number of nodes added
        nodeCount += root.addNode(word, index);




    }

    @Override
    public INode find(String word) {
        if (root == null) {
            return null;
        }
        else {
            INode endNode = null;
            int index = 0;
            endNode = root.find(word, index);
            return endNode;
        }




    }

    // Getters

    public int getWordCount() {


        return wordCount;

    }


    public int getNodeCount() {


        return nodeCount;

    }
    public INode getRootNode() {
        return root;
    }


    @Override
    public String toString() {
        StringBuilder dictionary = new StringBuilder("");
        StringBuilder word = new StringBuilder("");
        return root.toString(dictionary, word); // the recursive toString call

    }


    @Override
    public int hashCode() {

        int hashCode = 0;
        int primeNumber = 31;
        hashCode = (nodeCount + wordCount)* primeNumber;

        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true; // this is the exact memory location, thus we return true
        }
        if (o == null) {
            return false; // obviously if null doesn't exist
        }
        if (getClass() != o.getClass()) {
            return false; // the classes don't match, so return false
        }
        Trie tr = (Trie) o; // we cast o as a Trie because we know that they are ==

        if (nodeCount != tr.getNodeCount()) {
            return false; // the nodeCounts are different
        }
        else if (wordCount != tr.getWordCount()) {
            return false; // the wordCounts are different
        }
        else {
            TrieNode myRoot = (TrieNode) tr.getRootNode();
            return root.equals(myRoot); // finally check to see if their root nodes are equal
        }

    }





}