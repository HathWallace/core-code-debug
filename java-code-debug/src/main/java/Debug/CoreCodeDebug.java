package Debug;

import InputAndOutput.IOFromConsole;
import InputAndOutput.InputFactory;
import Exception.OnlyOnePublicMethodInSolution;

import java.lang.reflect.Method;

public class CoreCodeDebug {

    private IOFromConsole io = new IOFromConsole();

    private InputFactory inputFactory = new InputFactory();

    public Method getSolutionMethod(Class<?> solutionClass)
            throws OnlyOnePublicMethodInSolution {
        Method[] methods = solutionClass.getDeclaredMethods();

        Method solutionMethod = null;
        for (Method method : methods) {
            boolean isPublic = (method.getModifiers() & 1) == 1;
            if (!isPublic)
                continue;
            if (solutionMethod != null)
                throw new OnlyOnePublicMethodInSolution();
            solutionMethod = method;
        }

        if (solutionMethod == null)
            throw new OnlyOnePublicMethodInSolution();
        return solutionMethod;
    }

    public Object[] getInputParameter(Method solutionMethod) throws Exception {
        Class<?>[] parameterTypes = solutionMethod.getParameterTypes();
        int paramCount = parameterTypes.length;
        Object[] params = new Object[paramCount];
        for (int i = 0; i < paramCount; i++) {
            String input = io.input();
            params[i] = inputFactory
                    .produce(parameterTypes[i], input);
        }
        return params;
    }

    public void outputResult(Object result) {
        io.output(result);
    }
}