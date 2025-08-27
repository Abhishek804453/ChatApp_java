package chatapplication;

import java.awt.*;
import javax.swing.*;

public class FrontPage extends JFrame {
    public FrontPage() {
        setTitle("🌈 Colorful Chat Application");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(135, 206, 250)); // SkyBlue

        JLabel title = new JLabel("Welcome to Chat App");
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        title.setForeground(new Color(128, 0, 128)); // Purple

        JButton serverBtn = new JButton("🚀 Start Server");
        serverBtn.setBackground(new Color(60, 179, 113)); // Green
        serverBtn.setForeground(Color.WHITE);
        serverBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JButton clientBtn = new JButton("💻 Start Client");
        clientBtn.setBackground(new Color(255, 99, 71)); // Tomato
        clientBtn.setForeground(Color.WHITE);
        clientBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JButton exitBtn = new JButton("❌ Exit");
        exitBtn.setBackground(Color.DARK_GRAY);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFont(new Font("Arial", Font.BOLD, 14));

        serverBtn.addActionListener(e -> {
            dispose(); // close front page
            ChatApp.main(new String[] {});
        });

        clientBtn.addActionListener(e -> {
            dispose();
            ChatClient.main(new String[] {});
        });

        exitBtn.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);
        gbc.gridy++;
        panel.add(serverBtn, gbc);
        gbc.gridy++;
        panel.add(clientBtn, gbc);
        gbc.gridy++;
        panel.add(exitBtn, gbc);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrontPage().setVisible(true);
        });
    }
}
