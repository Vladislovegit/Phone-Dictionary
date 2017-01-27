package processors;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс для работы с файлами
 */
public class FileProcessing {

    /**
     * Метод, читающий всю информацию из файла
     * @param filename исходное название файла
     * @return массив строк, содержащий информацию, находящуюся в файле
     */
    public static String[] readAll(String filename) {
        File file = new File("resources/txt/" + filename + ".txt");
        ArrayList<String> list = new ArrayList<String>();
        if(!file.exists())
            throw new IllegalArgumentException("Wrong filename.");
        try {
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                String s;
                while ((s = in.readLine()) != null) {
                    list.add(s);
                }
            }
        } catch (IOException ignored) {}

        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = list.get(i) + "\n";
        }
        return strings;
    }

    /**
     * Метод для записи в файл
     * @param filename имя файл, в который будет происходить запись
     * @param strings строки для записи
     */
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
