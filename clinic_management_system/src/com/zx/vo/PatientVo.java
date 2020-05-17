package com.zx.vo;

/**
 * 
 * @author 罗心莲
 *
 */
public class PatientVo {
	private String id;
	private String name;
	private String sex;
	private int age;
	private String phone;
	private String address;
	private String idCard;
	private String nation;
	private String brith;
	private String job;
	private double height;
	private double weight;
	private double bloodPressure;
	private double temperature;
	private String remarks;
	public PatientVo() {}
	public PatientVo(String id, String name, String sex, int age, String phone, String address, String idCard,
			String nation, String brith, String job, double height, double weight, double bloodPressure,
			double temperature, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.address = address;
		this.idCard = idCard;
		this.nation = nation;
		this.brith = brith;
		this.job = job;
		this.height = height;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.temperature = temperature;
		this.remarks = remarks;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getBrith() {
		return brith;
	}
	public void setBrith(String brith) {
		this.brith = brith;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(double bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "PatientVo [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", phone=" + phone
				+ ", address=" + address + ", idCard=" + idCard + ", nation=" + nation + ", brith=" + brith + ", job="
				+ job + ", height=" + height + ", weight=" + weight + ", bloodPressure=" + bloodPressure
				+ ", temperature=" + temperature + ", remarks=" + remarks + "]";
	}

}
