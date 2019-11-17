package ru.nsu.fit.g16203.util.data;

import ru.nsu.fit.g16203.util.file.FileUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    private String strToFind;
    private File file;

    public DataUtil(String strToFind, File file) {
        this.strToFind = strToFind;
        this.file = file;
    }

    public void search() {
        List<File> files = new ArrayList<>();
        if (file.isDirectory()) {
            files = FileUtil.getAllFiles("", file);
        } else {
            files.add(file);
        }
        for (File f : files) {
            try {
                searchInFile(f);
            } catch (IOException e) {
                System.err.println("Error with " + f.getName());
                e.printStackTrace();
            }
        }
    }

    private void searchInFile(File f) throws IOException {
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(f));
        long size = f.length();
        long count = 0;
        int sizeForArray = size < 1024 * 1024 ? (int) size : 1024 * 1024;
        byte[] buf = new byte[sizeForArray];
        while (count < size) {
            stream.read(buf, 0, buf.length);
            String str = new String(buf, 0, sizeForArray);
            int ind = str.indexOf(strToFind);
            if (ind > -1) {
                System.out.println("Found in: " + f.getName() + " on pos: " + (count + ind));
            }
            count += buf.length;
        }
    }

}
