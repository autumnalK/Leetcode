class Solution {
public:
    int numRollsToTarget(int d, int f, int target) {
        int min = d * 1;
        int max = d * f;
        int mod = 1000000007;
        if (target > max or target < min) return 0;
        else if (target == max or target == min) return 1;
        /**
        // 在最小目标分配的基础上，多出x点，本质是要求出这x分配给d的可能性
        // 考虑用枚举一个骰子，然后对剩下的骰子和点数做递归
        // 只有一个骰子的情况
        if (d == 1) return 1; // 上面已经排除了超过界限的情况
        // 考虑多个骰子的情况
        for(int i = f; i >= 1; i--){ // Patch1: 之前写习惯了失去了i = f的情况
            chance += numRollsToTarget(d-1,f,target-i);
        }
        // 超时了Orz，放弃递归
        */
        // 看了下题解，大家好像都用的DP算法，现学现用
        // 扔出第i个骰子之后的状态是d[i][j]，骰子之和为j
        // 第i个骰子的点数是k，可得d[i-1][j-k]
        // 于是d[i][j] = d[i-1][j-1]+d[i-1][j-2]+...+d[i-1][i-f]
        // 边界条件是d[1][k] = 1（k遍历1到f）
        vector<vector<int>> dp(d, vector<int>(target+1, 0));
        for(int i = 1; i <= (f < target ? f : target); i++) dp[0][i] = 1;
        for(int i = 1; i < d; i++) // 掷第i个骰子
            for(int j = i+1; j <= (target < (i+1)*f ? target : (i+1)*f); j++) // 前i+1个骰子可能掷出的总和
                for(int k = 1; k <= (f < j ? f : j); k++) // 骰子的可能取值
                    dp[i][j] = (dp[i][j] + dp[i-1][j-k]) % mod;
        return dp[d-1][target];
    }
};
