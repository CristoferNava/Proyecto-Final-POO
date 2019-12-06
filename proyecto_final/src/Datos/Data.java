package Datos;

import java.io.*;
import java.util.TreeMap;

public class Data {
    private static Data data = null;
    private TreeMap<String, Object> values = new TreeMap<>();
    private final String file = "data.dat";

    private Data() {}

    public static Data getInstance() {
        if (data == null) {
            data = new Data();
        }
        return data;
    }

    public void add(String key, Object value) {
        this.values.put(key, value);
    }

    public void edit(String key, Object value) {
        if (this.values.containsKey(key)) {
            this.values.replace(key, value);
        } else {
            this.values.put(key, value);
        }
    }

    public Object get(String key) {
        if (this.values.containsKey(key)) {
            return this.values.get(key);
        }
        return null;
    }

    public void load() throws Exception {
        try {
            if (!new File(this.file).exists()) {
                return;
            }
            ObjectInputStream oisLoad = new ObjectInputStream(new FileInputStream(this.file));
            this.values = (TreeMap<String, Object>) oisLoad.readObject();
            oisLoad.close();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void save() throws Exception {
        try {
            ObjectOutputStream oosSave = new ObjectOutputStream(new FileOutputStream(this.file));
            oosSave.writeObject(this.values);
            oosSave.close();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
