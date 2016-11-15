package forms;

import models.Parameters;
import processors.FileProcessing;

import javax.swing.*;
public class AboutAuthorForm extends JFrame {

    private JTextArea info;

    public AboutAuthorForm() {
        setTitle("Об авторе");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(Parameters.WIDTH, Parameters.HEIGHT - 200);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Parameters.BACKGROUND_COLOR);
        panel.setLayout(null);

        info = new JTextArea("        Об авторе.\n\n");
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setBounds(10, 10, Parameters.WIDTH, Parameters.HEIGHT);
        info.setFont(Parameters.LABELS_FONT);
        fillTextAreaWithTextFromFile("author");

        panel.add(info);
        add(panel);
    }

    private void fillTextAreaWithTextFromFile(String filename) {
        String[] strings = FileProcessing.readAll(filename);
        for (String s : strings) {
            info.append(s);
        }
    }
}
