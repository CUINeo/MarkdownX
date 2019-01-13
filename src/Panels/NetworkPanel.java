package src.Panels;

import java.awt.*;
import javax.swing.*;

public class NetworkPanel extends JPanel {
    public JButton connButton;
    public JButton disConnButton;
    public JTextField ipField;
    public JTextField portField;

    public NetworkPanel() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder());

        connButton = getButton("Connect");
        disConnButton = getButton("Disconnect");
        ipField = getJTextField();
        portField = getJTextField();

        JTextField ipArea = getFinalTextField("IP address:");
        JTextField portArea = getFinalTextField("Port:");
        JTextField emptyField = getFinalTextField("  ");

        // Set layout and add components
        setLayout(new FlowLayout(FlowLayout.LEADING));
        add(ipArea);
        add(ipField);
        add(portArea);
        add(portField);
        add(emptyField);
        add(connButton);
        add(disConnButton);
    }

    private JButton getButton(String content) {
        JButton button = new JButton(content);
        button.setBackground(new Color(0,191,255));
        button.setFont(new Font("Arial", Font.PLAIN, 15));

        return button;
    }

    private JTextField getFinalTextField(String content) {
        JTextField textField = new JTextField(content);
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setEditable(false);

        return textField;
    }

    private JTextField getJTextField() {
        JTextField textField = new JTextField();
        textField.setColumns(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setBackground(Color.WHITE);

        return textField;
    }
}
