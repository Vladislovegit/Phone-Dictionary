package forms;

import models.DirectoryEntry;
import models.EntriesTableModel;
import models.Parameters;
import processors.FileProcessing;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditEntryForm extends JFrame {

    private JButton add, delete, editBtn, ok;

    private static EntriesTableModel entriesTable;
    private JTable entries;
    private JComboBox comboBox;
    private JTextField firstNameTextField, lastNameTextField, patronymicTextField, addressTextField, phoneNumberTextField;
    private int index;

    public EditEntryForm() {
        buildGUI();

        delete.setEnabled(false);
        editBtn.setEnabled(false);
        showData();
    }

    private void buildGUI() {
        setTitle("Редактирование");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Parameters.WIDTH + 400, Parameters.HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Parameters.BACKGROUND_COLOR);
        panel.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu main = new JMenu("Главная");
        main.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MainForm form = new MainForm();
                form.setVisible(true);
                saveData();
                dispose();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        final JMenu edit = new JMenu("Редактирование");
        JMenu author = new JMenu("Об авторе");
        author.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                AboutAuthorForm form = new AboutAuthorForm();
                form.setVisible(true);
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

        JLabel firstName = new JLabel("Имя: ");
        firstName.setBounds(390, 60, 375, 20);
        firstName.setFont(Parameters.LABELS_FONT);
        firstNameTextField = new JTextField();
        firstNameTextField.setBounds(520, 60, 250, 25);
        firstNameTextField.setFont(Parameters.LABELS_FONT);

        JLabel lastName = new JLabel("Фамилия: ");
        lastName.setBounds(390, 35, 375, 20);
        lastName.setFont(Parameters.LABELS_FONT);
        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(520, 35, 250, 25);
        lastNameTextField.setFont(Parameters.LABELS_FONT);

        JLabel patronymic = new JLabel("Отчество: ");
        patronymic.setBounds(390, 85, 375, 20);
        patronymic.setFont(Parameters.LABELS_FONT);
        patronymicTextField = new JTextField();
        patronymicTextField.setBounds(520, 85, 250, 25);
        patronymicTextField.setFont(Parameters.LABELS_FONT);

        JLabel address = new JLabel("Адрес: ");
        address.setBounds(390, 110, 375, 20);
        address.setFont(Parameters.LABELS_FONT);
        addressTextField = new JTextField();
        addressTextField.setBounds(520, 110, 250, 25);
        addressTextField.setFont(Parameters.LABELS_FONT);

        JLabel phoneNumber = new JLabel("Номер телефона: ");
        phoneNumber.setBounds(390, 135, 375, 20);
        phoneNumber.setFont(Parameters.LABELS_FONT);
        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(520, 135, 250, 25);
        phoneNumberTextField.setFont(Parameters.LABELS_FONT);

        delete = new JButton("Удалить");
        delete.setBounds(5, 335, 150, 25);
        delete.setFont(Parameters.BUTTONS_FONT);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entriesTable.remove(entries.getSelectedRow());
                saveData();
            }
        });

        add = new JButton("Добавить");
        add.setBounds(380, 180, 150, 25);
        add.setFont(Parameters.BUTTONS_FONT);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isFieldsValid()) {
                    entriesTable.add(new DirectoryEntry(
                            firstNameTextField.getText(),
                            lastNameTextField.getText(),
                            patronymicTextField.getText(),
                            addressTextField.getText(),
                            phoneNumberTextField.getText() + "\n"));
                    resetStrings();
                    saveData();
                }
            }
        });

        editBtn = new JButton("Редактировать");
        editBtn.setBounds(240, 335, 150, 25);
        editBtn.setFont(Parameters.BUTTONS_FONT);
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                index = entries.getSelectedRow();
                String[] args = entriesTable.getRow(index);
                setStrings(args);
                ok.setVisible(true);
            }
        });

        ok = new JButton("Готово");
        ok.setVisible(false);
        ok.setBounds(620, 180, 150, 25);
        ok.setFont(Parameters.BUTTONS_FONT);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DirectoryEntry entry = new DirectoryEntry(
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        patronymicTextField.getText(),
                        addressTextField.getText(),
                        phoneNumberTextField.getText() + "\n");
                entriesTable.update(entry, index);
                resetStrings();
                saveData();
                ok.setVisible(false);
            }
        });


        JButton exit = new JButton("Выход");
        exit.setBounds(650, 335, 150, 25);
        exit.setFont(Parameters.BUTTONS_FONT);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveData();
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
        entries.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                delete.setEnabled(true);
                editBtn.setEnabled(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                delete.setEnabled(false);
                editBtn.setEnabled(false);
            }
        });

        panel.add(exit);
        panel.add(scroll);
        panel.add(comboBox);
        panel.add(phoneNumber);
        panel.add(phoneNumberTextField);
        panel.add(firstName);
        panel.add(lastName);
        panel.add(patronymic);
        panel.add(firstNameTextField);
        panel.add(lastNameTextField);
        panel.add(patronymicTextField);
        panel.add(address);
        panel.add(addressTextField);
        panel.add(add);
        panel.add(editBtn);
        panel.add(delete);
        panel.add(ok);
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

    private void resetStrings() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        patronymicTextField.setText("");
        addressTextField.setText("");
        phoneNumberTextField.setText("");
    }

    private void setStrings(String[] args) {
        firstNameTextField.setText(args[0]);
        lastNameTextField.setText(args[1]);
        patronymicTextField.setText(args[2]);
        addressTextField.setText(args[3]);
        phoneNumberTextField.setText(args[4]);
    }

    private void saveData() {
        String filename;
        switch (comboBox.getSelectedIndex()) {
            case 0:
                filename = "dir_vit";
                break;
            case 1:
                filename = "dir_minsk";
                break;
            default:
                filename = "dir_new";
        }
        FileProcessing.write(filename, entriesTable.getData());
    }

    private boolean isFieldsValid() {
        return !firstNameTextField.getText().equals("") &&
                !addressTextField.getText().equals("") &&
                !phoneNumberTextField.getText().equals("");
    }
}
