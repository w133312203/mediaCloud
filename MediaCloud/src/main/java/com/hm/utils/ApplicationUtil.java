package com.hm.utils;

public class ApplicationUtil {
	
	/**
	 * 图片路径
	 */
	public static String IMAGE_URL = (String) SpringPropertiesUtil.getContextProperty("image_url");
	
	/**
	 * 主域名
	 */
	public static String MAIN_HTTP = (String) SpringPropertiesUtil.getContextProperty("main_http");
	
	/**
	 * 页面路面
	 */
	public static String JSP_URL = (String) SpringPropertiesUtil.getContextProperty("jsp_url");
	
	/**
	 * OSS_KEY
	 */
	public static String ACCESS_KEY_ID = (String) SpringPropertiesUtil.getContextProperty("access_key_id");
	
	/**
	 * OSS_SECRET
	 */
	public static String ACCESS_KEY_SECRET = (String) SpringPropertiesUtil.getContextProperty("access_key_secret");
	
	/**
	 * OSS_SECURITY_TOKEN
	 */
	public static String SECURITY_TOKEN = (String) SpringPropertiesUtil.getContextProperty("security_token");
	
	/**
	 * 点播主域名
	 */
	public static String VOD_DOMAIN = (String) SpringPropertiesUtil.getContextProperty("vod_domain");
	
	/**
	 * END_POINT
	 */
	public static String END_POINT = (String) SpringPropertiesUtil.getContextProperty("end_point");
	
	/**
	 * 视频BUCKET
	 */
	public static String BUCKET_VIDEO = (String) SpringPropertiesUtil.getContextProperty("bucket_video");
	
	/**
	 * 图片BUCKET
	 */
	public static String BUCKET_PIC = (String) SpringPropertiesUtil.getContextProperty("bucket_pic");
	
	/**
	 * 图片主域名
	 */
	public static String PICTURE_DOMAIN = (String) SpringPropertiesUtil.getContextProperty("picture_domain");
	
	/**
	 * 图片缩略图
	 */
	public static String PICTURE_SLT = (String) SpringPropertiesUtil.getContextProperty("picture_slt");
	
}
