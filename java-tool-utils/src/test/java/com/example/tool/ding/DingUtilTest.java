package com.example.tool.ding;

import com.example.tool.dingding.DingMessageConstants;
import com.example.tool.dingding.util.DingDingMessUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengqiang
 * @version .java, v 0.1   -   -
 */
public class DingUtilTest {

    private static final String robotWebHook = "https://oapi.dingtalk.com/robot/send?access_token=xxx";

    public static void main(String[] args) {

        String title = String.format("异常告警 %s", DingMessageConstants.ON_FIRE);
        List<String> lineText = new ArrayList<>();
        lineText.add("我是第1行消息:111");
        lineText.add("我是第2行消息:222");
        lineText.add("我是第3行消息:333");
        lineText.add("我是第4行消息");
        lineText.add("我是第5行消息");
//        DingDingMessUtil.sendLineText(robotWebHook, title, lineText, false, false);
//        DingDingMessUtil.sendLineText(robotWebHook, title, lineText);

        List<String> mobiles = new ArrayList<>();
        mobiles.add("13235717261");
        DingDingMessUtil.sendLineOrderTextAtAll(robotWebHook, title, lineText);
    }
}