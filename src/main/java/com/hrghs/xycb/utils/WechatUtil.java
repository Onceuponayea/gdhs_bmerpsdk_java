package com.hrghs.xycb.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hrghs.xycb.domains.BanmaerpSigningVO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信工具类
 *
 * @author hury
 */
@Component
@Data
public class WechatUtil {

    @Autowired
    private RestTemplate restTemplate;

    private String corpid = "wwd3fcf9134cd03abe";

    private String corpsecret = "C9SFT8Hpg-fRedRxnYmh-Gc6g7UHphQ4k9qjxbu0tUw";

    private String agentid = "3010046";

    private String webhook = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=a34d8f1c-a22d-4d2c-aeea-5323d7ae35d2";

    private String ddWebhook = "https://oapi.dingtalk.com/robot/send?access_token=5502e6e4abdcce21a8d3ae71614c7708421b45f32ad33344a713d6638207e7d7";

    private HttpHeaders httpHeaders = new HttpHeaders();



    // 企业微信机器人
    public void qywxSendText(String message, String phone, String email) {
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        message = message.replaceAll("\"", "");
        if (message.contains("{")) {
            String response = message.substring(message.indexOf("{"), message.lastIndexOf("}")+1);
            System.out.println(response);
            JsonParser parser = new JsonParser();

            // 2.获得 根节点元素
            JsonElement element = parser.parse(response);

            // 3.根据 文档判断根节点属于 什么类型的 Gson节点对象
            JsonObject root = element.getAsJsonObject();

            message = root.get("Message").getAsString();
            System.out.println(message);
        }

        String msg = "{\n" +
                "    \"msgtype\": \"markdown\",\n" +
                "    \"markdown\": {\n" +
                "        \"content\": \"实时新增用户反馈:\n<font color=\\\"red\\\">" + message + "</font>\n选品中心账户无法绑定到斑马，请相关同事注意。\\n\n" +
                "         >手机：<font color=\\\"comment\\\">" + phone + "</font>\n" +
                "         >邮箱：<font color=\\\"comment\\\">" + email + "</font>\"\n" +
                "    }\n" +
                "}";

//        HttpUtil.post(webhook, msg);
        restTemplate.exchange(webhook, HttpMethod.POST, new HttpEntity<>(msg, httpHeaders), new ParameterizedTypeReference<Object>() {
        });

    }

    // 钉钉机器人
    public void ddSendText(String massage, String phone, String email) {
        String msg = "{\n" +
                "     \"msgtype\": \"markdown\",\n" +
                "     \"markdown\": {\n" +
                "         \"title\":\"实时新增用户反馈\",\n" +
                "         \"text\": \"#### 实时新增用户反馈"+massage+"，选品中心账户无法绑定到斑马，请相关同事注意。 \\n > #### 手机："+phone+"\\n > #### 邮箱："+email+" \\n\"\n" +
                "      }\n" +
                " }";
//        HttpUtil.post(ddWebhook, msg);
        restTemplate.exchange(ddWebhook, HttpMethod.POST, new HttpEntity<>(msg, httpHeaders), new ParameterizedTypeReference<Object>() {
        });
    }

}
