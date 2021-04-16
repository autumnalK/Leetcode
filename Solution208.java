class Trie {

    private boolean isEnd;
    private Trie[] next; // 下一字母可能的26种映射

    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
        for (int i = 0; i < 26; ++i)
            next[i] = null;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie current = this;
        char[] words = word.toCharArray();
        for (char cur : words){
            int index = cur-'a';
            if (current.next[index] == null)
                current.next[index] = new Trie();
            current = current.next[index];
        }
        current.isEnd=true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (isEnd == true) return false;
        char[] words = word.toCharArray();
        Trie current = this;
        for (char cur : words){
            if (current.next[cur-'a']==null)
                return false;
            current = current.next[cur-'a'];
        }
        if (current.isEnd == true) return true;
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (isEnd == true) return false;
        char[] words = prefix.toCharArray();
        Trie current = this;
        for (char cur : words){
            if (current.next[cur-'a']==null)
                return false;
            current = current.next[cur-'a'];
        }
        return true;
    }

    public void print(){

        Trie current = this;
        for (int i = 0; i < 26; ++i) {
            if (current.next[i] != null) System.out.println((char)('a'+i));
        }
    }
}

public class Solution208 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("app");
        System.out.println(trie.search("apple"));   // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("apple");
        System.out.println(trie.search("app"));    // 返回 True
    }
}
