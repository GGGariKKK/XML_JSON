package com.iharkusha.fileWork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriter {
    public void writeToFile(String fileName, String data) throws IOException {
        Files.write(Paths.get(fileName), data.getBytes());
    }
}
