/**
 * 
 */
package com.zx.vo;

/**
 * 主页项目清单记录数据类
 * 
 * @author biao boss
 *
 */
public class MainVo {

    /**
     * 项目编号
     */
    private String proId;
    /**
     * 项目名称
     */
    private String proName;
    /**
     * 项目类型
     */
    private String proType;
    /**
     * 单位
     */
    private String proUnits;
    /**
     * 项目单价
     */
    private double proPrice;
    /**
     * 项目数量
     */
    private int proAmount;
    /**
     * 项目总价格
     */
    private double proAllPrice;
    /**
     * 项目规格型号
     */
    private String proSize;
    /**
     * 订单操作人员
     */
    private String orderOperation;
    
    
    
    
    public String getProId() {
        return proId;
    }
    public void setProId(String proId) {
        this.proId = proId;
    }
    public String getProName() {
        return proName;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }
    public String getProType() {
        return proType;
    }
    public void setProType(String proType) {
        this.proType = proType;
    }
    public String getProUnits() {
        return proUnits;
    }
    public void setProUnits(String proUnits) {
        this.proUnits = proUnits;
    }
    public double getProPrice() {
        return proPrice;
    }
    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }
    public int getProAmount() {
        return proAmount;
    }
    public void setProAmount(int proAmount) {
        this.proAmount = proAmount;
    }
    public double getProAllPrice() {
        return proAllPrice;
    }
    public void setProAllPrice(double proAllPrice) {
        this.proAllPrice = proAllPrice;
    }
    public String getProSize() {
        return proSize;
    }
    public void setProSize(String proSize) {
        this.proSize = proSize;
    }
    public String getOrderOperation() {
        return orderOperation;
    }
    public void setOrderOperation(String orderOperation) {
        this.orderOperation = orderOperation;
    }
    
    
    
}
