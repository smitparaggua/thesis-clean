package com.csg.warrior.android;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
    public static void closeQuietly(Closeable stream) {
        try {
            if (stream != null) stream.close();
        } catch (IOException e) { }
    }
}
