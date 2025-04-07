import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    boolean gameOver = false; // fitur Game Over

    JLabel scoreLabel; // label untuk skor
    int score = 0; // nilai skor

    boolean pipeCounted = false; // penanda agar skor hanya bertambah sekali per pasangan pipa

    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setBackground(Color.blue);
        setLayout(null); // agar bisa mengatur posisi absolut komponen (JLabel)
        addKeyListener(this);
        setFocusable(true);

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        scoreLabel = new JLabel("Score: 0"); // label skor
        scoreLabel.setBounds(10, 10, 100, 30);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel);

        initGame(); // inisialisasi awal game
    }

    public void initGame() { // method baru untuk inisialisasi ulang game
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<>();
        gameOver = false;
        score = 0;
        scoreLabel.setText("Score: 0");

        if (pipesCooldown != null) pipesCooldown.stop();
        pipesCooldown = new Timer(3000, e -> placePipes());
        pipesCooldown.start();

        if (gameLoop != null) gameLoop.stop();
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        if (gameOver) { // tampilkan teks Game Over
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over", 80, frameHeight / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Press 'R' to Restart", 90, frameHeight / 2 + 40);
        }
    }

    public void move() {
        if (gameOver) return; // jangan gerakkan apapun jika game over

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            if (checkCollision(player, pipe)) {
                gameOver();
            }

            // Skor hanya dihitung di upper pipe yang belum dihitung sebelumnya
            if (i % 2 == 0 && !pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                score++;
                pipe.setPassed(true); // tandai upper pipe ini sudah dilewati
                scoreLabel.setText("Score: " + score);
            }
        }

        if (player.getPosY() + player.getHeight() >= frameHeight) { // jatuh ke bawah
            gameOver();
        }
    }

    private boolean checkCollision(Player p, Pipe pipe) { // method baru untuk cek tabrakan
        Rectangle playerRect = new Rectangle(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
        return playerRect.intersects(pipeRect);
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + pipeHeight + openingSpace), pipeWidth, pipeHeight, lowerPipeImage);

        pipes.add(upperPipe);
        pipes.add(lowerPipe);

        pipeCounted = false; // reset hitung skor tiap pasangan pipa baru
    }

    private void gameOver() { // method baru untuk menghentikan game
        gameOver = true;
        gameLoop.stop();
        pipesCooldown.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            pipes.clear(); // reset semua pipa
            initGame(); // mulai ulang game
        }

        if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10); // terbang ke atas
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
