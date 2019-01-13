package src.Markdown2Html;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MarkdownEntity {
    // css style
    private String css;

    // div label
    private Map<String, String> divStyle = new ConcurrentHashMap<>();

    // Converted html string
    private String html;

    void setCss(String css) {
        this.css = css;
    }

    void setHtml(String html) {
        this.html = html;
    }

    MarkdownEntity() { }

    @Override
    public String toString() {
        return css + "\n<div " + parseDiv() + ">\n" + html + "\n</div>";
    }

    private String parseDiv() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : divStyle.entrySet()) {
            builder.append(entry.getKey()).append("=\"")
                    .append(entry.getValue()).append("\" ");
        }
        return builder.toString();
    }

    void addDivStyle() {
        if (divStyle.containsKey("class")) {
            divStyle.put("class", divStyle.get("class") + " " + "markdown-body ");
        } else {
            divStyle.put("class", "markdown-body ");
        }
    }
}