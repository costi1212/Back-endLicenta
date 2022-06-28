package com.parcel.ParcelManagerBackend.utils;

import lombok.experimental.UtilityClass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@UtilityClass
public class FileUtils {

    public void writeBufferedWriter(String fileName, String content)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();
    }

}
