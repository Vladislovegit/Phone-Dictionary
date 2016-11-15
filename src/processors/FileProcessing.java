package processors;

import java.io.*;
import java.util.ArrayList;

public class FileProcessing {

    public static String[] readAll(String filename) {
        File file = new File("resources/txt/" + filename + ".txt");
        ArrayList<String> list = new ArrayList<String>();
        if(!file.exists())
            throw new IllegalArgumentException("Wrong filename.");
        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    list.add(s);
                }
            }
            finally {
                in.close();
            }
        } catch (IOException ignored) {}

        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = list.get(i) + "\n";
        }
        return strings;
    }

    public static void write(String filename, ArrayList<String[]> strings) {
        File file = new File("resources/txt/" + filename + ".txt");
        if(!file.exists())
            throw new IllegalArgumentException("Wrong filename.");
        try {
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                String string;
                for (String[] args : strings) {
                    string = args[0] + " " + args[1] + " " + args[2] + ";" + args[3] + ";" + args[4];
                    out.print(string);
                }
            }
            finally {
                out.close();
            }
        } catch (IOException ignored) {}

    }
}
