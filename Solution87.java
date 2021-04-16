import java.util.HashMap;
import java.util.Map;

public class Solution87 {
    private static Map<String, Map<String, Boolean>> memory = new HashMap<>();

    public static boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (s1.equals(s2)) return true;
        else if (n == 1) return false;

        // 简单递归超时
        if (memory.containsKey(s1) && memory.get(s1).containsKey(s2))
            return memory.get(s1).get(s2);

        // check 字符组成
        Map<Character, Integer> map1 = new HashMap<>();
        for (char c : s1.toCharArray()) {
            int i = map1.getOrDefault(c, 0);
            map1.put(c, i + 1);
        }
        for (char c : s2.toCharArray()) {
            if (!map1.containsKey(c)) return false;
            if (map1.get(c) == 0) return false;
            map1.put(c, map1.get(c) - 1);
        }
        // 没有判断s1.s2的长度和组成

        Map<String, Boolean> tmp;
        for (int i = 1; i < n; ++i) {
            boolean a = isScramble(s1.substring(0, i), s2.substring(n - i, n));
            boolean b = isScramble(s2.substring(0, n - i), s1.substring(i, n));
            if (a && b) {
                tmp = memory.getOrDefault(s1, new HashMap<>());
                tmp.put(s2, true);
                memory.put(s1, tmp);
                return true;
            }
            a = isScramble(s1.substring(0, i), s2.substring(0, i));
            b = isScramble(s2.substring(i, n), s1.substring(i, n));
            if (a && b) {
                tmp = memory.getOrDefault(s1, new HashMap<>());
                tmp.put(s2, true);
                memory.put(s1, tmp);
                return true;
            }

        }
        tmp = memory.getOrDefault(s1, new HashMap<>());
        tmp.put(s2, false);
        memory.put(s1, tmp);
        return false;
    }

    public static void main(String[] args) {
        String s = "great", t = "rgeat";
        System.out.println(isScramble(s,t));

    }
}
