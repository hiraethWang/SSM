package cn.cisdom.controller;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName){
		// 动态跳转页面
		return formName;
	}

	@ResponseBody
	@RequestMapping(value="/Time")
	 public String Time(){
		
//		long start = System.currentTimeMillis();
//		 final long end = start + 1 *  6 *1000;
		 final Timer timer =new Timer();

		
		 timer.schedule(new TimerTask(){
			 public void run(){
				 System.out.println("倒计时启动了");
			 }
		 }, 60000);

		 
		return "ceshi";
	}
}
