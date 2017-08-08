package cn.cisdom.Interceptor;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.cisdom.pojo.Name;
import cn.cisdom.res.BaseResponse;
import cn.cisdom.util.JWT;
import cn.cisdom.util.RedisUtil;


public class TokenInterceptor implements HandlerInterceptor{
	  BaseResponse breq = new BaseResponse();
	  
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView model) throws Exception {
    }

    //拦截每个请求
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
      
        //token不存在
        if(null != token) {
            Name login = JWT.unsign(token, Name.class);
            RedisUtil redisUtil = new RedisUtil();
            String loginId = (String) redisUtil.get(token);
         
            //解密token后的loginId与用户传来的loginId不一致，一般都是token过期
            
            if(null != loginId && null != login) {
                if(loginId .equals (login.getUser())) {
                	breq.setCode("200");
            		breq.setMessage("正确");
            		breq.setReqNo("1");
            		breq.setMessageBody("");
                    return true;
                }
                else{
                	breq.setCode("404");
            		breq.setMessage("token不一致,请重新登录！");
            		breq.setReqNo("1");
            		breq.setMessageBody("");
                    return false;
                }
            }
            else
            {
            	breq.setCode("404");
        		breq.setMessage("请重新登录");
        		breq.setReqNo("1");
        		breq.setMessageBody("");
                return false;
            }
        }
        else
        {
        	
    		breq.setCode("404");
    		breq.setMessage("请重新登录");
    		breq.setReqNo("1");
    		breq.setMessageBody("");
         
            return false;
        }
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out) {
      
        response.setContentType("application/json; charset=utf-8");  
        String json = JSON.toJSONString(breq);
        out.print(json);
        out.flush();
        out.close();
    }

}