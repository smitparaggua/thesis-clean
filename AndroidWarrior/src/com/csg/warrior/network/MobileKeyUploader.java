package com.csg.warrior.network;

import com.csg.warrior.IOUtils;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.exception.FailedUploadException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MobileKeyUploader {
    public static String upload(MobileKey mobileKey) throws FailedUploadException {
        // todo make it asynchronous
        DataOutputStream writer = null;
        BufferedReader reader = null;
        String response = "";
        try {
            HttpURLConnection connection = configureHttpConnection(mobileKey);
            writer = sendParameters(mobileKey, connection);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = readServerResponse(reader);
            connection.disconnect();
        } catch (MalformedURLException e) {
            throw new FailedUploadException("Malformed URL", e);
        } catch (ProtocolException e) {
            throw new FailedUploadException("Invalid Protocol", e);
        } catch (IOException e) {
            // TODO note: IOException may result from connection problem or error in reading the key from file. Fix this
            throw new FailedUploadException("Connection Problem", e);
        } finally {
            closeIoStream(writer, reader);
        }
        return response;
    }

    private static HttpURLConnection configureHttpConnection(MobileKey mobileKey) throws IOException {
        URL url = new URL(mobileKey.getUrlForUpload());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        return connection;
    }

    private static DataOutputStream sendParameters(MobileKey mobileKey, HttpURLConnection connection) throws IOException {
        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
        writer.writeBytes(parseParameters(mobileKey));
        writer.flush();
        return writer;
    }

    private static String parseParameters(MobileKey mobileKey) throws IOException {
        HttpParameterParser parameterParser = new HttpParameterParser();
        parameterParser.addParameter("key", extractKey(mobileKey));
        parameterParser.addParameter("username", mobileKey.getKeyOwner());
        return parameterParser.parse();
    }

    private static String extractKey(MobileKey mobileKey) throws IOException {
        String key;
        BufferedReader reader = new BufferedReader(new FileReader(mobileKey.getAssociatedFile()));
        key = reader.readLine();
        return key;
    }

    private static void closeIoStream(DataOutputStream writer, BufferedReader reader) {
        IOUtils.closeQuietly(writer);
        IOUtils.closeQuietly(reader);
    }

    private static String readServerResponse(BufferedReader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }
        return stringBuilder.toString();
    }
}
