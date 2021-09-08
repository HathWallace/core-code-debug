package Debug;

import Case.Solution;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CoreCodeDebugTest {

    public static void main(String[] args) throws Exception {
        CoreCodeDebug codeDebug = new CoreCodeDebug();
        Method solutionMethod = codeDebug
                .getSolutionMethod(Solution.class);

        Solution solution = new Solution();
        while (true) {
            try {
                Object[] inputParameters = codeDebug
                        .getInputParameter(solutionMethod);
                Object result = solutionMethod
                        .invoke(solution, inputParameters);
                codeDebug.outputResult(result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}