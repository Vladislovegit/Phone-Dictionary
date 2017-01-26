package forms;

import forms.util.Parameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GreetingForm extends JFrame {

    private static final GreetingForm instance = new GreetingForm();

    public static GreetingForm getInstance() {
        return instance;
    }

    private GreetingForm() {
        setTitle("Приветствие");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Parameters.WIDTH - 100, Parameters.HEIGHT - 150);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/img/greeting.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, Parameters.WIDTH - 100, Parameters.HEIGHT - 150);

        setContentPane(label);

        setLayout(new FlowLayout());

        JButton enter = new JButton("Войти");
        enter.setPreferredSize(new Dimension(125, 45));
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
        exit.setPreferredSize(new Dimension(125, 45));
        exit.setFont(Parameters.BUTTONS_FONT);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                System.exit(0);
            }
        });

        JTextArea info = new JTextArea("\tДобро пожаловать.\n\n");
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setPreferredSize(new Dimension(Parameters.WIDTH, Parameters.HEIGHT - 250));
        info.setFont(Parameters.LABELS_FONT);
        info.setOpaque(false);

        add(info);
        add(enter, BOTTOM_ALIGNMENT);
        add(exit, BOTTOM_ALIGNMENT);
    }
}
