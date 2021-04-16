import java.util.Arrays;

public class Solution213 {
    // improvement on 198

    private int max (int a, int b){
        if (a > b) return a;
        else return b;
    }

    private int rob_list(int[] nums) {
        int N = nums.length;

        // f(k) = max {f(k-1), f(k-2)+H_k-1}
        // dp.length need to be nums.length+1
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= N; ++i){
            dp[i] = max(dp[i-1], dp[i-2]+nums[i-1]);
        }

        return dp[N];

    }

    public int rob(int[] nums) {
        int N = nums.length;
        if (N == 0) return 0;
        if (N == 1) return nums[0];

        int rob_first = rob_list(Arrays.copyOfRange(nums, 0, N-1));
        int rob_last = rob_list(Arrays.copyOfRange(nums, 1, N));
        return max(rob_first,rob_last);
    }
}
