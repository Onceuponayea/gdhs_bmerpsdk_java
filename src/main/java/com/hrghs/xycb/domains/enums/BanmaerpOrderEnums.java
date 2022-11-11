package com.hrghs.xycb.domains.enums;

public class BanmaerpOrderEnums {
    public enum OrderStatus {
        Ordered("Ordered"),Audit("Audit"),PrepareDeliver("PrepareDeliver"),PartialDeliver("PartialDeliver"),
        Delivered("Delivered"),Cancel("Cancel");
        private String orderStatus;
        private OrderStatus(String _orderStatus){
            this.orderStatus = _orderStatus;
        }
    }
}
