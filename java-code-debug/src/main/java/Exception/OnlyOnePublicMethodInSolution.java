package Exception;

public class OnlyOnePublicMethodInSolution extends Exception {

    public OnlyOnePublicMethodInSolution() {
        super("必须有且仅有一个公共方法！");
    }
}