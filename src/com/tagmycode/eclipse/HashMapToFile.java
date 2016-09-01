package com.tagmycode.eclipse;

import java.io.*;
import java.util.HashMap;

public class HashMapToFile {
    private HashMap<String, String> storage;
    private String filename;

    public HashMapToFile(String filename) {
        this.filename = filename;
        loadFromFile();
    }

    public void saveValue(String key, String value) {
        storage.put(key, value);
        saveToFile();
    }

    public String loadValue(String key) {
        return storage.get(key);
    }

    public void deleteValue(String key) {
        storage.remove(key);
        saveToFile();
    }

    private void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(storage);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	private void loadFromFile() {
        try {
            FileInputStream fis = new FileInputStream(filename);

            ObjectInputStream ois = new ObjectInputStream(fis);
            storage = (HashMap<String, String>) ois.readObject();
            ois.close();
            fis.close();

        } catch (Exception e) {
            storage = new HashMap<>();
            saveToFile();
            e.printStackTrace();
        }
    }
}
