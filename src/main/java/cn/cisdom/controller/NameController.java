package cn.cisdom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cisdom.pojo.Name;
import cn.cisdom.res.BaseResponse;
import cn.cisdom.service.NameService;
import cn.cisdom.util.JWT;
import cn.cisdom.util.RedisUtil;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
@RequestMapping(value="/abc")
public class NameController {
	  private Logger logger = Logger.getLogger(NameController.class);
	@Autowired
	private NameService nameService;

	@Autowired
	private RedisUtil redisutil;

	
	@ResponseBody
	@RequestMapping(value="/Login")//接口的URL
	public Object  getItemParamList(@RequestParam("user") String user,
			@RequestParam("password") String password ,
			HttpServletRequest request) throws Exception{

	String password1 =nameService.selectUserByID(user);
	 String token = null;
	String message = "";
	int res ;
	if (password1 == null){
		message="没有此用户";
		res= 3;
	}else{
	if (password1.equals(password)){
		message="正确";
		res= 1;
		Name name =new Name();
		name.setUser(user);
		name.setMima(password1);
		name.setZhiwei("测试");
		  token = JWT.sign(name, 60L* 1000L* 30L);
		 redisutil.set(token, 1);
	}else
	{
		message="失败";
		res= 2;
	}
	}
	
		Map map = new HashMap();
		map.put("res", res);
		map.put("msg", message);
		map.put("ip","1");
		BaseResponse breq = new BaseResponse();
		breq.setCode("200");
		breq.setMessage(token);
		breq.setReqNo("1");
		breq.setMessageBody(map);
			return breq;	
			
	}
	
	
	

	
}
