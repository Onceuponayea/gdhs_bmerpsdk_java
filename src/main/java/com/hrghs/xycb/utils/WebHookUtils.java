package com.hrghs.xycb.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;

import static com.hrghs.xycb.domains.Constants.*;

/**
 * 企业微信工具类
 *
 * @author hury
 */
public class WebHookUtils {

    @Autowired
    @Qualifier(value = "bmerp_restTemplate")
    private RestTemplate restTemplate;
    @Value("${msg.webhook.qywechat.enable:false}")
    private Boolean qywechat_enabled;
    @Value("${msg.webhook.dingtalk.enable:false}")
    private Boolean dingtalk_enabled;
    @Value("${msg.webhook.qywechat.key}")
    private String qywechat_key;
    @Value("${msg.webhook.dingtalk.access_token}")
    private String access_token;
    @Value("${msg.webhook.qywechat.url}")
    private String qywechat_url = "";
    @Value("${msg.webhook.dingtalk.url}")
    private String dingtalk_url = "";

    private HttpHeaders httpHeaders = new HttpHeaders();

    @PostConstruct
    private void init(){
        qywechat_url = StringUtils.hasText(qywechat_key)?String.format(WEBHOOK_BASEURL_WECHAT_ENTERPISE,qywechat_key):qywechat_url;
        dingtalk_url = StringUtils.hasText(access_token)?String.format(WEBHOOK_BASEURL_DINGTALK_ENTERPISE,access_token):dingtalk_url;
    }
    public void sendNotice(String massage, String phone, String email){
        if (qywechat_enabled){
            qywechatSendText(massage, phone, email);
        }
        if (dingtalk_enabled){
            dingtalkSendText(massage, phone, email);
        }
    }
    // 企业微信机器人
    public void qywechatSendText(String massage, String phone, String email) {
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String msg = "{\n" +
                "    \"msgtype\": \"markdown\",\n" +
                "    \"markdown\": {\n" +
                "        \"content\": \"实时新增用户反馈<font color=\\\"warning\\\">" + massage + "</font>，选品中心账户无法绑定到斑马，请相关同事注意。\\n\n" +
                "         >手机：<font color=\\\"comment\\\">" + phone + "</font>\n" +
                "         >邮箱：<font color=\\\"comment\\\">" + email + "</font>\"\n" +
                "    }\n" +
                "}";
        restTemplate.exchange(qywechat_url, HttpMethod.POST, new HttpEntity<>(msg, httpHeaders), new ParameterizedTypeReference<Object>() {
        });

    }

    // 钉钉机器人
    public void dingtalkSendText(String massage, String phone, String email) {
        String msg = "{\n" +
                "     \"msgtype\": \"markdown\",\n" +
                "     \"markdown\": {\n" +
                "         \"title\":\"实时新增用户反馈\",\n" +
                "         \"text\": \"#### 实时新增用户反馈"+massage+"，选品中心账户无法绑定到斑马，请相关同事注意。 \\n > #### 手机："+phone+"\\n > #### 邮箱："+email+" \\n\"\n" +
                "      }\n" +
                " }";
        restTemplate.exchange(dingtalk_url, HttpMethod.POST, new HttpEntity<>(msg, httpHeaders), new ParameterizedTypeReference<Object>() {
        });
    }

}
