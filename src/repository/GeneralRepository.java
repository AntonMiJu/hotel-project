package repository;

import model.GeneralClass;

import java.io.*;
import java.util.ArrayList;

public abstract class GeneralRepository<T extends GeneralClass> {
    private String path;


    public ArrayList<T> readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<T> objects = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                objects.add(map(line));
            }
            return objects;
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        } catch (IOException e) {
            System.out.println("Reading from file " + path + " failed");
        }
        return null;
    }

    public T addObject(T t) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {
            File file = new File(path);
            if (file.length() != 0)
                bufferedWriter.append("\n");
            bufferedWriter.append(reverseMap(t));
            return t;
        } catch (IOException e) {
            System.err.println("Can't write to file" + path);
        }
        return null;
    }

    public void deleteObject(long id) {
        StringBuffer stringBuffer = deleteObjectFromString(id);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.append(stringBuffer);
        } catch (IOException e) {
            System.err.println("Writing to file " + path + " failed");
        }
    }

    private StringBuffer deleteObjectFromString(long id) {
        StringBuffer res = new StringBuffer();
        for (T el : readFromFile()) {
            if (el.getId() != id) {
                if (res.length() != 0)
                    res.append("\n");
                res.append(reverseMap(el));
            }
        }
        return res;
    }

    public void deleteObject(long roomId, long userId) {
        StringBuffer stringBuffer = deleteObjectFromString(roomId, userId);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.append(stringBuffer);
        } catch (IOException e) {
            System.err.println("Deleting from file " + path + " failed");
        }
    }

    private StringBuffer deleteObjectFromString(long roomId, long userId) {
        StringBuffer res = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String[] array;
            while ((line = br.readLine()) != null) {
                array = line.split(",");
                if (Long.parseLong(array[1]) != userId || Long.parseLong(array[2]) != roomId) {
                    if (res.length() != 0)
                        res.append("\n");
                    res.append(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Deleting from file " + path + " failed");
        }
        return res;
    }

    public abstract T map(String str);

    public abstract String reverseMap(T t);

    public void setPath(String path) {
        this.path = path;
    }
}
