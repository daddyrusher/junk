package com.daddyrusher.cachemanager;

public class DataObject {
    private String data;

    private static int objectCounter = 0;

    public DataObject() {
    }

    public DataObject(String data) {
        this.data = data;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static int getObjectCounter() {
        return objectCounter;
    }

    public static void setObjectCounter(int objectCounter) {
        DataObject.objectCounter = objectCounter;
    }

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }
}