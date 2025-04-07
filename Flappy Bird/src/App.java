import javax.swing.*;

public class App {
    public App() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Buat objek JPanel FlappyBird
        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack(); // sesuaikan ukuran frame dengan preferensi panel
        frame.setLocationRelativeTo(null); // tetap di tengah setelah pack
        frame.setVisible(true); // baru ditampilkan setelah semua siap

        flappyBird.requestFocus(); // panel langsung fokus untuk input keyboard
    }
}
