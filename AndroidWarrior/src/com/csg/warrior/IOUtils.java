package com.csg.warrior;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
    public static void closeQuietly(Closeable stream) {
        try {
            stream.close();
        } catch (IOException e) { }
    }
}
