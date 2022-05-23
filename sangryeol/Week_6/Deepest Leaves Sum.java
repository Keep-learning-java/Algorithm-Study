class Solution {
    int maxDepth, sum;
    public void dfs(TreeNode curr, int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
            sum = 0;
        }
        if (depth == maxDepth) {
            sum += curr.val;
        }
        if (curr.left != null) dfs(curr.left, depth + 1);
        if (curr.right != null) dfs(curr.right, depth + 1);
    }
    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }
}