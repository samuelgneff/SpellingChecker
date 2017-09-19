package spell;

public class TrieNode implements Trie.INode {

    public TrieNode() {
        letters = new TrieNode[26];
    }

    boolean isEnd;

    private int count;

    TrieNode letters[];


    public int getValue() {

        return count;
    }

    public void increaseCount() {
        count++;
    }


}
