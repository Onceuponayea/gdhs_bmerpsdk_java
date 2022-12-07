package com.hrghs.xycb.domains.enums;

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
        // 无权限
        NoPermissions("NoPermissions"),
        // 全部权限
        FullPermissions("FullPermissions"),
        // 指定权限
        SpecifyPermissions("SpecifyPermissions");
        private String dataAccessMode;

        DataAccessMode(String dataAccessMode) {
            this.dataAccessMode = dataAccessMode;
        }
    }

    public enum UserType {
        MasterAccount("MasterAccount"), SubAccount("SubAccount"), Unknown("Unknown");
        private String userType;
        UserType(String _userType){
            this.userType =  _userType;
        }

    }
    public enum UserState {
        Normal("正常"),
        Resigned("离职"),Unknown("未知");
        private String userState;
        UserState(String _userState){
            this.userState = _userState;
        }
    }
}
