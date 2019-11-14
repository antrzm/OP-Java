package ru.nsu.fit.g16203;

import ru.nsu.fit.g16203.data.DataGetter;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//https://pastebin.com/x9TzKb
public class Main {

    private static final String MAN = "[--name fileName] [--data searchString] <folder|file>";
    private static String searchString;
    private static File startFile;
    private static Predicate<File> fileFilter;

    public static void main(String[] args) {

        parseArgs(args);

        List<File> files = getFileList(startFile, fileFilter);  //TODO

        /*
         * print file list
         */
        if (searchString == null) {
            for (File f : files) {
                System.out.println(f.getName());
            }
            System.exit(0);
        }

        List<DataGetter> dataGetters = new ArrayList<>(files.size());

        for (File f : files) {

        }

        for (File file : files) {
//            DataGetter dataGetter = new DataGetter(file, searchString);
//            dataGetters.add(dataGetter);
//            dataGetter.run();
        }
        System.out.println("String was found in files: ");
//        dataGetters.stream()
//                .filter(DataGetter::isFoundString)
//                .forEach(dg -> System.out.println(dg.getFile()));
    }

    private static void parseArgs(String[] args) {
        if (args.length < 1) {
            printErrorAndTerminate();
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].toLowerCase().equals("--name")) {
                if (i + 1 == args.length) {
                    printErrorAndTerminate();
                }
                String fileName = args[i + 1];
                fileFilter = f -> f.getName().equals(fileName);
                i++;
            } else if (args[i].toLowerCase().equals("--data")) {
                if (i + 1 == args.length) {
                    printErrorAndTerminate();
                }
                searchString = args[i + 1];
                i++;
            } else if (args[i].toLowerCase().toLowerCase().equals("help")) {
                printHelpAndTerminate();
                break;
            } else {
                startFile = Paths.get(args[i]).toFile();
            }
        }
        if (startFile == null) {
            printErrorAndTerminate();
        }
    }

    private static void printErrorAndTerminate() {
        System.err.println("No enough args");
        printHelpAndTerminate();
    }

    private static void printHelpAndTerminate() {
        System.err.println("No enough args");
        System.out.println(MAN);
        System.exit(-1);
    }


}
