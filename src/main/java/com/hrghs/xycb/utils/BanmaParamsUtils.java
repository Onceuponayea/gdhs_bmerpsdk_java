package com.hrghs.xycb.utils;

import static com.hrghs.xycb.domains.Constants.PAGE_SIZE_DEFAULT;

public class BanmaParamsUtils {
    public static int checkPageSize(Integer pageSize){
        return pageSize==null||pageSize<1?PAGE_SIZE_DEFAULT:pageSize;
    }
    public static int checkPageNum(Integer pageNumber,Boolean remote){
        int offset = remote?0:1;
        return pageNumber==null||pageNumber<1?0:pageNumber-offset;
    }
}
