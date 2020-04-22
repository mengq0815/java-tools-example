package com.example.tool.dingding;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 钉钉链接类型发送类
 */
public class DingLinkMessage implements DingMessage {

    /**
     * 标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String text;

    /**
     * 消息URL
     */
    private String messageUrl;

    /**
     * 图片URL
     */
    private String picUrl;

    /**
     * 被@人的手机号
     */
    private List<String> atMobiles;

    /**
     * @ 所有人时: true, 否则为:false
     */
    private boolean isAtAll;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public boolean isAtAll() {
        return isAtAll;
    }

    public void setAtAll(boolean atAll) {
        isAtAll = atAll;
    }

    @Override
    public String toJsonString() {
        Map<String, Object> items = new HashMap<>(8);
        items.put("msgtype", "link");

        Map<String, String> textContent = new HashMap<>(8);
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("text should not be blank");
        }

        textContent.put("text", text);
        textContent.put("title", title);
        textContent.put("messageUrl", messageUrl);
        textContent.put("picUrl", picUrl == null ? "" : picUrl);
        items.put("link", textContent);

        Map<String, Object> atItems = new HashMap<>(8);
        if (atMobiles != null && !atMobiles.isEmpty()) {
            atItems.put("atMobiles", atMobiles);
        }

        if (isAtAll) {
            atItems.put("isAtAll", isAtAll);
        }

        items.put("at", atItems);
        return JSON.toJSONString(items);
    }
}
