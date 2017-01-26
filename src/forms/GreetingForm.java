package forms;

import forms.util.Parameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GreetingForm extends JFrame {

    private static final GreetingForm instance = new GreetingForm();

    private JTextArea info;

    public static GreetingForm getInstance() {
        return instance;
    }

    private GreetingForm() {
        setTitle("Приветствие");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Parameters.WIDTH - 100, Parameters.HEIGHT - 150);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Parameters.BACKGROUND_COLOR);
        panel.setLayout(null);

        JButton enter = new JButton("Войти");
        enter.setBounds(87, 150, 125, 45);
        enter.setFont(Parameters.BUTTONS_FONT);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainForm form = MainForm.getInstance();
                form.setVisible(true);
            }
        });

        JButton exit = new JButton("Выйти");
        exit.setBounds(100, 200, 100, 25);
        exit.setFont(Parameters.BUTTONS_FONT);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                System.exit(0);
            }
        });

        info = new JTextArea(" Добро пожаловать в самый лучший в мире телефонный справочник (нет).\n\n");
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setBounds(110, 0, Parameters.WIDTH - 200, Parameters.HEIGHT - 300);
        info.setFont(Parameters.LABELS_FONT);


        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/img/greeting.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, 100, 100);

        panel.add(label);
        panel.add(info);
        panel.add(enter);
        panel.add(exit);
        add(panel);
    }
}
