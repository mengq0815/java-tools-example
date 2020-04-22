package com.example.tool.dingding;

/**
 * 钉钉消息
 */
public interface DingMessage {

    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    String toJsonString();
}
