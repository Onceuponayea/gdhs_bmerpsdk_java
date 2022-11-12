package com.hrghs.xycb.domains.enums;

public class BanmaerpStorageEnums {

    public enum FileType{
        // 未知
        Unknow("Unknow"),
        // 图片
        Image("Image"),
        // 文档
        Documentation("Documentation"),
        // 压缩包
        CompressedPackage("CompressedPackage"),
        // 视频
        Video("Video"),
        // 音频
        Audio("Audio"),
        // 其他
        Other("Other");
        private String fileType;

        private FileType(String fileType) {
            this.fileType = fileType;
        }
    }
}
