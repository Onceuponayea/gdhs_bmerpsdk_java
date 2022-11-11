package com.hrghs.xycb.utils;

import com.hrghs.xycb.domains.BanmaerpSigningVO;
import org.apache.commons.codec.binary.Hex;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

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
    public String banmaerpSigning(BanmaerpSigningVO banmaerpSigningVO) throws NoSuchAlgorithmException {
        /** the signing order is unalterable! **/
        String signText = banmaerpSigningVO.getRequest_method().name()
                .concat(banmaerpSigningVO.getRequest_path())
                .concat(banmaerpSigningVO.getRequest_query().toLowerCase(Locale.ROOT))
                .concat(banmaerpSigningVO.getTimestamp().toString())
                .concat(banmaerpSigningVO.getRequest_body());
        return getSHA256(signText,banmaerpSigningVO.getSign_method());
    }
    private String getSHA256(String signText,String sign_method) throws NoSuchAlgorithmException {
        MessageDigest messageDigest=MessageDigest.getInstance(sign_method);
        messageDigest.update(signText.getBytes(StandardCharsets.UTF_8));
        byte[] hash = messageDigest.digest(signText.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hash);
    }

}
