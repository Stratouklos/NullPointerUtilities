package com.nullpointerengineering.utilities.text;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created with IntelliJ IDEA.
 * User: Stratos
 * Date: 20/03/13
 * Time: 9:02 PM
 * Utility class to read a file line by line.
 */
public final class FileReader {

    private final Path path;

    public static FileReader getInstance(Path path) {
        return new FileReader(path);
    }

    private FileReader(Path path){
        this.path = path;
    }

    public void read(LineParser parser) {
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String lineIn = reader.readLine();
            while (lineIn != null) {
                parser.parse(lineIn);
                lineIn = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface LineParser {
        public void parse(String lineIn);
    }
}
