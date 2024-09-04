import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Hangman extends JFrame {
    private JPanel panel;
    private final JButton[] letterButtons = new JButton[27];
    private JTextField numberOfAttempts, textField;
    private String[] word;
    private int numberOfErrors, randomNumber;

    private final String[] alphabet = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    private final String[] secretWords = {
            "FLUVIAL", "TUQUEQUE", "BERENJENA", "FESTIVAL", "CHOZA",
            "LECHUZA", "TRANVIA", "MILENIO", "MANANTIAL", "AJEDREZ"
    };

    public Hangman() {
        setComponents();

        setTitle("El ahorcado");
        setSize(525,550);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Image miIcono = miPantalla.getImage("assets/hangman.png");
        setIconImage(miIcono);

        this.numberOfAttempts.setText("6");
        this.newGame();
    }

    private void newGame() {
        this.numberOfErrors = 0;
        this.textField.setText("");

        for (int i = 0; i < 27; i++) {
            letterButtons[i].setEnabled(true);
        }

        this.randomNumber = (int) (Math.random() * ((secretWords.length - 1) + 1));

        String lettersOfWord = secretWords[this.randomNumber];

        this.word = new String[secretWords[this.randomNumber].length()];

        for (int i = 0; i < lettersOfWord.length(); i++) {
            this.textField.setText(this.textField.getText() + "_" + " ");
            this.word[i] = "_";
        }
    }

    private void setPanel() {
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBackground(Color.WHITE);
        this.add(this.panel);
    }

    private void setButtons() {
        JButton resetButton = new JButton("Reiniciar");
        resetButton.setBounds(200,235,150,30);
        resetButton.setFont(new Font("Consolas", Font.PLAIN, 18));
        resetButton.setBackground(Color.WHITE);
        this.panel.add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                resetAction(evt);
            }

            private void resetAction(ActionEvent evt) {
                numberOfAttempts.setText("6");
                newGame();
            }
        });

        JButton quitButton = new JButton("Salir");
        quitButton.setBounds(230,320,100,25);
        quitButton.setFont(new Font("Consolas", Font.PLAIN, 14));
        quitButton.setBackground(Color.WHITE);
        this.panel.add(quitButton);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del juego? Perderás el progreso realizado.",
                        "El Ahorcado", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        int j = 0, k = 0;
        for (int i = 0; i < 27; i++) {
            this.letterButtons[i] = new JButton(alphabet[i]);
            this.letterButtons[i].setBounds(10 + (55 * j++), 30 + (55 * k), 50, 50);
            this.letterButtons[i].setBackground(Color.WHITE);
            this.letterButtons[i].setFont(new Font("Consolas", Font.PLAIN, 18));
            this.letterButtons[i].setForeground(Color.BLACK);

            if (j == 9) j = 0;
            if (i == 8 || i == 17) k++;

            this.panel.add(this.letterButtons[i]);
        }

        for (int i = 0; i < 27; i++) {
            this.letterButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    verificarLetra(e);
                }
            });
        }
    }

    public void verificarLetra(ActionEvent e) {
        JButton letterButtonSelected = (JButton) e.getSource();
        String textSelected = ((JButton) e.getSource()).getText();
        char letterSelected = textSelected.charAt(0);

        boolean hasLetterFound = false;
        boolean isFinished = true;

        for (int i = 0; i < secretWords[this.randomNumber].length(); i++) {
            if (letterSelected == secretWords[this.randomNumber].charAt(i)) {
                this.word[i] = String.valueOf(letterSelected);
                hasLetterFound = true;
            }
        }

        if (hasLetterFound) {
            for (int i = 0; i < this.word.length; i++) {
                String[] currentWord = this.textField.getText().split(" ");
                currentWord[i] = this.word[i];
                this.textField.setText(String.join(" ", currentWord));
            }

            isFinished = !this.textField.getText().contains("_");

            if (isFinished) {
                JOptionPane.showMessageDialog(null,"Felicitaciones. Has ganado la partida.", "El Ahorcado", JOptionPane.INFORMATION_MESSAGE);
                this.numberOfAttempts.setText("6");
                this.newGame();
                return;
            }
        } else {
            this.numberOfErrors++;
            this.numberOfAttempts.setText(""+(6 - numberOfErrors));
        }

        if (numberOfErrors == 6) {
            JOptionPane.showMessageDialog(null, "Has perdido la partida. Inténtalo de nuevo.", "El Ahorcado", JOptionPane.INFORMATION_MESSAGE);
            this.numberOfAttempts.setText("6");
            this.newGame();
            return;
        }

        letterButtonSelected.setEnabled(false);
    }

    private void setTextField() {
        this.textField = new JTextField();
        this.textField.setBounds(150,280,250,30);
        this.panel.add(this.textField);
        this.textField.setEditable(false);
        this.textField.setBackground(Color.WHITE);
        this.textField.setHorizontalAlignment(JTextField.CENTER);
        this.textField.setFont(new Font("Consolas", Font.PLAIN, 18));

        this.numberOfAttempts = new JTextField();
        this.numberOfAttempts.setBounds(370,460,30,30);
        this.panel.add(this.numberOfAttempts);
        this.numberOfAttempts.setEditable(false);
        this.numberOfAttempts.setBackground(Color.WHITE);
        this.numberOfAttempts.setHorizontalAlignment(JTextField.CENTER);
        this.numberOfAttempts.setFont(new Font("Consolas", Font.PLAIN, 18));
    }

    private void setLabels() {
        JLabel[] labels = new JLabel[4];
        String[] wordsForLabels = {"¿A qué no adivinas la palabra", "en menos de 6 intentos? :P", "Te restan", "intentos"};

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setText(wordsForLabels[i]);
            labels[i].setFont(new Font("Consolas", Font.PLAIN, 12));
        }

        labels[0].setBounds(20, 450, 250, 50);
        labels[1].setBounds(20, 460, 250, 50);
        labels[2].setBounds(300, 455, 80, 50);
        labels[3].setBounds(405, 455, 100, 50);

        for (JLabel label : labels) {
            this.panel.add(label);
        }
    }

    private void setComponents() {
        this.setPanel();
        this.setLabels();
        this.setButtons();
        this.setTextField();
    }
}