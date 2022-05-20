/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int sum = 0;
    int maxDepth = 0;

    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    public void dfs(TreeNode cur, int depth) {
        // 기저: cur이 null
        if(cur == null)
            return;

        // maxDepth보다 깊이 왔으면 갱신
        if (maxDepth < depth) {
            maxDepth = depth;
            sum = cur.val;
        }
        // 같은 깊이면 합해줌
        else if(maxDepth == depth) {
            sum += cur.val;
        }

        dfs(cur.left, depth+1);
        dfs(cur.right, depth+1);

    }
}