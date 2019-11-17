package ru.nsu.fit.g16203;

import ru.nsu.fit.g16203.util.file.FileUtil;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class Main {

    private static final String HELP = "[--name fileName] [--data stringToFind] <folder|file>";
    private static final String NOT_ENOUGH_ERROR = "Not enough args";
    private static final String WRONG_INPUT_ERROR = "Wrong input";
    private static String stringToFind;
    private static File file;
    private static String fileNameToFind;

    public static void main(String[] args) {

        parseArgs(args);

        if (file != null) {
            if (stringToFind != null) {
                //TODO
            }
            if (fileNameToFind != null) {
                FileUtil.getAllFiles(fileNameToFind, file);
            }
        } else {
            printErrorAndTerminate(WRONG_INPUT_ERROR);
        }

    }

    private static void parseArgs(String[] args) {
        if (args.length < 1) {
            printErrorAndTerminate(NOT_ENOUGH_ERROR);
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].toLowerCase().equals("--name")) {
                if (i + 1 == args.length) {
                    printErrorAndTerminate(NOT_ENOUGH_ERROR);
                }
                fileNameToFind = args[i + 1];
                i++;
            } else if (args[i].toLowerCase().equals("--data")) {
                if (i + 1 == args.length) {
                    printErrorAndTerminate(NOT_ENOUGH_ERROR);
                }
                stringToFind = args[i + 1];
                i++;
            } else if (args[i].toLowerCase().toLowerCase().equals("help")) {
                printHelpAndTerminate();
                break;
            } else {
                try {
                    file = Paths.get(args[i]).toFile();
                } catch (InvalidPathException e) {
                    printErrorAndTerminate(WRONG_INPUT_ERROR);
                }
            }
        }
    }

    private static void printErrorAndTerminate(String err) {
        System.err.println(err);
        printHelpAndTerminate();
    }

    private static void printHelpAndTerminate() {
        System.out.println(HELP);
        System.exit(-1);
    }


}
