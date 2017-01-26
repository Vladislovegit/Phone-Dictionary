package forms;

import forms.util.EntriesTableModel;
import forms.util.Parameters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainForm extends JFrame {

    private static MainForm instance = new MainForm();

    private static EntriesTableModel entriesTable;
    private JTable entries;
    private JComboBox comboBox;

    private MainForm() {
        buildGUI();
        showData();
    }

    public static MainForm getInstance() {
        return instance;
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
        edit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                EditEntryForm form = EditEntryForm.getInstance();
                form.setVisible(true);
                dispose();

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        JMenu author = new JMenu("Об авторе");
        author.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                AboutAuthorForm form = AboutAuthorForm.getInstance();
                form.setVisible(true);
                setEnabled(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        JMenu help = new JMenu("Справка");
        help.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                HelpForm form = HelpForm.getInstance();
                form.setVisible(true);
                setEnabled(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
