package com.csg.warrior.network;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HttpPostRequestTest {
    HttpPostParameterParser httpPostParameterParser = new HttpPostParameterParser();

    @Test
    public void addingNewParameters() {
        final String parameterName = "username";
        final String parameterValue = "password";
        httpPostParameterParser.addParameter(parameterName, parameterValue);
        assertTrue("Parameter not added to the request.", httpPostParameterParser.containsParameter(parameterName));
    }

    @Test
    public void validateAddedParameterValue() {
        final String parameterName = "username";
        final String parameterValue = "password";
        httpPostParameterParser.addParameter(parameterName, parameterValue);
        String actualParameterAdded = httpPostParameterParser.getParameterValue(parameterName);
        final String errorMessage = "Wrong parameter value added. Added value: " + parameterValue + ".";
        assertEquals(errorMessage, parameterValue, actualParameterAdded);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullParameter() {
        httpPostParameterParser.addParameter(null, null);
    }

    @Test
    public void parseSingleParameter() {
        final String parameterName = "username";
        final String parameterValue = "password";
        httpPostParameterParser.addParameter(parameterName, parameterValue);
        final String parsedParameters = httpPostParameterParser.parse();
        final String expected = parameterName +"="+parameterValue;
        assertEquals("Parameters are not parsed correctly", expected, parsedParameters);
    }

    @Test
    public void parseMultipleParameters() {
        final String[] parameterNames = {"param1", "param2", "param3"};
        final String[] parameterValues = {"val1", "val2", "val3"};
        httpPostParameterParser.addMultipleParameters(parameterNames, parameterValues);
        // todo check parsed values
    }
}
