package ru.nsu.fit.g16203.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InFileSearcher {
    private File folder;

    private long time1, time2;

    private List<String> find(File file, File folder, String str) {
        final List<String> found = new ArrayList<>();
        time1 = System.currentTimeMillis();
        //TODO



        time2 = System.currentTimeMillis();
        System.out.println("Took " + (time2 - time1) / 1000 + "seconds");
        return found;
    }


}
