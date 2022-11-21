package com.hrghs.xycb.utils;

import com.hrghs.xycb.domains.BanmaerpSigningVO;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.UUID;

@Component
public class EncryptionUtils {
    /**
     * @@apiNote  banmaErp support multiple signing method:
     *  SHA1
     *  MD5
     *  SHA256
     *  SHA384
     *  SHA512
     *  HMACMD5
     *  HMACSHA256
     *  HMACSHA512
     *  but here only support SHA256. you can implement more sign method by your choice!
     * @@return sign
     * @@throws NoSuchAlgorithmException
     */
    public String banmaerpSigning(BanmaerpSigningVO banmaerpSigningVO) {
        /** the signing order is unalterable! **/
        String requestQuery=banmaerpSigningVO.getRequest_query();
        requestQuery= StringUtils.hasText(requestQuery)?requestQuery.toLowerCase(Locale.ROOT):"";
        String requestBody=banmaerpSigningVO.getRequest_body();
        requestBody=StringUtils.hasText(requestBody)?requestBody:"";
        String signText = banmaerpSigningVO.getRequest_method().name()
                .concat(banmaerpSigningVO.getRequest_path())
                .concat(requestQuery)
                .concat(banmaerpSigningVO.getTimestamp().toString())
                .concat(requestBody)
                .concat("/");
        return getSign(signText,banmaerpSigningVO.getSign_algorithm());
    }
    private String getSign(String signText,String sign_algorithm) {
        MessageDigest messageDigest= null;
        String signalgorithm=sign_algorithm;
        switch (sign_algorithm) {
            case "SHA256": signalgorithm = "SHA-256";
            break;
            default: signalgorithm = "SHA-256";
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
    public String getUUID(){
        return UUID.randomUUID().toString();
    }
    public String getUUIDWithoutDash(){
        return getUUID().replaceAll("-","");
    }

}
