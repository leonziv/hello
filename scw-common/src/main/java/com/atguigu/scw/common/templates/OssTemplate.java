package com.atguigu.scw.common.templates;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class OssTemplate {

	// 返回值为string
	// string为阿里云的URL地址(我们按照他的规则拼接出来的)
	// 前端拿到后，后面需要使用这些URL地址
	// 图片再项目发布页面中上传图片发布给服务器
	// multipartFile代表上传的一个文件，相当于了FileStream
	// multipartFile含有文件大小、名字、流
	public String upLoadImg(MultipartFile multipartFile) throws Exception {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String scheme = "https://";// 1
		String endpoint = "oss-cn-shanghai.aliyuncs.com";// 3
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = "LTAI4Fg7KK1HTzVAKRXo7Upo";
		String accessKeySecret = "OOXg55r3DVq6clFj68urOnd1hl6e2A";
		String bucketName = "lzw-scw-project";// 2
		String imgsDir = "imgs/";// 4
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(scheme + endpoint, accessKeyId, accessKeySecret);
		File file = new File("C:\\Users\\13758\\Documents\\1.jpg");
		// 上传文件流。
		InputStream inputStream = multipartFile.getInputStream();//获取multipart里面的上传文件流
		String objectName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString().replace("-", "") + "_"
				+ multipartFile.getOriginalFilename();// 5,获取文件名
		ossClient.putObject(bucketName, imgsDir + objectName, inputStream);

		// https://lzw-scw-project.oss-cn-shanghai.aliyuncs.com/imgs/1569400483998_2c6512430b334dbe9a9b8d98e9d5c868_1.jpg
		// 阿里云系统URL拼接地址规则=scheme+bucketName+"."+endpoint+"/"+imgsDir+objectName;
		// 关闭OSSClient。
		ossClient.shutdown();
		String stringURL = scheme + bucketName + "." + endpoint + "/" + imgsDir + objectName;
		return stringURL;
	}

}
