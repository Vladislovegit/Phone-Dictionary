package forms;

import forms.util.Parameters;
import processors.FileProcessing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AboutAuthorForm extends JFrame {

    private static final AboutAuthorForm instance = new AboutAuthorForm();

    public static AboutAuthorForm getInstance() {
        return instance;
    }

    private JTextArea info;

    private AboutAuthorForm() {
        setTitle("Об авторе");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
        info.setBounds(170, 10, Parameters.WIDTH - 200, Parameters.HEIGHT);
        info.setFont(Parameters.LABELS_FONT);
        fillTextAreaWithTextFromFile("author");

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/img/author.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, 150, 150);

        panel.add(label);
        panel.add(info);
        add(panel);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(panel,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    dispose();
                    MainForm form = MainForm.getInstance();
                    if (!form.isEnabled()) {
                        form.setEnabled(true);
                    } else {
                        EditEntryForm editEntryForm = EditEntryForm.getInstance();
                        editEntryForm.setEnabled(true);
                    }
                }
            }
        });
    }

    private void fillTextAreaWithTextFromFile(String filename) {
        String[] strings = FileProcessing.readAll(filename);
        for (String s : strings) {
            info.append(s);
        }
    }
}
