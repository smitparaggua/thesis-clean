package com.csg.warrior.android.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.util.Log;

import com.csg.warrior.android.IOUtils;
import com.csg.warrior.android.exception.FailedUploadException;

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
        	throw new FailedUploadException("Malformed URL: please check the URL", e);            
        } catch (ProtocolException e) {
            throw new FailedUploadException("Invalid Protocol", e);
        } catch (IOException e) {          
        	Log.i("DAN", "eto ba");
            throw new FailedUploadException("Connection Problem: please check your internet connection or the server may be down", e);
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
