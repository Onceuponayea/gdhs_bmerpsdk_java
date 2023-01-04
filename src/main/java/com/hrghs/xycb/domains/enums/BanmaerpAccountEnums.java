package com.hrghs.xycb.domains.enums;

import lombok.Data;

public class BanmaerpAccountEnums {

    public enum SortField {
        // 用户ID
        ID("ID"),
        // 创建时间
        CreateTime("CreateTime"),
        // 更新时间
        UpdateTime("UpdateTime");

        private String sortField;

        SortField(String sortField) {
            this.sortField = sortField;
        }
    }

    public enum DataAccessMode {
        // 无权限-0
        NoPermissions("无权限",0),
        // 全部权限-1
        FullPermissions("全部权限",1),
        // 指定权限-2
        SpecifyPermissions("指定权限",2);
        private String dataAccessMode;
        private Integer dataAccessMode_v;


        public static DataAccessMode valueof(String  _dataAccessMode){
            switch (_dataAccessMode){
                case "无权限": return NoPermissions;
                case "全部权限": return FullPermissions;
                case "指定权限": return SpecifyPermissions;
                case "NoPermissions": return NoPermissions;
                case "FullPermissions": return FullPermissions;
                case "SpecifyPermissions": return SpecifyPermissions;
                case "0": return NoPermissions;
                case "1": return FullPermissions;
                case "2": return SpecifyPermissions;
            }
            return null;
        }

        public String getDataAccessMode() {
            return dataAccessMode;
        }

        public void setDataAccessMode(String dataAccessMode) {
            this.dataAccessMode = dataAccessMode;
        }

        public Integer getDataAccessMode_v() {
            return dataAccessMode_v;
        }

        public void setDataAccessMode_v(Integer dataAccessMode_v) {
            this.dataAccessMode_v = dataAccessMode_v;
        }

        DataAccessMode(String _dataAccessMode, Integer _dataAccessMode_v) {
            this.dataAccessMode = _dataAccessMode;
            this.dataAccessMode_v = _dataAccessMode_v;
        }

    }

    public enum UserType {
        MasterAccount("MasterAccount"), SubAccount("SubAccount"), Unknown("Unknown");
        private String value;
        public String getValue(){
            return value;
        }
        public static UserType valueof(String _value){
            switch (_value){
                case "MasterAccount": return MasterAccount;
                case "SubAccount": return SubAccount;
            }
            return Unknown;
        }
        UserType(String _value){
            this.value =  _value;
        }

    }
    public enum UserState {
        Normal("正常"),
        Resigned("离职"),Unknown("未知");
        private String value;
        public String getValue(){
            return value;
        }
        public static UserState valueof(String _value){
            switch (_value) {
                case "正常": return Normal;
                case "离职": return Resigned;
            }
            return Unknown;
        }
        UserState(String _value){
            this.value = _value;
        }
    }
}
