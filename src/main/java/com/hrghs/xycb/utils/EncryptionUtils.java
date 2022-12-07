package com.hrghs.xycb.utils;

import cn.hutool.crypto.SecureUtil;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_APPID;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_APPSECRET;
import static jodd.util.StringPool.*;

public class EncryptionUtils {
    /**
     * @@apiNote banmaErp support multiple signing method:
     * SHA1
     * MD5
     * SHA256
     * SHA384
     * SHA512
     * HMACMD5
     * HMACSHA256
     * HMACSHA512
     * but here only support SHA256. you can implement more sign method by your choice!
     * @@return sign
     * @@throws NoSuchAlgorithmException
     */
    public String banmaerpSigning(BanmaerpSigningVO banmaerpSigningVO) {
        String requestQuery = captureName(banmaerpSigningVO);
        String requestBody = banmaerpSigningVO.getRequest_body();
        requestBody = StringUtils.hasText(requestBody) ? requestBody : "";
        String signText = banmaerpSigningVO.getRequest_method().name()
                .concat(banmaerpSigningVO.getRequest_path())
                .concat(requestQuery)
                .concat(banmaerpSigningVO.getTimestamp().toString())
                .concat(requestBody);
        return SecureUtil.sha256(signText);
    }

    private String getSign(String signText, String sign_algorithm) {
        MessageDigest messageDigest = null;
        String signalgorithm = sign_algorithm;
        switch (sign_algorithm) {
            case "SHA256":
                signalgorithm = "SHA-256";
                break;
            default:
                signalgorithm = "SHA-256";
        }
        try {
            messageDigest = MessageDigest.getInstance(signalgorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(signText.getBytes(StandardCharsets.UTF_8));
        byte[] hash = messageDigest.digest(signText.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hash);
    }

    public String getUUID() {
        return UUID.randomUUID().toString();
    }

    public String getUUIDWithoutDash() {
        return getUUID().replaceAll("-", "");
    }

    public String rmEmptyParas(String apiUrl) {
        if (apiUrl.contains(QUESTION_MARK)) {
            String[] splitedStr = apiUrl.split(BACK_SLASH + QUESTION_MARK);
            String formattedUrl = splitedStr[0].concat(QUESTION_MARK);
            StringBuffer stringBuffer = new StringBuffer(formattedUrl);
            String paramStr = splitedStr[1];
            Map<String, String> stringMap = stringToMap(paramStr, AMPERSAND, EQUALS, null);
            stringMap.forEach((k, v) -> {
                if (StringUtils.hasText(v) && !v.toLowerCase(Locale.ROOT).equalsIgnoreCase("null")) {
                    stringBuffer.append(k.concat(EQUALS).concat(v).concat(AMPERSAND));
                }
            });
            apiUrl = stringBuffer.substring(0, stringBuffer.length() - 1);
        }
        return apiUrl;
    }

    public Map<String, String> stringToMap(String value, String split, String key_valueSperator, List<String> excludeKey) {
        Map<String, String> stringMap = new HashMap<>();
        for (String reqpair : value.split(split)) {
            String[] kv = reqpair.split(key_valueSperator);
            if (excludeKey != null) {
                for (String exKey : excludeKey) {
                    if (kv.length == 2 && !kv[0].equalsIgnoreCase(exKey)) {
                        stringMap.put(kv[0], kv[1]);
                    }
                }
            } else {
                if (kv.length == 2) {
                    stringMap.put(kv[0], kv[1]);
                }
            }
        }
        return stringMap;
    }

    public String mapToString(Map<String, String> stringMap, String split, String key_valueSperator) {
        StringBuffer stringBuffer = new StringBuffer();
        stringMap.forEach((k, v) -> {
            String kv = k.concat(key_valueSperator).concat(v).concat(split);
            stringBuffer.append(kv);
        });
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public static String captureName(BanmaerpSigningVO banmaerpSigningVO) {
        String query = "";
        //第1步 拼接固定的参数
        Map<String, String> map = new TreeMap<String, String>(
                (obj1, obj2) -> {
                    // 降序排序
                    return obj1.compareTo(obj2);
                });
        map.put(BANMAERP_FIELD_APPID, banmaerpSigningVO.getApp_id());
        map.put(BANMAERP_FIELD_APPSECRET, banmaerpSigningVO.getApp_secret());
        if (banmaerpSigningVO.getRequest_query() != null && banmaerpSigningVO.getRequest_query() != "") {
            //第2步 所有将请求的查询参数拆分到字典里。并且保证参数为小写
            String[] request_querys = banmaerpSigningVO.getRequest_query().split("\\&");
            for (int i = 0; i < request_querys.length; i++) {
                String[] querys = request_querys[i].split("=");
                map.put(querys[0].toLowerCase(), querys[1]);
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            query += entry.getKey() + "=" + entry.getValue() + "&";
        }
        return query;
    }
}
