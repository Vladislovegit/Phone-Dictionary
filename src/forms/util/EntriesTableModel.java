package forms.util;

import models.DirectoryEntry;
import processors.FileProcessing;
import processors.StringParser;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

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

    public ArrayList<String[]> getTableData() {
        return tableDataArrayList;
    }

    public ArrayList<String[]> getData() {
        return dataArrayList;
    }

    public int getRowCount() {
        return tableDataArrayList.size();
    }

    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = tableDataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public String[] getRow(int rowIndex) {
        String[] row = dataArrayList.get(rowIndex);
        return row;
    }

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

    public void remove(int index) {
        tableDataArrayList.remove(index);
        dataArrayList.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void update(DirectoryEntry entry, int index) {
        String[] rowSrc = getStrings(entry);
        tableDataArrayList.set(index, rowSrc);
        dataArrayList.set(index, changeDifferentFields(entry, index));
        fireTableRowsUpdated(index, index);
    }

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

    public void removeAll() {
        int size = tableDataArrayList.size();
        if (tableDataArrayList.size() > 0)
            for (int i = size - 1; i >= 0; i--) {
                tableDataArrayList.remove(i);
            }
        else return;
    }

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

    public int getRowNumber(DirectoryEntry entry) {
        String[] rowDest;
        String[] rowSrc = getStrings(entry);
        int index = 0;
        for (int i = 0; i < tableDataArrayList.size(); i++) {
            rowDest = tableDataArrayList.get(i);
            if (rowDest[0].compareTo(rowSrc[0]) == 0 &&
                    rowDest[1].compareTo(rowSrc[1]) == 0 &&
                    rowDest[2].compareTo(rowSrc[2]) == 0) {
                index = i;
                break;
            }
        }
        return index;
    }

    private String[] getStrings(DirectoryEntry entry) {
        String[] rowSrc = new String[getColumnCount()];
        rowSrc[0] = (entry.getLastName() + " " + entry.getFirstName().charAt(0) + ". " + entry.getPatronymic().charAt(0) + ".").toUpperCase();
        rowSrc[1] = entry.getAddress();
        rowSrc[2] = entry.getPhoneNumber();
        return rowSrc;
    }
}

