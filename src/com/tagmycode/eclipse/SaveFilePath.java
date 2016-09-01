package com.tagmycode.eclipse;

import java.io.File;

public class SaveFilePath {
    public String getPath(String file) {
        String userHome = System.getProperty("user.home");
        String path = userHome + File.separator + ".tagmycode_example";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return path + File.separator + file;
    }
}
