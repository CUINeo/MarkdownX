package src.Panels;

import java.awt.*;
import javax.swing.*;

public class TextPanel extends JPanel {
    public InputPanel inputPanel;
    public PreviewPanel previewPanel;

    public TextPanel() {
        inputPanel = new InputPanel();
        previewPanel = new PreviewPanel();

        setLayout(new GridLayout(1, 2));

        add(inputPanel);
        add(previewPanel);
    }
}
