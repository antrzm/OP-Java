package ru.nsu.fit.g16203.util.file;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FileUtil {

    public static void getAllFiles(String str, File dir) {
        Stack<File> dirs = new Stack<File>();
        dirs.push(dir);
        while (!dirs.isEmpty()) {
            File[] files = dirs.pop().listFiles();
            if (files != null) {
                for (final File f : files) {
                    if (f.isDirectory()) {
                        dirs.push(f);
                    } else {
                        search(str, f);
                    }
                }
            }
        }
    }

    static void search(String str, File f) {
        final TwoWaySearch algorithm = new TwoWaySearch();
        final List<Long> positions = new LinkedList<Long>();
        final int length = str.length();
        final byte[] needed = str.getBytes();
        final long fileSize = f.length();
        long currentPos = 0L;
        BufferedInputStream reader;
        try {
            reader = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e2) {
            System.out.println("File not found");
        }
        final byte[] array = new byte[1048576];
        try {
            while (currentPos < fileSize) {
                int size = 0;
                if (reader != null) {
                    reader.mark(1048576);
                    size = reader.read(array, 0, 1048576);
                    reader.reset();
                    reader.skip(size - length);
                }
                final List<Long> positionList = algorithm.indexOf(needed, array, size);
                for (final Long val : positionList) {
                    positions.add(val + currentPos);
                }
                currentPos += size;
                if (currentPos < fileSize - 1L) {
                    currentPos -= length;
                }
            }
            positions.forEach(pos -> System.out.println(f.getName() + ' ' + pos));
        } catch (IOException e) {
            System.out.println(f.getName());
            System.out.println(e.getMessage());
        }
    }

}
