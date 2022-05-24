package jrobot.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DefaultContextManager implements ContextManager {
    
    public static void main(String[] args) {
        DefaultContextManager test = new DefaultContextManager();
        test.put("a", 123);
        test.put("b", "ggg");
        List<String> list = new ArrayList<>();
        list.add("qqq");
        list.add("wwww");
        test.put("arr", list);
        
        test.get("a");
        test.get("b");
        test.get("{arr[0]}");
        test.get("{arr}");
    }

    private Map<String, Object> localVariables;
    
    public DefaultContextManager() {
        this.localVariables = new HashMap<>();
    }

    private Object defaultVariable(String keyword) {
        if (keyword.equals("CURR_ROUND")) {
            return Optional.ofNullable(this.localVariables.get(LoopCounter.VARNAME))
                .map(LoopCounter.class::cast)
                .map(LoopCounter::currentIteration)
                .orElse(-1);
        }
        return null;
    }
    
    @Override
    public Object get(String var) {
        String varName;
        if (var.startsWith("{")) {
            varName = var.substring(1, var.length() - 1);
            Object fromKeyword = Optional.ofNullable(this.defaultVariable(varName)).orElse(null);
            if (fromKeyword != null) return fromKeyword;

            if (varName.endsWith("]")) {
                String rawIndex = varName.substring(varName.indexOf("[") + 1, varName.indexOf("]"));
                // Parse variable recursively
                Object indexFromManager = get(rawIndex);
                // If it is pure number of rawIndex, directly parsing it to an integer
                int realIndex = indexFromManager == null ? Integer.parseInt(rawIndex) : (int)indexFromManager;
                varName = varName.substring(0, varName.indexOf("["));
                return ((List<?>)localVariables.get(varName)).get(realIndex);
            } else
                return localVariables.get(varName);
        } else {
            varName = var;
        }
        
//        return localVariables.getOrDefault(varName, varName);
        return localVariables.get(varName);
    }

    @Override
    public void put(String var, Object value) {
        localVariables.put(var, value);
    }

}
