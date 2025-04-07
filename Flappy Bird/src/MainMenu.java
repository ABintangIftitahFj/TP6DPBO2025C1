import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Flappy Bird - Main Menu");
        setSize(360, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel utama
        JPanel panel = new JPanel() {
            Image bg = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null); // pakai layout bebas

        // Judul
        JLabel title = new JLabel("FLAPPY BIRD", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(40, 100, 280, 50);
        panel.add(title);

        // Tombol Start
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(110, 300, 140, 40);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(startButton);

        // Aksi tombol
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // tutup menu
                new App(); // buka game
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
