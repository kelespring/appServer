package org.appserver.web.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量
 * @author chao
 *
 */
public interface CommonConstant {
	
	//设备类型
	public static Byte DEVTYPE_PC = 0;
	public static Byte DEVTYPE_PHONE = 1;

	public static final Map<Byte,String> devTypeMap = new HashMap<Byte,String>(){
		{
			put(DEVTYPE_PC,"浏览器");
			put(DEVTYPE_PHONE,"浏览器");
		}
	};
	
	public static String SESSION_USEROBJ_KEY = "appserver:user";
}
