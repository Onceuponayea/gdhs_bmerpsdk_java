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
import org.joda.time.format.DateTimeFormatter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
//import com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup;

public class SimpleTests {
    public static void main(String[] args) {
//        System.out.println(MySQL8Dialect.class.getName());
//        System.out.println(org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl.class.getName());
//"/v1/store?PageNumber=%d&PageSize=%d&SearchTimeStart=%tF&SearchTimeEnd=%tF";
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//        String formatedUrl = String.format(BanmaerpURL.banmaerp_storelist_GET,1,20,"2021-01-01 09:00:00","2023-01-10 09:00:00","","","");
//        System.out.println(formatedUrl);
//
//        System.out.println(new DateTime().toString());
//System.out.println(        BanmaerpPlatformEnums.Platform.Lazada.toString());
//        BanmaerpPlatformEnums.Platform platform = null;
//        System.out.println(platform.toString());
        //DateTime d1 = new DateTime("2021-01-01 09:00:00");
//        DateTime d2 = new DateTime("2021-01-01");
//        DateTime d3 = new DateTime("2021-01-01T09:00:00");
//        System.out.println(dateTimeFormatter.parseDateTime("2021-01-01 09:00:00"));
//        System.out.println(dateTimeFormatter.parseDateTime("2021-01-01"));
//        System.out.println(dateTimeFormatter.parseDateTime("2021-01-01T09:00:00"));

//        BanmaerpProperties banmaerpProperties =null;
//        Class[] classes= new Class[2];
//        classes[0] =BanmaerpProperties.class;
//        BanmaerpProperties banmaerpProperties2 =new BanmaerpProperties();
//        System.out.println(banmaerpProperties instanceof BanmaerpProperties);
//        System.out.println(banmaerpProperties2 instanceof BanmaerpProperties);
//
//        System.out.println(classes[0] == BanmaerpProperties.class );
//
//        Object[] objects = new Object[2];
//        Object o = objects[1];
//        System.out.println(o);
//        System.out.println(o==null);

       long count =
               Arrays.stream(ImageFormats.values())
                       .filter(format -> Arrays.stream(format.getExtensions())
                               .anyMatch(extension -> "keycloak.jpg".toLowerCase(Locale.ROOT).contains(extension.toLowerCase(Locale.ROOT))))
                               .count();
       System.out.println(count);
//        System.out.println(imageFormats.getName());
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
