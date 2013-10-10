package com.csg.warrior.filechooser;

import java.io.File;
import java.util.Comparator;

public class DirectoryFirstComparator implements Comparator<File> {
    @Override
    public int compare(File file, File anotherFile) {
        if (file.isDirectory() == anotherFile.isDirectory()) {
            return file.compareTo(anotherFile);
        } else {
            return file.isDirectory() ? -1 : 1;
        }
    }
}
