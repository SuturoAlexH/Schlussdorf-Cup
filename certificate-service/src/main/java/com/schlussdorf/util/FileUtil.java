package com.schlussdorf.util;

import com.google.common.collect.Iterables;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * This class provides com.schlussdorf.util method for files.
 */
public class FileUtil {

    /**
     * Replace all occurrences of the keys with the values of the replacementMap at the file corresponding with the given file path.
     *
     * @param filePath the file path to the file that should be modified
     * @param replacementMap a map that contains place holders as key and the corresponding values
     * @throws IOException if something went wrong while replacing the place holders
     */
    public static void replacePlaceHolder(final String filePath, final Map<String, String> replacementMap) throws IOException {
        String fileContent = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        String modifiedContent = StringUtils.replaceEach(fileContent, Iterables.toArray(replacementMap.keySet(), String.class), Iterables.toArray(replacementMap.values(), String.class));

        try(FileWriter fw = new FileWriter(filePath, StandardCharsets.UTF_8)){
            fw.write(modifiedContent);
        }
    }
}
