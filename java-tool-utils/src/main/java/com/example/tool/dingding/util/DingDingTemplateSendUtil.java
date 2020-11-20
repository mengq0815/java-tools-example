package com.example.tool.dingding.util;

import com.example.tool.dingding.DingMessageConstants;
import com.example.tool.string.StringPool;

/**
 * 钉钉模板消息发送
 *
 * @author mengqiang
 * @version SupplierTask.java, v 1.0 2018-12-21 16:42
 */
public class DingDingTemplateSendUtil {

    /**
     * 发送 > 普通同步任务消息
     *
     * @author mengqiang
     */
    public static void sendCommonMess(String robotWebHook, String requestId, String taskName, long time) {
        StringBuilder dingMess = new StringBuilder();
        dingMess
                .append("任务名称 : ").append(taskName)
                .append(StringPool.NEWLINE)
                .append("执行状态 : ").append(DingMessageConstants.SMILEY_FACE)
                .append("成功")
                .append(StringPool.NEWLINE)
                .append("日志标识 : ").append(requestId)
                .append(StringPool.NEWLINE)
                .append("任务耗时 : ").append(time).append(" ms");

        DingDingMessUtil.sendText(robotWebHook, dingMess.toString());
    }

    /**
     * 发送 > 异常 消息提醒
     *
     * @author mengqiang
     */
    public static void sendExceptionMess(String robotWebHook, String requestId, String taskName, String exceptionMsg) {
        StringBuilder dingMess = new StringBuilder();
        dingMess
                .append("异常警告 : ").append(DingMessageConstants.ON_FIRE).append(DingMessageConstants.ON_FIRE).append(DingMessageConstants.ON_FIRE)
                .append(StringPool.NEWLINE)
                .append("任务名称 : ").append(taskName)
                .append(StringPool.NEWLINE)
                .append("执行状态 : ").append(DingMessageConstants.CRY_FACE).append("异常")
                .append(StringPool.NEWLINE)
                .append("日志标识 : ").append(requestId)
                .append(StringPool.NEWLINE)
                .append("异常简介 : ").append(exceptionMsg)
                .append(StringPool.NEWLINE);

        DingDingMessUtil.sendTextAtAll(robotWebHook, dingMess.toString());
    }


}