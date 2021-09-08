package Param;

import net.sf.json.JSONArray;

import java.util.LinkedList;
import java.util.List;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        List<Integer> valList = new LinkedList<>();
        ListNode pt = this;
        while (pt != null) {
            valList.add(pt.val);
            pt = pt.next;
        }
        return JSONArray
                .fromObject(valList)
                .toString();
    }
}