package com.csg.warrior.network;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HttpParameterParserTest {
    HttpParameterParser httpParameterParser = new HttpParameterParser();

    @Test
    public void addingNewParameters() {
        final String parameterName = "username";
        final String parameterValue = "password";
        httpParameterParser.addParameter(parameterName, parameterValue);
        assertTrue("Parameter not added to the request.", httpParameterParser.containsParameter(parameterName));
    }

    @Test
    public void validateAddedParameterValue() {
        final String parameterName = "username";
        final String parameterValue = "password";
        httpParameterParser.addParameter(parameterName, parameterValue);
        String actualParameterAdded = httpParameterParser.getParameterValue(parameterName);
        final String errorMessage = "Wrong parameter value added. Added value: " + parameterValue + ".";
        assertEquals(errorMessage, parameterValue, actualParameterAdded);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullParameter() {
        httpParameterParser.addParameter(null, null);
    }

    @Test
    public void parseSingleParameter() {
        final String parameterName = "username";
        final String parameterValue = "password";
        httpParameterParser.addParameter(parameterName, parameterValue);
        final String parsedParameters = httpParameterParser.parse();
        final String expected = parameterName +"="+parameterValue;
        assertEquals("Parameters are not parsed correctly", expected, parsedParameters);
    }

    @Test
    public void parseMultipleParameters() {
        final String[] parameterNames = {"param1", "param2", "param3"};
        final String[] parameterValues = {"val1", "val2", "val3"};
        addMultipleParameters(parameterNames, parameterValues);
        final String expected = parameterNames[0] + "=" + parameterValues[0] + "&" +
                                parameterNames[1] + "=" + parameterValues[1] + "&" +
                                parameterNames[2] + "=" + parameterValues[2];
        assertEquals("Parsed multiple parameters wrong.", expected, httpParameterParser.parse());
    }

    private void addMultipleParameters(final String[] parameterNames, final String[] parameterValues) {
        for(int i = 0; i < parameterValues.length; i++) {
            httpParameterParser.addParameter(parameterNames[i], parameterValues[i]);
        }
    }

    @Test
    public void parseNoParameters() {
        assertEquals("", httpParameterParser.parse());
    }
}
