package ru.nsu.fit.g16203.util.file;

import ru.nsu.fit.g16203.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FileUtil {

    private static final String NO_FILE_ERROR = "File doesn't exist";

    public static List<File> getAllFiles(String str, File dir) {
        if (!dir.exists()){
            Main.printErrorAndTerminate(NO_FILE_ERROR);
        }
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
        if (!foundFiles.isEmpty()) System.out.println("Found files:");
        foundFiles.forEach(System.out::println);
        return foundFiles;
    }
}
