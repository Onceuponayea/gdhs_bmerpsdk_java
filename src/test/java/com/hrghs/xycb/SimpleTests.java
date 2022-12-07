package com.hrghs.xycb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.GetSsoPassportResponse;
import com.hrghs.xycb.domains.SsoRegisterResponse;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.AppsInfoDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpAuthEnums;
import org.apache.commons.codec.binary.Hex;
import org.joda.time.LocalDateTime;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashSet;
import java.util.Set;
//import com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup;

public class SimpleTests {
    public static void main(String[] args) throws UnknownHostException, SocketException, MalformedURLException, JsonProcessingException {
        Set<String> taskStates = new LinkedHashSet<>();
        taskStates.add("product_0_0_1");
        taskStates.add("order_0_0_1");
        String[] taskStatesArray = taskStates.toArray(taskStates.toArray(new String[0]));
        System.out.println(taskStatesArray[0]+"\t"+taskStatesArray[1]+"\t\t\t"+taskStatesArray.length);

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
