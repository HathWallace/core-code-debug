package InputAndOutput;

import Param.ListNode;
import Param.TreeNode;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class InputFactory {

    public Object produce(Class<?> paraClass, String input) throws Exception {
        if (paraClass.equals(int.class) ||
                paraClass.equals(Integer.class)
        )
            return parseNum(input);

        if (paraClass.equals(String.class))
            return parseString(input);

        if (paraClass.equals(char.class) ||
                paraClass.equals(Character.class))
            return parseChar(input);

        if (paraClass.equals(int[].class))
            return parseNums(input);

        if (paraClass.equals(int[][].class))
            return parseMatrix(input);

        if (paraClass.equals(ListNode.class))
            return parseListNode(input);

        if (paraClass.equals(TreeNode.class))
            return parseTreeNode(input);

        throw new Exception("暂不支持的类型。");
    }

    private TreeNode parseTreeNode(String input) {
        return TreeNode.deserialize(input);
    }

    private char parseChar(String input) {
        return input.charAt(1);
    }

    private String parseString(String input) {
        if (input.isEmpty())
            return input;
        if (input.charAt(0) != '"')
            return input;
        return input
                .substring(1, input.length() - 1);
    }

    private ListNode parseListNode(String input) {
        Collection<?> valCollection = getJsonCollection(input);
        ListNode head = new ListNode(), pt = head;
        for (Object val : valCollection) {
            pt.next = new ListNode((int) val);
            pt = pt.next;
        }
        return head.next;
    }

    private int[][] parseMatrix(String input) {
        Collection<?> valCollection = getJsonCollection(input);
        int[][] matrix = new int[valCollection.size()][];
        int index = 0;
        for (Object nums : valCollection) {
            ArrayList arrayList = (ArrayList) nums;
            matrix[index] = new int[arrayList.size()];
            int[] row = matrix[index++];
            for (int i = 0; i < arrayList.size(); i++) {
                row[i] = (int) arrayList.get(i);
            }
        }
        return matrix;
    }

    private int[] parseNums(String input) {
        Collection<?> valCollection = getJsonCollection(input);
        int[] nums = new int[valCollection.size()];
        int index = 0;
        for (Object val : valCollection) {
            nums[index++] = (int) val;
        }
        return nums;
    }

    private int parseNum(String input) {
        return Integer.parseInt(input);
    }

    private Collection<?> getJsonCollection(String line) {
        JSONArray jsonArray = JSONArray.fromObject(line);
        return JSONArray.toCollection(jsonArray);
    }
}