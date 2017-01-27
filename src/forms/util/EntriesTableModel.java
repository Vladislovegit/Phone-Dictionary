package forms.util;

import models.DirectoryEntry;
import processors.FileProcessing;
import processors.StringParser;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Класс, описывающий модель поведения таблицы
 */
public class EntriesTableModel extends AbstractTableModel {
    private final static int COLUMN_COUNT = 3;

    private ArrayList<String[]> tableDataArrayList;
    private ArrayList<String[]> dataArrayList;

    public EntriesTableModel() {
        tableDataArrayList = new ArrayList<String[]>();
        for (int i = 0; i < tableDataArrayList.size(); i++) {
            tableDataArrayList.add(new String[(getColumnCount())]);
        }
        dataArrayList = new ArrayList<String[]>();
    }

    public ArrayList<String[]> getData() {
        return dataArrayList;
    }

    /**
     * Метод, возвращающий количество строк в таблице
     * @return количество строк в таблице
     */
    public int getRowCount() {
        return tableDataArrayList.size();
    }

    /**
     * Метод, возвращающий количество столбцов в таблице
     * @return количество столбцов в таблице
     */
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    /**
     * Метод, для получение данных из определенной позиции таблицы
     * @param rowIndex номер строки таблицы
     * @param columnIndex номер столбца таблицы
     * @return искомые данные
     */
    public String getValueAt(int rowIndex, int columnIndex) {
        String[] rows = tableDataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    /**
     * Метод для получения всех данных определенной строки
     * @param rowIndex номер строки
     * @return массив строк, содержащий искомые данные
     */
    public String[] getRow(int rowIndex) {
        return dataArrayList.get(rowIndex);
    }

    /**
     * Метод для добавления в таблице новой записи
     * @param entry искомая запись
     */
    public void add(DirectoryEntry entry) {
        dataArrayList.add(new String[] {
                entry.getFirstName(),
                entry.getLastName(),
                entry.getPatronymic(),
                entry.getAddress(),
                entry.getPhoneNumber()
        });
        String[] args = getStrings(entry);
        tableDataArrayList.add(args);
        fireTableDataChanged();
    }

    /**
     * Метод для удаление записи из таблицы
     * @param index номер запии
     */
    public void remove(int index) {
        tableDataArrayList.remove(index);
        dataArrayList.remove(index);
        fireTableRowsDeleted(index, index);
    }

    /**
     * Метод для обновления определенной записи в таблице
     * @param entry запись с обновленными данными
     * @param index позиция записи в таблице
     */
    public void update(DirectoryEntry entry, int index) {
        String[] rowSrc = getStrings(entry);
        tableDataArrayList.set(index, rowSrc);
        dataArrayList.set(index, changeDifferentFields(entry, index));
        fireTableRowsUpdated(index, index);
    }

    /**
     * Метод, который проверяет даныые на идентичность, в отличном случае заменяет
     * @param entry данные, которые будут сравниваться
     * @param index позиция в таблице данных, с которыми будет происходить сравнение
     * @return результат сравнения
     */
    private String[] changeDifferentFields(DirectoryEntry entry, int index) {
        String[] strings = dataArrayList.get(index);
        if(!strings[0].equals(entry.getFirstName()))
            strings[0] = entry.getFirstName();
        if(!strings[1].equals(entry.getLastName()))
            strings[1] = entry.getLastName();
        if(!strings[2].equals(entry.getPatronymic()))
            strings[2] = entry.getPatronymic();
        if(!strings[3].equals(entry.getAddress()))
            strings[3] = entry.getAddress();
        if(!strings[4].equals(entry.getPhoneNumber()))
            strings[4] = entry.getPhoneNumber();
        return strings;
    }

    /**
     * Метод для удаления всех записей из таблицы
     */
    public void removeAll() {
        int size = tableDataArrayList.size();
        if (tableDataArrayList.size() > 0)
            for (int i = size - 1; i >= 0; i--) {
                tableDataArrayList.remove(i);
            }
        else return;
    }

    /**
     * Метод для приведения данных в подобающий для отображения вид
     * @param filename имя файла, из которого беруться данные
     */
    public void showData(String filename) {
        ArrayList<DirectoryEntry> result = new ArrayList<DirectoryEntry>();
        dataArrayList.clear();
        String[] strings = FileProcessing.readAll(filename);
        String[] args;
        String[] fullName;
        for (String string : strings) {
            args = StringParser.rowParser(string);
            fullName = StringParser.nameParser(args[0]);
            result.add(new DirectoryEntry(fullName[0], fullName[1], fullName[2], args[1], args[2]));
        }
        removeAll();
        for (DirectoryEntry aResult : result) add(aResult);
        fireTableDataChanged();
    }

    /**
     * Метод для преобазования записи DirectoryEntry в массив строк
     * @param entry запись для преобразования
     * @return полученный массив строк
     */
    private String[] getStrings(DirectoryEntry entry) {
        String[] rowSrc = new String[getColumnCount()];
        rowSrc[0] = (entry.getLastName() + " " + entry.getFirstName().charAt(0) + ". " + entry.getPatronymic().charAt(0) + ".").toUpperCase();
        rowSrc[1] = entry.getAddress();
        rowSrc[2] = entry.getPhoneNumber();
        return rowSrc;
    }
}

