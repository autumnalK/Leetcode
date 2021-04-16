import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution179 {
    private static int[] num = {432,43243};
    public static String largestNumber(int[] nums) {
        List<Integer> tmp = new LinkedList<>();
        for (int i : nums){
            tmp.add(i);
        }
        tmp.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Long.valueOf(String.valueOf(o1)+String.valueOf(o2)) > Long.valueOf(String.valueOf(o2)+String.valueOf(o1))){
                    return -1;
                }
                else return 1;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i : tmp){
            sb.append(i);
        }
        int i = 0;
        while (i < nums.length && nums[i] == 0) i++;
        if (i == nums.length) return "0";
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(largestNumber(num));
    }
}
