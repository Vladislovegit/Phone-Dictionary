package forms;

import models.Parameters;
import processors.FileProcessing;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpForm extends JFrame {

    private JTextArea info;

    public HelpForm() {
        setTitle("Справка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Parameters.WIDTH, Parameters.HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Parameters.BACKGROUND_COLOR);
        panel.setLayout(null);

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

        JMenuBar menuBar = new JMenuBar();
        JMenu main = new JMenu("Главная");
        main.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MainForm form = new MainForm();
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

        info = new JTextArea("        Справка.\n\n");
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setBounds(10, 10, Parameters.WIDTH, Parameters.HEIGHT);
        info.setFont(Parameters.LABELS_FONT);
        fillTextAreaWithTextFromFile("help");

        menuBar.add(main);
        menuBar.add(edit);
        menuBar.add(author);
        menuBar.add(help);

        panel.add(exit);
        panel.add(info);
        setJMenuBar(menuBar);
        add(panel);
    }

    private void fillTextAreaWithTextFromFile(String filename) {
        String[] strings = FileProcessing.readAll(filename);
        for (String s : strings) {
            info.append(s);
        }
    }
}
