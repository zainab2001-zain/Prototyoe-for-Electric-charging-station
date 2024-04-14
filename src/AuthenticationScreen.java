import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthenticationScreen extends JPanel {
    private JTextField accountNumberField;
    private JPasswordField pinField;
    private JButton submitButton;
    private JLabel qrCodeLabel;
    private ImageIcon qrCodeImage;
    private JLabel scanLabel;
    private JDialog keyboardDialog;

    public AuthenticationScreen() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(600, 400));

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to the EV Public Charging Point");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(welcomeLabel);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Account Number Panel
        JPanel accountNumberPanel = new JPanel();
        accountNumberPanel.setLayout(new BoxLayout(accountNumberPanel, BoxLayout.Y_AXIS));
        accountNumberPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel accountNumberLabel = new JLabel("Enter Account Number:");
        accountNumberField = new JTextField(20);
        accountNumberPanel.add(accountNumberLabel);
        accountNumberPanel.add(accountNumberField);
        mainPanel.add(accountNumberPanel);

        // Add some space between fields
        mainPanel.add(Box.createHorizontalStrut(20));

        // QR Code Panel
        JPanel qrCodePanel = new JPanel();
        qrCodePanel.setLayout(new BoxLayout(qrCodePanel, BoxLayout.Y_AXIS));
        qrCodePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        qrCodeLabel = new JLabel();
        qrCodeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        qrCodePanel.add(qrCodeLabel);
        scanLabel = new JLabel("Scan your account here");
        scanLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        qrCodePanel.add(scanLabel);
        mainPanel.add(qrCodePanel);

        add(mainPanel);

        // PIN Panel
        JPanel pinPanel = new JPanel();
        pinPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel pinLabel = new JLabel("Enter PIN:");
        pinField = new JPasswordField(20);
        pinPanel.add(pinLabel);
        pinPanel.add(pinField);
        add(pinPanel);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(submitButton);

        // Add action listener to submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                char[] pinChars = pinField.getPassword();
                String pin = new String(pinChars); // Convert char array to string

                // Validate account number and PIN
                // For now, just print them to console
                System.out.println("Account Number: " + accountNumber);
                System.out.println("PIN: " + pin);

                // Open ChargingDetails screen
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AuthenticationScreen.this);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new ChargingDetails());
                frame.revalidate();
                frame.repaint();
            }
        });

        // Initialize keyboard dialog
        keyboardDialog = createKeyboardDialog();
        
        // Add focus listener to account number field
        accountNumberField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                keyboardDialog.setVisible(true);
            }
        });
    }

    private JDialog createKeyboardDialog() {
        JDialog dialog = new JDialog((Frame) null, "Keyboard", false);
        dialog.setLayout(new GridLayout(4, 10));
        String[] keyboardLayout = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L", "",
                "Z", "X", "C", "V", "B", "N", "M", "", "", ""
        };
        for (String key : keyboardLayout) {
            JButton button = new JButton(key);
            button.addActionListener(new KeyboardInputActionListener(key));
            dialog.add(button);
        }
        dialog.pack();
        return dialog;
    }

    private class KeyboardInputActionListener implements ActionListener {
        private String input;

        public KeyboardInputActionListener(String input) {
            this.input = input;
        }

        public void actionPerformed(ActionEvent e) {
            accountNumberField.setText(accountNumberField.getText() + input);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Authentication Screen");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.getContentPane().add(new AuthenticationScreen());
                frame.setVisible(true);
            }
        });
    }
}
