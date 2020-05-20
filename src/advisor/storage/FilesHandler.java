package advisor.storage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

public class FilesHandler {

    final static private String PATH = System.getProperty("user.home") + "\\advisor\\";
    public static void check(String path, boolean isDir) throws IOException {
        File file = get(path);
        if (file.exists()) {
            System.out.println("This file is already there");
        } else {
            if (isDir) {
                file.mkdir();
            } else {
                file.createNewFile();
            }
        }

    }

    public static Scanner read(String path) throws IOException {
        check(path, false);

        return new Scanner(new File(PATH + path));
    }

    public static PrintWriter wirte(String path) throws IOException {
        check(path, false);
        return new PrintWriter(new File(PATH + path));
    }

    public static void init() throws IOException {
        check("", true);
    }

    public static String getImage(String name) {
        String temp = name;
        try {
            File image = advisor.Advisor.pick();
            if (image == null) {
                return temp;
            }
            name += image.getName();
            delete(name);
            File copy = get(name);
            Files.copy(image.toPath(), copy.toPath());
            return name;
        } catch (IOException ex) {
            ex.printStackTrace();
            return temp;
        }
    }

    public static void delete(String path) {
        File f = get(path);
        f.delete();
    }

    public static File get(String path) {
        try {
            return new File(PATH + path);
        } catch (Exception e) {
            return null;
        }
    }
}
