package cn.cisdom.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class Tools {

	public String GetUserLoginIp(HttpServletRequest request){
		//获取登录IP
		String saveIp ;
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			saveIp= ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
		// 多次反向代理后会有多个IP值，第一个为真实IP。
		int index = ip.indexOf(',');
		if (index != -1) {
			saveIp = ip.substring(0, index);
		} else {
			saveIp = ip;
		}
		} else {
			saveIp = request.getRemoteAddr();
		}
		return saveIp;
	}
}
