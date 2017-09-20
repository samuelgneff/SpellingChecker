package spell;

public class TrieNode implements Trie.INode {
    boolean isEnd;

    private int count; // how many times has the word appeared

    TrieNode letters[];


    // Constructors
    public TrieNode() {
        letters = new TrieNode[26];
        count = 0;
    }



    // Getters
    public int getValue() {

        return count;
    }

    // Setters

    // I don't think that I need any setters

    public TrieNode getNodeAt(int index) throws IndexOutOfBoundsException {
        if (index >= letters.length || index < 0) {
            System.out.println("Index is out of bounds");
            return null;
        }
        else {
            return letters[index];
        }
    }

    public TrieNode find(String word, int currentIndex) {
        if (currentIndex == word.length()) {
            if (count == 0) {
                return null;
            }
            else {
                return this;
            }
        }
        else {
            if (letters[word.charAt(currentIndex) - 'a'] == null) {
                return null;
            }
            else {
                return letters[word.charAt(currentIndex) - 'a'].find(word, currentIndex + 1);
            }
        }

    }

    public String toString(StringBuilder dictionary, StringBuilder word) {
        for (char i = 'a'; i < 'z'; i++) { // length of array by char value
            if (letters[i - 'a'] != null) { // if a node exists at this location
                if (letters[i - 'a'].getValue() > 0) { // is there a word at this location?
                    word.append(i); // we add the word to the stringbuilder
                    dictionary.append(word.toString() + "\n"); // add word and an endline
                    letters[i - 'a'].toString(dictionary, word); // this takes the next letter in the trie
                }
                else { // there isn't a word at this location, so don't add, just continue search
                    letters[i - 'a'].toString(dictionary, word.append(i));
                }
                word.deleteCharAt(word.length() - 1);
            }
        }
        return dictionary.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this ==o) { // is this the exact same object? (same memory location)
            return true;
        }
        else if (o == null) { // does the object exist?
            return false;
        }
        else if (getClass() != o.getClass()) { // do the share the same class?
            return false;
        }
        else {
            TrieNode myNode = (TrieNode) o; // we know they are the same class, so cast 0 to be a node;
            if (this.getValue() != myNode.getValue()) { // compares the frequency of the two objects
                return false;
            }
            for (int i = 0; i < 26; i++) { // for loop for length of array of nodes
                // two nodes for comparison
                TrieNode n1 = letters[i];
                TrieNode n2 = myNode.getNodeAt(i);
                if (n1 != null) { // does n1 exist?
                    if (!n1.equals(n2)) { // start recursive call
                        return false;
                    }
                }
                else {
                    if (n2 != null) { // if n2 doesn't exist, they are obviously not equal
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public int addNode(String word, int currentIndex) {
        int newNodes = 0; // counts the amount of new nodes made when adding a word
        if (currentIndex == word.length()) {
            count++;
        }
        else {
            if (letters[word.charAt(currentIndex) - 'a'] == null) {
                newNodes++;
                letters[word.charAt(currentIndex) - 'a'] = new TrieNode();
            }
            newNodes += letters[word.charAt(currentIndex) - 'a'].addNode(word,currentIndex + 1);

        }
        return newNodes; // the amount of new words created
    }



}
