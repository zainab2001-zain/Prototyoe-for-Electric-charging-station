import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class WelcomeScreen extends JPanel {
    private JLabel welcomeLabel;
    private Image backgroundImage;

    public WelcomeScreen() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 500));

        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("D:\\Freelance\\hci assignemnt\\game.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(Box.createVerticalStrut(0)); // Adjust the height as needed
        // Welcome Label
        welcomeLabel = new JLabel("EV Public Charging Point");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
       // welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(welcomeLabel);

        // Animate welcome message and transition to authentication screen after 5 seconds
        Timer timer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(WelcomeScreen.this);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new AuthenticationScreen());
                frame.revalidate();
                frame.repaint();
            }
        });
        timer.setRepeats(false); // Set to execute only once
        timer.start();
    }

    // Override paintComponent to draw background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Welcome Screen");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 500);
                frame.setLocationRelativeTo(null);
                frame.getContentPane().add(new WelcomeScreen());
                frame.setVisible(true);
            }
        });
    }
}
