package InputAndOutput;

import Param.ListNode;
import Param.TreeNode;
import net.sf.json.JSONArray;

class InputFactoryTest {

    private static Class<?>[] testList = {
            int.class,
            String.class,
            char.class,
            int[].class,
            int[][].class,
            ListNode.class,
            TreeNode.class,
    };

    public static void main(String[] args) {
        IOFromConsole io = new IOFromConsole();
        InputFactory inputFactory = new InputFactory();
        while (true) {
            try {
                for (Class<?> aClass : testList) {
                    io.output(aClass.getName());
                    String input = io.input();
                    if (input.isEmpty())
                        continue;
                    Object produce = inputFactory.produce(aClass, input);
                    io.output(produce);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}