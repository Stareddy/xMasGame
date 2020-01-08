package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class GameJFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    JTextField jTextField = new JTextField();
    JTextField currentTime = new JTextField();
    JButton startStopGame = new JButton();
    JButton nextWord = new JButton();

    GameJFrame(String title) {
        super(title);
        setBackground(Color.GREEN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(-10, -5, 1960, 1060);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 2, 0, 0));

        GameFrameHandler gameFrameHandler = new GameFrameHandler(this);


        JLabel lblJaraspi = new JLabel("Frohe Weihnachten");
        contentPane.add(lblJaraspi);
        lblJaraspi.setHorizontalAlignment(SwingConstants.CENTER);
        lblJaraspi.setFont(new Font("Serif", Font.BOLD, 60));

        JLabel lblNewLabel = new JLabel("25. Dezember 2019");
        contentPane.add(lblNewLabel);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Serif", Font.BOLD, 60));

        contentPane.add(jTextField);
        jTextField.setColumns(10);
        jTextField.setBackground(SystemColor.menu);
        jTextField.setHorizontalAlignment(SwingConstants.CENTER);
        jTextField.setFont(new Font("Serif", Font.BOLD, 60));
        jTextField.setEditable(false);

        contentPane.add(currentTime);
        currentTime.setColumns(10);
        currentTime.setBackground(SystemColor.menu);
        currentTime.setHorizontalAlignment(SwingConstants.CENTER);
        currentTime.setFont(new Font("Serif", Font.BOLD, 60));
        currentTime.setEditable(false);

        startStopGame.setText("Spiel starten");
        contentPane.add(startStopGame);
        startStopGame.setFont(new Font("Serif", Font.BOLD, 25));
        startStopGame.addActionListener(gameFrameHandler);

        nextWord.setText("Nächstes Wort");
        contentPane.add(nextWord);
        nextWord.setEnabled(false);
        nextWord.setFont(new Font("Serif", Font.BOLD, 25));
        nextWord.addActionListener(gameFrameHandler);

        startStopGame.addActionListener(listener -> {
            startStopGame.setForeground(Color.BLACK);
            startStopGame.setBackground(Color.RED);
            startStopGame.setText("Spiel beenden");
            startStopGame.setFocusable(false);
            nextWord.setBackground(Color.GREEN);
//            nextWord.setFocusable(false);
            nextWord.setEnabled(true);
        });

        nextWord.addActionListener(listener -> nextWord.setFocusable(true));
    }
}
