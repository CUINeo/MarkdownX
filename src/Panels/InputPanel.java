package src.Panels;

import java.awt.*;
import javax.swing.*;

public class InputPanel extends JPanel {
    public JTextArea input;

    InputPanel() {
        input = new JTextArea();
        input.setFont(new Font("黑体", Font.PLAIN, 14));
        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        input.setBackground(Color.WHITE);

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(1, 1));

        JScrollPane scrollPane = new JScrollPane(input);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
    }
}
