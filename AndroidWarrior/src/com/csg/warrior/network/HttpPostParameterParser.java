package com.csg.warrior.network;

import java.util.HashMap;
import java.util.Map;

public class HttpPostParameterParser {
    Map<String, String> parameters = new HashMap<String, String>();

    public void addParameter(String parameterName, String parameterValue) {
        if(parameterName == null || parameterValue == null) {
            throw new IllegalArgumentException("Null parameter name or parameter value is not allowed");
        }
        parameters.put(parameterName, parameterValue);
    }

    public boolean containsParameter(String parameterName) {
        return parameters.containsKey(parameterName);
    }

    public String getParameterValue(String parameterName) {
        return parameters.get(parameterName);
    }

    public String parse() {
        return null;
    }

    public void addMultipleParameters(String[] parameterNames, String[] parameterValues) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
