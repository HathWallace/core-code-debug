package InputAndOutput;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Scanner;

public class IOFromConsole {

    private Scanner scanner = new Scanner(System.in);

    public String input() {
        return scanner.nextLine();
    }

    public void output(String line) {
        System.out.println(line);
    }

    public void output(Object produce) {
        Class<?> aClass = produce.getClass();
        if (aClass.isArray()) {
            JSONArray jsonArray = JSONArray.fromObject(produce);
            output(jsonArray.toString());
        } else {
            String line = produce.toString();
            output(line);
        }
    }
}