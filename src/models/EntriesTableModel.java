package models;

import processors.FileProcessing;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class EntriesTableModel extends AbstractTableModel {
    private final static int COLUMN_COUNT = 3;

    private ArrayList<String[]> dataArrayList;

    public EntriesTableModel() {
        dataArrayList = new ArrayList<String[]>();
        for (int i = 0; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[(getColumnCount())]);
        }
    }

    public ArrayList<String[]> getData() {
        return dataArrayList;
    }

    public int getRowCount() {
        return dataArrayList.size();
    }

    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public String[] getRow(int rowIndex) {
        String[] row = dataArrayList.get(rowIndex);
        return row;
    }

    public void add(DirectoryEntry entry) {
        String[] rowTable = getStrings(entry);
        dataArrayList.add(rowTable);
        fireTableDataChanged();
    }

    public void remove(DirectoryEntry entry) {
        int index = getRowNumber(entry);
        dataArrayList.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void remove(int index) {
        dataArrayList.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void update(DirectoryEntry entry, int index) {
        String[] rowSrc = getStrings(entry);
        dataArrayList.set(index, rowSrc);
        fireTableRowsUpdated(index, index);
    }

    public void removeAll() {
        int size = dataArrayList.size();
        if (dataArrayList.size() > 0)
            for (int i = size - 1; i >= 0; i--) {
                dataArrayList.remove(i);
            }
        else return;
    }

    public void showData(String filename) {
        ArrayList<DirectoryEntry> result = new ArrayList<DirectoryEntry>();
        String[] strings = FileProcessing.readAll(filename);
        String[] args;
        for (int i = 0; i < strings.length; i++) {
            args = strings[i].split(";");
            result.add(new DirectoryEntry(args[0], args[1], args[2]));
        }
        removeAll();
        for (DirectoryEntry aResult : result) add(aResult);
        fireTableDataChanged();
    }

    public int getRowNumber(DirectoryEntry entry) {
        String[] rowDest;
        String[] rowSrc = getStrings(entry);
        int index = 0;
        for (int i = 0; i < dataArrayList.size(); i++) {
            rowDest = dataArrayList.get(i);
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
        rowSrc[0] = entry.getFullName();
        rowSrc[1] = entry.getAddress();
        rowSrc[2] = entry.getPhoneNumber();
        return rowSrc;
    }
}

