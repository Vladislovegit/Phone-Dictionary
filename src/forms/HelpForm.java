package forms;

import forms.util.Parameters;
import processors.FileProcessing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HelpForm extends JDialog {

    private static final HelpForm instance = new HelpForm();
    private JTextArea info;

    public static HelpForm getInstance() {
        return instance;
    }

    private HelpForm() {
        setTitle("Справка");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(Parameters.WIDTH, Parameters.HEIGHT - 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        JPanel panel = new JPanel();
        panel.setBackground(Parameters.BACKGROUND_COLOR);
        panel.setLayout(null);

        info = new JTextArea("        Справка.\n\n");
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setBounds(220, 10, Parameters.WIDTH - 170, Parameters.HEIGHT);
        info.setFont(Parameters.LABELS_FONT);
        fillTextAreaWithTextFromFile("help");

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/img/help.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, 200, 200);

        panel.add(label);
        panel.add(info);
        add(panel);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(panel,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    dispose();
                    MainForm form = MainForm.getInstance();
                    if (!form.isEnabled()) {
                        form.setEnabled(true);
                    }
                    else {
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
