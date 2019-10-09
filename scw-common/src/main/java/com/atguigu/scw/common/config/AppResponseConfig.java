package com.atguigu.scw.common.config;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//相应类:为了统一相应数据的格式，让调用者快速判断成功失败，或者异常信息
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppResponseConfig<T> implements Serializable{
	//相应状态码
	private int code;//10000-代表成功，
	private String message;
	private T data;
	
	//成功
	public static <T>AppResponseConfig<T> ok (String message,T data){
		return new AppResponseConfig<T>(10000,message,data);//封装相应状态码+信息+数据
	}
	
	//失败
	public static <T>AppResponseConfig<T> fail (String message,T data){
		return new AppResponseConfig<T>(10001,message,data);//封装相应状态码+信息+数据
	}
}
