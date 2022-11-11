package com.hrghs.xycb;

//import static java.security.spec.MGF1ParameterSpec.SHA256;

import org.springframework.http.HttpMethod;

import java.util.Locale;

import static javax.xml.crypto.dsig.DigestMethod.SHA256;

/**
 * https://piotrminkowski.com/2020/08/04/guide-to-building-spring-boot-library/
 * https://medium.com/trendyol-tech/how-to-write-a-spring-boot-library-project-7064e831b63b
 *
 */
public class BanmaerpApplication
{
    public static void main( String[] args )
    {

        System.out.println(HttpMethod.GET.name());
    }
}
