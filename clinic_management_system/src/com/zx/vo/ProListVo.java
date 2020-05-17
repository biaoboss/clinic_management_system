package com.zx.vo;
/**
 * 
 * @author 彭文大帅比
 *
 */
public class ProListVo {
	private String pro_id;
	private String pro_name;
	private String units;
	private String pro_size;
	private double price;
	private String pro_type;
	private int prostock;
	private  int id;
	private int acount;
	
	private String orderId;
	/**
	 * 订单总价格
	 */
	private double allPrice;
	private String diseaseType;
	private String username;
	private String advice;
	private String orderDate;
	private String patientId;
	
	private int projectAmount;
	/**
	 * 项目清单总价格
	 */
	private double projectPrice;
	
	public ProListVo () {}
	
	
	public ProListVo(String pro_name, int acount ,double price, String pro_type ) {
		super();
		this.pro_name = pro_name;
		this.price = price;
		this.pro_type = pro_type;
		
		this.acount = acount;
	}
	public ProListVo(String pro_id,String pro_name, int acount ,double price, String pro_type ) {
		super();
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.price = price;
		this.pro_type = pro_type;
		this.acount = acount;
	}

	public ProListVo(String pro_id, String pro_name, double price, String pro_type) {
		
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.price = price;
		this.pro_type = pro_type;
		
		
	}



	public ProListVo(String pro_id, String pro_name, String units, String pro_size, double price, String pro_type,
			int prostock) {

		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.units = units;
		this.pro_size = pro_size;
		this.price = price;
		this.pro_type = pro_type;
		this.prostock = prostock;
	}

	public String getPro_id() {
		return pro_id;
	}

	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getPro_size() {
		return pro_size;
	}

	public void setPro_size(String pro_size) {
		this.pro_size = pro_size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPro_type() {
		return pro_type;
	}

	public void setPro_type(String pro_type) {
		this.pro_type = pro_type;
	}

	public int getProstock() {
		return prostock;
	}

	public void setProstock(int prostock) {
		this.prostock = prostock;
	}

	public String toString() {
		return "ProListVo [pro_id=" + pro_id + ", pro_name=" + pro_name + ", units=" + units + ", pro_size=" + pro_size
				+ ", price=" + price + ", pro_type=" + pro_type + ", prostock=" + prostock + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcount() {
		return acount;
	}

	public void setAcount(int acount) {
		this.acount = acount;
	}


    public String getOrderId() {
        return orderId;
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public double getAllPrice() {
        return allPrice;
    }


    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }


    public String getDiseaseType() {
        return diseaseType;
    }


    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getAdvice() {
        return advice;
    }


    public void setAdvice(String advice) {
        this.advice = advice;
    }


    public String getOrderDate() {
        return orderDate;
    }


    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    public String getPatientId() {
        return patientId;
    }


    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }


    public int getProjectAmount() {
        return projectAmount;
    }


    public void setProjectAmount(int projectAmount) {
        this.projectAmount = projectAmount;
    }


    public double getProjectPrice() {
        return projectPrice;
    }


    public void setProjectPrice(double projectPrice) {
        this.projectPrice = projectPrice;
    }
	
	

}
