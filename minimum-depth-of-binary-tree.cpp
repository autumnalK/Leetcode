/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
 // mainly use Recursive Method, just too lazy for others
class Solution {
public:
    int minDepth(TreeNode* root) {
        // Patch 1: Forget about Empty tree
        if (root == NULL) return 0;
        int res, l, r;
        res = l = r = 0;
        // Patch 2: Didn't realize that root with one child(1) is different from leaf with one child (0)
        if (root->left == NULL and root->right == NULL) return 1;
        else if (root->left == NULL){
            root = root->right;
            return 1 + minDepth(root);
        }
        else if (root->right == NULL){
            root = root->left;
            return 1 + minDepth(root);
        }
        TreeNode *p = root;
        if (p->left != NULL) {
             l = minDepth(p->left);
        }
        else return 1;
        if (p->right != NULL){
            r = minDepth(p->right);
        }
        else return 1;
        res += l < r ? l : r;
        return res + 1;
    }
};
