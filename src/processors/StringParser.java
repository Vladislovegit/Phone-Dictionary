package processors;

public class StringParser {
    public static String[] rowParser(String src) {
        return src.split(";");
    }

    public static String[] nameParser(String fullname) {
        return fullname.split(" ");
    }
}
