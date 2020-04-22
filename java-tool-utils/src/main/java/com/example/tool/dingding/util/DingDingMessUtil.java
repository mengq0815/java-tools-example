package com.example.tool.dingding.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.tool.dingding.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * @param phones       被 @ 人员的时候号
     * @param args         占位符需要替换的参数
     */
    public static void sendTextAtPhones(String robotWebHook, List<String> phones, String text, Object... args) {
        sendText(robotWebHook, phones, false, text, args);
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