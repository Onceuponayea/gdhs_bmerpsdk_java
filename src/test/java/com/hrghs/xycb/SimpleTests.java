package com.hrghs.xycb;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.enums.BanmaerpAuthEnums;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.imaging.ImageFormats;
import org.hibernate.dialect.MySQL8Dialect;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static jodd.util.StringPool.COMMA;
//import com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup;

public class SimpleTests {
    public static void main(String[] args) {
        List<String>  strings = Arrays.asList("1","a","afa","fsfs");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strings){
            stringBuilder.append(s).append(COMMA);
        }
        String dbData = stringBuilder.substring(0,stringBuilder.length()-1);
        System.out.println(dbData);
        List<String> result =
        Arrays.stream(dbData.split(COMMA))
                .collect(Collectors.toList());
        System.out.println(result.get(0)+"\t"+result.size());
    }
    private void banmaSign(){
        boolean isIP = ("IP白名单" == BanmaerpAuthEnums.AuthMethod.IP_WHITELIST.getAuthType());
        System.out.println(isIP);
        System.out.println( BanmaerpAuthEnums.AuthMethod.IP_WHITELIST.getAuthType());
        System.out.println();

        String timeStamp=(System.currentTimeMillis()/1000L)+"";
        String signText=" GET/v1/Auth/GetTokenapp_id=1492055167686688768&app_secret=a7437ad68d0f457b9788e17f92e13b0f&"
                +timeStamp;
        System.out.println("timeStamp: "+ timeStamp);
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(signText.getBytes(StandardCharsets.UTF_8));
        byte[] hash = messageDigest.digest(signText.getBytes(StandardCharsets.UTF_8));
        String result = Hex.encodeHexString(hash);
        System.out.println(result);
    }



}
