package com.hrghs.xycb.utils;

import static com.hrghs.xycb.domains.Constants.PAGE_SIZE_DEFAULT;

public class BanmaParamsUtils {
    public static int checkPageSize(Integer pageSize){
        return pageSize==null||pageSize<1?PAGE_SIZE_DEFAULT:pageSize;
    }
    public static int checkPageNum(Integer pageNumber){
        return pageNumber==null||pageNumber<1?0:pageNumber-1;
    }
}
