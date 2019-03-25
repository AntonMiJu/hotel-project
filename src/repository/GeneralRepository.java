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
            bufferedWriter.append(t.toString());
            bufferedWriter.append("\n");
            return t;
        } catch (IOException e) {
            System.err.println("Can't write to file" + path);
        }
        return null;
    }

    public void deleteObject(long id) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.append(deleteObjectFromString(id));
        } catch (IOException e) {
            System.err.println("Writing to file " + path + " failed");
        }
    }

    private StringBuffer deleteObjectFromString(long id) {
        StringBuffer res = new StringBuffer();
        for (T el : readFromFile()) {
            if (el.getId() != id) {
                res.append(el.toString());
                res.append("\n");
            }
        }
        return res;
    }

    public void deleteObject(long roomId, long userId) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.append(deleteObjectFromString(roomId, userId));
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
                    res.append(line);
                    res.append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Deleting from file " + path + " failed");
        }
        return res;
    }

    public abstract T map(String str);

    public void setPath(String path) {
        this.path = path;
    }

    public T findObjectById(long id) {
        for (T el : readFromFile()) {
            if (el.getId() == id)
                return el;
        }
        return null;
    }
}
