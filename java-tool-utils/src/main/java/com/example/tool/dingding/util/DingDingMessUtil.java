package com.example.tool.dingding.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.tool.dingding.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.List;

/**
 * 钉钉机器人消息发送工具类
 *
 * @author mengqiang
 * @version DingDingMessUtil.java, v 1.0
 */
public class DingDingMessUtil {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DingDingMessUtil.class);

    /**
     * 发送钉钉普通文本消息 +占位符自动替换
     * 示例： DingDingMessUtil.sendText("test demo {0},{1}", "000", "001");
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param text         钉钉消息内容
     * @param args         占位符需要替换的参数
     */
    public static void sendText(String robotWebHook, String text, Object... args) {
        sendText(robotWebHook, null, false, text, args);
    }

    /**
     * 发送钉钉普通文本消息 =>  指定@人
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param text         钉钉消息内容
     * @param mobiles      被 @ 用户手机号
     * @param args         占位符需要替换的参数
     */
    public static void sendTextAtMobiles(String robotWebHook, List<String> mobiles, String text, Object... args) {
        sendText(robotWebHook, mobiles, false, text, args);
    }

    /**
     * 发送钉钉普通文本消息 =>  @ 所有人
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param text         钉钉消息内容
     * @param args         占位符需要替换的参数
     */
    public static void sendTextAtAll(String robotWebHook, String text, Object... args) {
        sendText(robotWebHook, null, true, text, args);
    }

    /**
     * 发送 > 多行消息
     * 注：实际以MarketDOwn形式发生-便于美化格式
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param lineText     每一行的内容集，每一个节点是一行
     */
    public static void sendLineText(String robotWebHook, String title, List<String> lineText) {
        sendLineText(robotWebHook, title, lineText, null, false, false);
    }

    /**
     * 发送 > 多行消息-并指定@的用户手机号集
     * 注：实际以MarketDOwn形式发生-便于美化格式
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param lineText     每一行的内容集，每一个节点是一行
     * @param mobiles      被 @ 用户手机号
     */
    public static void sendLineTextAtMobiles(String robotWebHook, String title, List<String> lineText, List<String> mobiles) {
        sendLineText(robotWebHook, title, lineText, mobiles, false, false);
    }

    /**
     * 发送 > 多行消息-并@群内所有人
     * 注：实际以MarketDOwn形式发生-便于美化格式
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param title        消息标题
     * @param lineText     消息每一行的内容集，每一个节点是一行
     */
    public static void sendLineTextAtAll(String robotWebHook, String title, List<String> lineText) {
        sendLineText(robotWebHook, title, lineText, null, false, true);
    }

    /**
     * 发送 > 多行消息（消息呈现无序列表样式）
     * 注：实际以MarketDOwn形式发生-便于美化格式
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param title        消息标题
     * @param lineText     消息每一行的内容集，每一个节点是一行
     */
    public static void sendLineOrderText(String robotWebHook, String title, List<String> lineText) {
        sendLineText(robotWebHook, title, lineText, null, true, false);
    }

    /**
     * 发送 > 多行消息（消息呈现无序列表样式）-并@所有人
     * 注：实际以MarketDOwn形式发生-便于美化格式
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param title        消息标题
     * @param lineText     消息每一行的内容集，每一个节点是一行
     */
    public static void sendLineOrderTextAtAll(String robotWebHook, String title, List<String> lineText) {
        sendLineText(robotWebHook, title, lineText, null, true, true);
    }

    /**
     * 公共发送-钉钉普通消息方法
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param text         钉钉消息内容
     * @param atAll        是否@所有人
     * @param phones       被 @ 人员的时候号
     * @param args         占位符需要替换的参数
     */
    private static void sendText(String robotWebHook, List<String> phones, Boolean atAll, String text, Object... args) {
        String msgText = "";
        try {
            //占位符替换消息内容
            msgText = MessageFormat.format(text, args);
            DingTextMessage message = new DingTextMessage(msgText);
            message.setIsAtAll(atAll);
            if (null != phones && phones.size() > 0) {
                message.setAtMobiles(phones);
            }
            DingSendResult result = httpSend(robotWebHook, message);
            if (!result.isSuccess()) {
                LOGGER.error("钉钉普通消息-推送失败！消息内容={},错误码={},错误内容={}", msgText, result.getErrorCode(), result.getErrorMsg());
            }
        } catch (Exception e) {
            LOGGER.error("钉钉普通消息-推送异常！消息内容={},异常信息={},tack={}", msgText, e.getMessage(), e);
        }
    }


    /**
     * 发送 > 普通多行消息
     *
     * @param robotWebHook 钉钉机器人请求地址
     * @param lineText     每一行的内容集，每一个节点是一行
     * @param needOrder    是否无序列表格式展示
     * @param isAtAll      是否@所有人
     */
    public static void sendLineText(String robotWebHook, String title, List<String> lineText, List<String> atMobiles, boolean needOrder, boolean isAtAll) {
        if (CollectionUtils.isEmpty(lineText)) {
            return;
        }

        DingMarkdownMessage markdownMessage = new DingMarkdownMessage();

        StringBuilder dingMess = new StringBuilder();
        if (!StringUtils.isEmpty(title)) {
            markdownMessage.setTitle(title);
            dingMess.append(DingMarkdownMessage.getThreeHeaderText(title));
        }
        if (needOrder) {
            dingMess.append(DingMarkdownMessage.getUnorderListText(lineText));
        } else {
            for (String text : lineText) {
                dingMess.append(DingMarkdownMessage.getFourHeaderText(text)).append(DingMessageConstants.NEW_LINE);
            }
        }
        //指定@人
        if (!CollectionUtils.isEmpty(atMobiles)) {
            //换行
            atMobiles.forEach(mobile -> dingMess.append("@").append(mobile));
        }
        markdownMessage.setAtAll(isAtAll);
        markdownMessage.setAtMobiles(atMobiles);
        markdownMessage.setMarkdownText(dingMess.toString());
        DingDingMessUtil.sendMarkdown(robotWebHook, markdownMessage);
    }


    /**
     * 公共发送-钉钉Link消息方法
     *
     * @param robotWebHook 钉钉机器人请求地址
     */
    public static void sendLink(String robotWebHook, DingLinkMessage linkMessage) {
        try {

            DingSendResult result = httpSend(robotWebHook, linkMessage);
            if (!result.isSuccess()) {
                LOGGER.error("钉钉Link类型消息-推送失败！消息内容={},错误码={},错误内容={}", linkMessage.toJsonString(), result.getErrorCode(), result.getErrorMsg());
            }
        } catch (Exception e) {
            LOGGER.error("钉钉Link类型消息-推送异常！消息内容={},异常信息={},tack={}", linkMessage.toJsonString(), e.getMessage(), e);
        }
    }


    /**
     * 公共发送-钉钉Markdown消息方法
     *
     * @param robotWebHook 钉钉机器人请求地址
     */
    public static void sendMarkdown(String robotWebHook, DingMarkdownMessage markdownMessage) {
        try {

            DingSendResult result = httpSend(robotWebHook, markdownMessage);
            if (!result.isSuccess()) {
                LOGGER.error("钉钉Markdown类型消息-推送失败！错误码={},错误内容={}", result.getErrorCode(), result.getErrorMsg());
            }
        } catch (Exception e) {
            LOGGER.error("钉钉Markdown类型消息-推送异常！异常信息={},tack={}", e.getMessage(), e);
        }
    }

    /**
     * http方式调用钉钉开放接口
     * 发送钉钉报警提醒
     *
     * @param robotWebHook 访问地址
     * @param message      消息
     */
    private static DingSendResult httpSend(String robotWebHook, DingMessage message) throws Exception {
        DingSendResult sendResult = new DingSendResult();
        String result = HttpUtil.post(robotWebHook, message.toJsonString());
        if (null != result) {
            JSONObject obj = JSONObject.parseObject(result);
            Integer errCode = obj.getInteger("errcode");
            sendResult.setErrorCode(errCode);
            sendResult.setErrorMsg(obj.getString("errmsg"));
            sendResult.setIsSuccess(errCode.equals(0));
        }
        return sendResult;
    }

}