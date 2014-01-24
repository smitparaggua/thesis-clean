package com.csg.warrior.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.util.Log;

import com.csg.warrior.IOUtils;
import com.csg.warrior.exception.FailedUploadException;

public class HttpPOSTHelper {
	
	HttpParameterBuilder paramBuilder;
	
	public HttpPOSTHelper () {		
		paramBuilder = new HttpParameterBuilder();
	}
	
	public void addParameter(String key, String value){
		paramBuilder.addParameter(key, value);
	}
	
	public String sendPOST(String param_url) throws FailedUploadException {
		
		DataOutputStream writer = null;
        BufferedReader reader = null;
        String response = "";

        try {
        	URL url = new URL(param_url);
        	HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
        	connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
           
            writer = new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(paramBuilder.parse());
            writer.flush();
            
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            response = readServerResponse(reader);
            
            connection.disconnect(); 
        } catch (MalformedURLException e) {
        	Log.i("DAN", "Class: " + e.getClass() +
        			"\n Description" + e.getMessage());
        	throw new FailedUploadException("Malformed URL", e);            
        } catch (ProtocolException e) {
        	Log.i("DAN", "Class: " + e.getClass() +
        			"\n Description: " + e.getMessage());
            throw new FailedUploadException("Invalid Protocol", e);

        } catch (IOException e) {
        	 // TODO note: IOException may result from connection problem or error in reading the key from file. Fix this
        	Log.i("DAN", "Class: " + e.getClass() +
        			"\n Description" + e.getMessage());           
            throw new FailedUploadException("Connection Problem", e);
        } finally {
            closeIoStream(writer, reader);
        }
		return response;
	}
	
    private  String readServerResponse(BufferedReader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }
        return stringBuilder.toString();
    }
    
    private  void closeIoStream(DataOutputStream writer, BufferedReader reader) {
        IOUtils.closeQuietly(writer);
        IOUtils.closeQuietly(reader);
    }
	
	
}
