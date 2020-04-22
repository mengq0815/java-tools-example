package com.example.tool.ding;

import cn.hutool.http.HttpUtil;

/**
 * @author mengqiang
 * @version .java, v 0.1   -   -
 */
public class DingDingMessTest2 {

    private static final String robotWebHook = "https://oapi.dingtalk.com/robot/send?access_token=xxxx";

    public static void main(String[] args) {

        String  message="{\n" +
                "     \"msgtype\": \"markdown\",\n" +
                "     \"markdown\": {\n" +
                "         \"title\":\"任务杭州天气\",\n" +
                "         \"text\": \"#### 任务杭州天气 @156xxxx8827\\n\" +\n" +
                "                 \"> 9度，西北风1级，空气良89，相对温度73%\\n\\n\" +\n" +
                "                 \"> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\\n\"  +\n" +
                "                 \"> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \\n\"\n" +
                "     },\n" +
                "    \"at\": {\n" +
                "        \"atMobiles\": [\n" +
                "            \"156xxxx8827\", \n" +
                "            \"189xxxx8325\"\n" +
                "        ], \n" +
                "        \"isAtAll\": false\n" +
                "    }\n" +
                " }";

        System.out.println(message);
        String result = HttpUtil.post(robotWebHook, message);
        System.out.println(result);
    }
}