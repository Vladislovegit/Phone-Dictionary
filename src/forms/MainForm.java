package forms;

import models.EntriesTableModel;
import models.Parameters;
import processors.FileProcessing;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainForm extends JFrame {

    private static EntriesTableModel entriesTable;
    private JTable entries;
    private JComboBox comboBox;

    public MainForm() {
        buildGUI();
        showData();
    }

    private void buildGUI() {
        setTitle("Телефонный справочник");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Parameters.WIDTH, Parameters.HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Parameters.BACKGROUND_COLOR);
        panel.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu main = new JMenu("Главная");
        JMenu edit = new JMenu("Редактирование");
        edit.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                EditEntryForm form = new EditEntryForm();
                form.setVisible(true);
                dispose();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        JMenu author = new JMenu("Об авторе");
        author.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                AboutAuthorForm form = new AboutAuthorForm();
                form.setVisible(true);
                dispose();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        JMenu help = new JMenu("Справка");
        help.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                HelpForm form = new HelpForm();
                form.setVisible(true);
                dispose();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        menuBar.add(main);
        menuBar.add(edit);
        menuBar.add(author);
        menuBar.add(help);

        JButton exit = new JButton("Выход");
        exit.setBounds(320, 335, 75, 25);
        exit.setFont(Parameters.BUTTONS_FONT);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                System.exit(0);
            }
        });

        comboBox = new JComboBox(Parameters.DIRECTORIES);
        comboBox.setBounds(10, 5, 250, 25);
        comboBox.setEditable(false);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });

        entriesTable = new EntriesTableModel();
        entries = new JTable(entriesTable);
        JScrollPane scroll = new JScrollPane(entries);
        scroll.setBounds(10, 35, 375, 295);
        entries.setTableHeader(null);

        panel.add(exit);
        panel.add(scroll);
        panel.add(comboBox);
        setJMenuBar(menuBar);
        add(panel);
    }

    private void showData() {
        switch (comboBox.getSelectedIndex()) {
            case 0:
                entriesTable.showData("dir_vit");
                break;
            case 1:
                entriesTable.showData("dir_minsk");
                break;

        }
    }
}
