package src.Panels;

import java.awt.*;
import javax.swing.*;

public class PreviewPanel extends JPanel {
    public JEditorPane editorPane;

    PreviewPanel() {
        editorPane = new JEditorPane();

        editorPane.setEditable(false);
        editorPane.setBackground(Color.WHITE);
        editorPane.setPreferredSize(new Dimension(200, 0));
        editorPane.setBorder(BorderFactory.createEmptyBorder());
        editorPane.setContentType("text/html");

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(1, 1));

        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }
}
