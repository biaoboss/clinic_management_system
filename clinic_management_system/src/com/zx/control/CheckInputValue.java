package com.zx.control;

import java.util.regex.Pattern;
/**
 * 
 * @author luo
 *
 */
public class CheckInputValue {
	
	/**
	 * 判断输入的值是否是数值型
	 * @param value
	 * @return
	 */
	public static boolean checkIsDigit(String value) {
	    for(int i = 0; i < value.length();i++) {
	      if(Character.isDigit(value.charAt(i))) {
	        return true;
	      }
	    }
	    return false;
	  }
	/**
	 * 判断输入的年份是否合法
	 */
	public static boolean checkDateFormateIsLagal(String date) {
	    if(date.length() != 10) {
	      return false;
	    }else if(date.charAt(4) != '-' ||date.charAt(7) != '-') {
	      return false;
	    }else if(Integer.parseInt(date.substring(0,4)) < 0) {
	      //年份不能小于0
	      return false;
	    }else if(Integer.parseInt(date.substring(5,7)) > 12) {
	      //月份不能大于12
	      return false;
	    }else if(Integer.parseInt(date.substring(8,10)) <= 0 ||
	      Integer.parseInt(date.substring(8,10)) >31){
	      //日期不能大于31
	        return false;
	      }
	      return true;
	  }
	/**
	 * 检查输入的年龄是否三位数以内的整数
	 * @param age
	 * @return
	 */
	  public boolean checkAgeIsLegal(String age){
		  String pattern="\\d{1,3}";
		  boolean isMatch=Pattern.matches(pattern, age);
		  System.out.println(isMatch);
		  return isMatch;
	  }
	  /**
	   * 判断输入的体重和血压是否合法
	   * @param height
	   * @return
	   */
	  public boolean checkValueIsLegal(String value) {
		  String pattern1="\\d{1,3}";
		  String pattern2="\\d{1,3}\\.\\d{1,2}";
		  boolean isMatch1=Pattern.matches(pattern1, value);
		  boolean isMatch2=Pattern.matches(pattern2, value);
		  boolean flag=isMatch1||isMatch2;
		  return flag;
	  }
	  /**
	   * 判断输入的体温是否 合法
	   * @param height
	   * @return
	   */
	  public boolean checkTemperatureIsLegal(String value) {
		  String pattern1="\\d{1,2}";
		  String pattern2="\\d{1,2}\\.\\d";
		  boolean isMatch1=Pattern.matches(pattern1, value);
		  boolean isMatch2=Pattern.matches(pattern2, value);
		  boolean flag=isMatch1||isMatch2;
		  return flag;
	  }
	  /**
	   * 判断输入的身高是否 合法
	   * @param height
	   * @return
	   */
	  public boolean checkHeightIsLegal(String value) {
		  String pattern1="\\d{1,3}";
		  String pattern2="\\d{1,3}\\.\\d";
		  boolean isMatch1=Pattern.matches(pattern1, value);
		  boolean isMatch2=Pattern.matches(pattern2, value);
		  boolean flag=isMatch1||isMatch2;
		  return flag;
	  }
}

