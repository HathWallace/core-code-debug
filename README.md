# core-code-debug
核心代码debug工具

示例：

```java
CoreCodeDebug codeDebug = new CoreCodeDebug();
Method solutionMethod = codeDebug
    .getSolutionMethod(Solution.class);
Solution solution = new Solution();
Object[] inputParameters = codeDebug
    .getInputParameter(solutionMethod);
Object result = solutionMethod
    .invoke(solution, inputParameters);
codeDebug.outputResult(result);
```

