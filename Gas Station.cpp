// first try: time O(n2) space O(1)
class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        // if sum(gas) < sum (cost) return -1
        // try start from 0
        // if at some point cur < 0 try again from next point
        for (int i = 0, cur = 0, j = 0; i < gas.size(); i ++){
            for (j = i; j < i + gas.size(); j ++){
                cur += gas[j % gas.size()] - cost[j % gas.size()]; // forget "%" --> overflow
                if (cur < 0) break;
            }
            if (j == i + gas.size()) return i;
            cur = 0;
        } 
        return -1;
    }
};

// second try: time O(n) space O(1)
class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        // if sum(gas) < sum (cost) return -1
        // try start from 0
        // if at some point cur <= 0 start from next point
        int total = 0, cur = 0, start = 0;
        for (int i = 0, cur = 0, j = 0; i < gas.size(); i ++){
            total += gas[i] - cost[i];
            cur += gas[i] - cost[i];
            if (cur < 0) {
              cur = 0;
              start = i + 1;
            }
        }
        if (total < 0) return -1;
        return start;
    }
};
