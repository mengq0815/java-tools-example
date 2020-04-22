package com.example.tool.dingding;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钉钉 Markdown 消息发送
 */
@Getter
@Setter
public class DingMarkdownMessage implements DingMessage {

    public static final String WRAP = "\n\n";

    /**
     * 首屏会话透出的展示内容
     */
    private String title;
    /**
     * 被@人的手机号(在text内容里要有@手机号)
     */
    private List<String> atMobiles;
    /**
     * @ 所有人时: true, 否则为:false
     */
    private boolean isAtAll;
    /**
     * markdown 内容
     */
    private String markdownText;

    /**
     * 加粗
     */
    public static String getBoldText(String text) {
        return "**" + text + "**";
    }

    /**
     * 4级 标题
     */
    public static String getFourHeaderText(String text) {
        return getHeaderText(4, text);
    }

    /**
     * 倾斜
     */
    public static String getItalicText(String text) {
        return "*" + text + "*";
    }

    /**
     * 链接文本
     */
    public static String getLinkText(String text, String href) {
        return "[" + text + "](" + href + ")";
    }

    /**
     * 图片文本
     */
    public static String getImageText(String imageUrl) {
        return "![image](" + imageUrl + ")";
    }

    /**
     * 1-6级 标题
     */
    public static String getHeaderText(int headerType, String text) {
        if (headerType < 1 || headerType > 6) {
            throw new IllegalArgumentException("headerType should be in [1, 6]");
        }
        StringBuffer numbers = new StringBuffer();
        for (int i = 0; i < headerType; i++) {
            numbers.append("#");
        }
        return numbers + " " + text + "\n";
    }

    /**
     * 换行
     */
    public static String wrap(String text) {
        return text + WRAP;
    }


    /**
     * 引用
     */
    public static String getReferenceText(String text) {
        return "> " + text;
    }

    /**
     * 有序列表
     */
    public static String getOrderListText(List<String> orderItem) {
        if (orderItem.isEmpty()) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= orderItem.size() - 1; i++) {
            sb.append(String.valueOf(i) + ". " + orderItem.get(i - 1) + "\n");
        }
        sb.append(String.valueOf(orderItem.size()) + ". " + orderItem.get(orderItem.size() - 1));
        return sb.toString();
    }

    /**
     * 无序列表
     */
    public static String getUnorderListText(List<String> unorderItem) {
        if (unorderItem.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < unorderItem.size() - 1; i++) {
            sb.append("- " + unorderItem.get(i) + "\n");
        }
        sb.append("- " + unorderItem.get(unorderItem.size() - 1));
        return sb.toString();
    }

    @Override
    public String toJsonString() {
        Map<String, Object> request = new HashMap<>(8);
        request.put("msgtype", "markdown");

        Map<String, Object> markdown = new HashMap<>(8);
        markdown.put("title", title);

        markdown.put("text", markdownText);
        request.put("markdown", markdown);

        Map<String, Object> atItems = new HashMap<>(8);
        if (atMobiles != null && !atMobiles.isEmpty()) {
            atItems.put("atMobiles", atMobiles);
        }
        if (isAtAll) {
            atItems.put("isAtAll", isAtAll);
        }
        request.put("at", atItems);

        return JSON.toJSONString(request);
    }
}
