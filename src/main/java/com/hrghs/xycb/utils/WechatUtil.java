package com.hrghs.xycb.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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


    private String corpid = "wwd3fcf9134cd03abe";

    private String corpsecret = "C9SFT8Hpg-fRedRxnYmh-Gc6g7UHphQ4k9qjxbu0tUw";

    private String agentid = "3010046";

    private String webhook = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=a34d8f1c-a22d-4d2c-aeea-5323d7ae35d2";

    private String ddWebhook = "https://oapi.dingtalk.com/robot/send?access_token=5502e6e4abdcce21a8d3ae71614c7708421b45f32ad33344a713d6638207e7d7";

    /**
     * 获取access_token
     */
    public String getAccessToken() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken" + "?corpid=" + corpid + "&corpsecret=" + corpsecret;
        String result = HttpUtil.get(url);
        cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(result);
        String access_token = jsonObject.get("access_token").toString();
        return access_token;
    }

    /**
     * 根据手机号获取企业微信用户UserId
     *
     * @param mobile
     * @return
     */
    public String getUserIdByMobile(String mobile) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=" + getAccessToken();
        HashMap<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        String result = HttpUtil.post(url, JSONUtil.toJsonStr(map));
        String userid = JSONUtil.parseObj(result).get("userid").toString();
        return userid;
    }

    /**
     * 发送文本消息
     *
     * @param content 发送内容
     * @param touser  接收人
     * @return
     */
    public void SendMsgText(String touser, String content) {
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> text = new HashMap<String, Object>();
        data.put("touser", touser);
        data.put("msgtype", "text");
        data.put("agentid", agentid);
        text.put("content", content);
        data.put("text", text);
        String param = JSONUtil.toJsonStr(data);
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + getAccessToken();
        HttpUtil.post(url, param);
    }

    // 企业微信机器人
    public void qywxSendText(String massage, String phone, String email) {
        String msg = "{\n" +
                "    \"msgtype\": \"markdown\",\n" +
                "    \"markdown\": {\n" +
                "        \"content\": \"实时新增用户反馈<font color=\\\"warning\\\">" + massage + "</font>，选品中心账户无法绑定到斑马，请相关同事注意。\\n\n" +
                "         >手机：<font color=\\\"comment\\\">" + phone + "</font>\n" +
                "         >邮箱：<font color=\\\"comment\\\">" + email + "</font>\"\n" +
                "    }\n" +
                "}";
        HttpUtil.post(webhook, msg);
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
        HttpUtil.post(ddWebhook, msg);
    }

}
