package com.hrghs.xycb.utils;

public class BanmaParamsUtils {
    public static int checkPageSize(Integer pageSize){
        return pageSize==null||pageSize<1?100:pageSize;
    }
    public static int checkPageNum(Integer pageNumber){
        return pageNumber==null||pageNumber<1?0:pageNumber-1;
    }
}
