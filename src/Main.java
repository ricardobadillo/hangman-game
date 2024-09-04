import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame {
    private JPanel panel;

    public static void main(String[] args) {
        Main principal = new Main();
        principal.setVisible(true);
    }

    public Main() {
        setTitle("El ahorcado");
        setSize(370,350);
        ImageIcon icon = new ImageIcon("assets/hangman.png");
        setIconImage(icon.getImage());

        setComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setComponents() {
        this.setPanel();
        this.setButtons();
        this.setLabels();
        this.setImage();
    }

    private void setPanel() {
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBackground(Color.WHITE);
        this.add(this.panel);
    }

    private void setButtons() {
        JButton gameButton = new JButton("Jugar");
        gameButton.setBounds(20,30,100,25);
        gameButton.setBackground(Color.WHITE);
        gameButton.setFont(new java.awt.Font("Consolas", Font.PLAIN, 14));
        gameButton.setForeground(Color.BLACK);
        this.panel.add(gameButton);

        gameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Hangman ahorcado = new Hangman();
                ahorcado.setVisible(true);
            }
        });

        JButton authorButton = new JButton("Autor");
        authorButton.setBounds(125,30,100,25);
        authorButton.setBackground(Color.WHITE);
        authorButton.setFont(new java.awt.Font("Consolas", Font.PLAIN, 14));
        authorButton.setForeground(Color.BLACK);
        this.panel.add(authorButton);

        authorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Creado por Ricardo Badillo", "El Ahorcado", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton quitButton = new JButton("Salir");
        quitButton.setBounds(230,30,100,25);
        quitButton.setBackground(Color.WHITE);
        quitButton.setFont(new java.awt.Font("Consolas", Font.PLAIN, 14));
        quitButton.setForeground(Color.BLACK);
        this.panel.add(quitButton);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir de la aplicación?",
                        "El Ahorcado", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    private void setLabels() {
        JLabel title = new JLabel();
        title.setText("El Ahorcado");
        title.setBounds(110,60,200,100);
        title.setFont(new java.awt.Font("Consolas", Font.PLAIN, 20));
        this.panel.add(title);
    }

    private void setImage() {
        ImageIcon imagen = new ImageIcon("assets/hangman.png");
        JLabel logo = new JLabel(imagen);
        logo.setBounds(45, 75,250,250);
        this.panel.add(logo);
    }
}