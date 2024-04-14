import javax.swing.*;
import java.awt.*;

public class ChargingDetails extends JPanel {
    public ChargingDetails() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Charging Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Add charging details components here
    }
}
