package ru.nsu.fit.g16203.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FileUtil {

    public static void getAllFiles(String str, File dir) {
        List<File> foundFiles = new ArrayList<>();
        Stack<File> dirs = new Stack<>();
        dirs.push(dir);
        while (!dirs.isEmpty()) {
            File[] files = dirs.pop().listFiles();
            if (files != null) {
                for (final File f : files) {
                    if (f.isDirectory()) {
                        dirs.push(f);
                    } else {
                        if (f.getName().contains(str)) {
                            foundFiles.add(f);
                        }
                    }
                }
            }
        }
        if (!dir.isDirectory() && dir.getName().contains(str)) {
            foundFiles.add(dir);
        }
        foundFiles.forEach(System.out::println);
    }
}
