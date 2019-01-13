package src;

import src.Panels.*;
import src.Sockets.*;
import src.Markdown2Html.*;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private TextPanel textPanel;
    private Converter converter;
    private NetworkPanel networkPanel;
    private FileTreePanel fileTreePanel;

    private Client client = null;

    MainWindow() {
        super("MarkdownX");

        // Set window icon
        Image icon = Toolkit.getDefaultToolkit().getImage("Icon.png");
        this.setIconImage(icon);
    }

    void Display() {
        converter = new Converter();
        textPanel = new TextPanel();
        networkPanel = new NetworkPanel();
        fileTreePanel = new FileTreePanel();

        InitNet();
        InitMenuBar();

        // Add listner for text panel
        textPanel.inputPanel.input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Update();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                Update();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                Update();
            }
        });

        // Set background
        textPanel.setBackground(Color.WHITE);
        networkPanel.setBackground(Color.WHITE);
        fileTreePanel.setBackground(Color.WHITE);

        // Add panels
        setJMenuBar(menuBar);
        add(networkPanel, BorderLayout.NORTH);
        add(fileTreePanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);

        // Initialize the frame
        setSize(1000, 600);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Initialize server
        InitServer();
    }

    // Add listener for network buttons
    private void InitNet() {
        networkPanel.connButton.addActionListener(e -> client = new Client());

        networkPanel.disConnButton.addActionListener(e -> {
            if (client != null) {
                if (!client.clientSocket.isClosed()) {
                    try {
                        client.clientSocket.close();
                    } catch (IOException ignored) { }
                }
                client = null;
            }
        });
    }

    // Initialize server
    private void InitServer() {
        Server server = new Server(textPanel.inputPanel.input);
        server.listen();
    }

    // Initialize the menubar
    private void InitMenuBar() {
        menuBar = new JMenuBar();

        // Create menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exportItem = new JMenuItem("Export as html");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exportItem);

        menuBar.add(fileMenu);

        // Add listener to menu items
        openItem.addActionListener(e -> Open());
        saveItem.addActionListener(e -> Save());
        exportItem.addActionListener(e -> Export());
    }

    // Update both preview and directory panel
    private void Update() {
        String mdContent = textPanel.inputPanel.input.getText();

        try {
            if (client != null) {
                String ip = networkPanel.ipField.getText();
                String port = networkPanel.portField.getText();
                client.SendFile(ip, port, mdContent);
            }

            fileTreePanel.Update(mdContent);
            converter.Markdown2Html(mdContent, textPanel.previewPanel.editorPane);
        } catch (IOException e) {
            System.out.println("Unconnected.");
        }
    }

    // Open from file
    private void Open() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String temp;
        StringBuilder fileContent = new StringBuilder();
        JFileChooser openChooser = new JFileChooser();
        int returnVal = openChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = openChooser.getSelectedFile().getPath();

            try {
                FileReader reader = new FileReader(new File(path));
                BufferedReader bReader = new BufferedReader(reader);
                while ((temp = bReader.readLine()) != null) {
                    fileContent.append(temp);
                    fileContent.append("\n");
                }

                bReader.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Open failed!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "Open succeeded!",
                    "Message", JOptionPane.INFORMATION_MESSAGE);

            textPanel.inputPanel.input.setText(fileContent.toString());
        }
    }

    // Save to file
    private void Save() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFileChooser saveChooser = new JFileChooser();
        int returnVal = saveChooser.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = saveChooser.getSelectedFile().getPath();

            try {
                FileWriter writer = new FileWriter(new File(path));
                BufferedWriter bWriter = new BufferedWriter(writer);
                bWriter.write(textPanel.inputPanel.input.getText());

                bWriter.flush();
                bWriter.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Save failed!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "Save succeeded!",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Export to html file
    private void Export() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFileChooser saveChooser = new JFileChooser();
        int returnVal = saveChooser.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = saveChooser.getSelectedFile().getPath();

            try {
                String temp;
                FileReader reader = new FileReader(new File("Test.html"));
                BufferedReader bReader = new BufferedReader(reader);

                FileWriter writer = new FileWriter(new File(path));
                BufferedWriter bWriter = new BufferedWriter(writer);

                while ((temp = bReader.readLine()) != null) {
                    bWriter.write(temp);
                    bWriter.write("\r\n");
                }

                bReader.close();
                reader.close();

                bWriter.flush();
                bWriter.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Export failed!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "Export succeeded!",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
