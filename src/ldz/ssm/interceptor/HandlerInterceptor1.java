package ldz.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandlerInterceptor1 implements HandlerInterceptor{

	//执行Handler完成执行此方法
    //应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest arg0, 
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("===1===HandlerInterceptor1====afterCompletion");
		
	}

	//进入Handler方法之后，返回modelAndView之前执行
    //应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，
	//也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest arg0, 
			HttpServletResponse arg1, Object arg2, 
			ModelAndView arg3)
			throws Exception {
		System.out.println("===1===HandlerInterceptor1====postHandle");
		
	}

	//进入 Handler方法之前执行
    //用于身份认证、身份授权
    //比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest arg0, 
			HttpServletResponse arg1, 
			Object arg2) throws Exception {
		System.out.println("===1===HandlerInterceptor1====preHandle");
		//return false表示拦截，不向下执行
        //return true表示放行
		return true;
	}

}
