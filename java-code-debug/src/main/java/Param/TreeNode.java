package Param;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public static String serialize(TreeNode root) {
        Deque<Integer> dataList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                dataList.offer(null);
                continue;
            }
            dataList.offer(node.val);
            queue.offer(node.left);
            queue.offer(node.right);
        }
        while (!dataList.isEmpty() && dataList.getLast() == null)
            dataList.removeLast();
        return JSONArray
                .fromObject(dataList)
                .toString();
    }

    public static TreeNode deserialize(String data) {
        JSONArray jsonArray = JSONArray.fromObject(data);
        int n = jsonArray.size();
        if (n == 0)
            return null;
        TreeNode root = new TreeNode((int) jsonArray.get(0));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        for (int i = 1; i < n; i += 2) {
            TreeNode node = nodeQueue.poll();
            Object leftVal = jsonArray.get(i);
            if (leftVal != JSONNull.getInstance()) {
                node.left = new TreeNode((int) leftVal);
                nodeQueue.offer(node.left);
            }
            if (i + 1 < n) {
                Object rightVal = jsonArray.get(i + 1);
                if (rightVal != JSONNull.getInstance()) {
                    node.right = new TreeNode((int) rightVal);
                    nodeQueue.offer(node.right);
                }
            }
        }
        return root;
    }

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return TreeNode.serialize(this);
    }
}
