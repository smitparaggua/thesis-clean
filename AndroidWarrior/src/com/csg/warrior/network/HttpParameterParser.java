package com.csg.warrior.network;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class HttpParameterParser {
    Map<String, String> parameters = new LinkedHashMap<String, String>();

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
        if(parameters.size() > 0) {
            return parseNonEmptyParameters();
        } else {
            return "";
        }
    }

    private String parseNonEmptyParameters() {
        StringBuilder stringBuilder = new StringBuilder();
        final Set<String> parameterNames = parameters.keySet();
        for(String name:parameterNames) {
            final String value = parameters.get(name);
            stringBuilder.append(name).append("=").append(value).append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
}
