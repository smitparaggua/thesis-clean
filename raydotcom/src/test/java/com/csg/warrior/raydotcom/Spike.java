package com.csg.warrior.raydotcom;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Spike {

    @Test
    public void warriorKeyStatus() {
        WarriorConfig config = new WarriorConfig();
        HttpParameterBuilder paramBuilder = new HttpParameterBuilder();
        System.out.println(true);
        try{
            ObjectOutputStream writer;
            BufferedReader reader;
            URL url = new URL("http://localhost:8080/testserver/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            paramBuilder.addParameter("username", "smit");
            paramBuilder.addParameter("website", "ray.com");
            paramBuilder.addParameter("invalidateForLogin", "false");

//            //writer = new DataOutputStream(connection.getOutputStream());
//            writer = new ObjectOutputStream(connection.getOutputStream());
//            writer.writeBytes(paramBuilder.parse());
//            writer.flush();

            ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
//            System.out.println(connection.getContent());

            //System.out.println(readServerResponse(reader));

            connection.disconnect();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }




    public class HttpParameterBuilder {
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
}
