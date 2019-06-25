package com.itheima.ssm.domain;

import com.itheima.ssm.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class Orders {
    /*  id varchar2(32) default SYS_GUID() PRIMARY KEY,
      orderNum VARCHAR2(20) NOT NULL UNIQUE,
      orderTime timestamp,
      peopleCount INT,
      orderDesc VARCHAR2(500),
      payType INT,
      orderStatus INT,
      productId varchar2(32),
      memberId varchar2(32),*/
    private String id;//主键id
    private String orderNum;//订单编号
    private Date orderTime;//下单时间
    private String orderTimeStr;//下单时间字符串格式
    private int orderStatus;//订单状态 0 未支付 1 已支付
    private String orderStatusStr;//订单状态 字符串格式
    private int peopleCount;//出行人数
    private Product product;//产品信息
    private List<Traveller> travellers;//旅客信息
    private Member member;//会员
    private Integer payType;//支付方式 0 支付宝 1 微信 2 其他
    private String payTypeStr;//支付方式字符串格式
    private String orderDesc;//订单备注
    private  String productId;//产品表外键
    private  String memberId;//会员表外键

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if (orderTime!=null) {
            orderTimeStr= DateUtils.date2String(orderTime,"yyyy-MM-dd HH:mm:ss");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusStr() {
        if (orderStatus==0) {
            orderStatusStr="未支付";
        }else if (orderStatus==1){
            orderStatusStr="已支付";
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        if (payType==0) {
            payTypeStr="支付宝";
        } else if (payType==1) {
            payTypeStr="微信";
        }else if(payType==2){
            payTypeStr="其他";
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
