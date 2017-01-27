package processors;

/**
 * Класса для обработки строк
 */
public class StringParser {
    /**
     * Метод для получения информации из строки таблицы, в которой данные разделены ";"
     * @param src исходная строка таблица
     * @return массив полученных строк (информации)
     */
    public static String[] rowParser(String src) {
        return src.split(";");
    }

    /**
     * Метод для получения раздельно имени, фамилии и отчества из строки, содержащей все три слова, разделенных " "
     * @param fullname полное имя
     * @return массив, содержащий раздельно имя, фамилию и отчество
     */
    public static String[] nameParser(String fullname) {
        return fullname.split(" ");
    }
}
