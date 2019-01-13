package src.Markdown2Html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.options.MutableDataSet;

import javax.swing.*;

public class Converter {
    private String mdCss;

    // Get css file
    public Converter() {
        try {
            File file = new File("mdParser.css");
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);

            String temp = br.readLine();
            StringBuilder mdCssBuilder = new StringBuilder("<style type=\"text/css\">\n");
            while (temp != null) {
                mdCssBuilder.append(temp);
                mdCssBuilder.append("\n");
                temp = br.readLine();
            }
            mdCssBuilder.append("\n</style>\n");
            mdCss = mdCssBuilder.toString();
        } catch (Exception e) {
            mdCss = "";
        }
    }

    private void Show(String content, JEditorPane editorPane) {
        editorPane.setText(content);
    }

    public void Markdown2Html(String content, JEditorPane editorPane) throws IOException {
        content = content.replaceAll("(?!\r)\n", "\r\n");

        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);

        // Enable table parse
        options.set(Parser.EXTENSIONS, Collections.singletonList(TablesExtension.create()));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(content);
        String html = renderer.render(document);

        MarkdownEntity entity = new MarkdownEntity();
        entity.setCss(mdCss);
        entity.setHtml(html);
        entity.addDivStyle();

        File file = new File("Test.html");
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write("<html>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<head>\n\n");
        bw.write(entity.toString());
        bw.flush();
        bw.close();

        // Show the change
        Show(entity.toString(), editorPane);
    }
}
