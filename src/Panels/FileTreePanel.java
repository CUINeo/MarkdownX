package src.Panels;

import java.awt.*;
import javax.swing.*;
public class FileTreePanel extends JPanel {
    private JEditorPane dir;

    public FileTreePanel() {
        dir = new JEditorPane();
        dir.setEditable(false);
        dir.setBackground(Color.WHITE);
        dir.setFont(new Font("黑体", Font.PLAIN, 14));
        dir.setPreferredSize(new Dimension(180, 0));
        dir.setBorder(BorderFactory.createEmptyBorder());

        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(1, 1));

        JScrollPane scrollPane = new JScrollPane(dir);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void Update(String mdContent) {
        // Clear document directory
        StringBuilder docDir = new StringBuilder();
        String [] lines = mdContent.split("\n");

//        for (String line : lines) {
//            System.out.println(line);
//        }

        // Scan content for titles
        for (String line : lines) {
            if (line.startsWith("#") && !line.startsWith("##")) {
                String lineCont = line.replaceAll("#", "");
                docDir.append("-");
                docDir.append(lineCont);
                docDir.append("\r\n");
            }
            else if (line.startsWith("##") && !line.startsWith("###")) {
                String lineCont = line.replaceAll("#", "");
                docDir.append(" -");
                docDir.append(lineCont);
                docDir.append("\r\n");
            }
            else if (line.startsWith("###") && !line.startsWith("####")) {
                String lineCont = line.replaceAll("#", "");
                docDir.append("  -");
                docDir.append(lineCont);
                docDir.append("\r\n");
            }
            else if (line.startsWith("####") && !line.startsWith("#####")) {
                String lineCont = line.replaceAll("#", "");
                docDir.append("   -");
                docDir.append(lineCont);
                docDir.append("\r\n");
            }
            else if (line.startsWith("#####")&& !line.startsWith("######")) {
                String lineCont = line.replaceAll("#", "");
                docDir.append("    -");
                docDir.append(lineCont);
                docDir.append("\r\n");
            }
            else if (line.startsWith("######")&& !line.startsWith("#######")) {
                String lineCont = line.replaceAll("#", "");
                docDir.append("     -");
                docDir.append(lineCont);
                docDir.append("\r\n");
            }
        }

        dir.setText(docDir.toString());
//        System.out.println(docDir.toString());
    }
}
