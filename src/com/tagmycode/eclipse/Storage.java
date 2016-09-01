package com.tagmycode.eclipse;

import com.tagmycode.plugin.IStorage;

import java.io.IOException;

public class Storage implements IStorage {

    private HashMapToFile hashMapToFile;

    public Storage() {
        this.hashMapToFile = new HashMapToFile(new SaveFilePath().getPath("eclipse_tagmycode_storage.ser"));
    }

    @Override
    public String read(String key) throws IOException {
        return hashMapToFile.loadValue(key);
    }

    @Override
    public void write(String key, String value) throws IOException {
        hashMapToFile.saveValue(key, value);
    }

    @Override
    public void unset(String key) throws IOException {
        hashMapToFile.deleteValue(key);
    }
}
