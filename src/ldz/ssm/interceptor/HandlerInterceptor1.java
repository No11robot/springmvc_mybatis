package ldz.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandlerInterceptor1 implements HandlerInterceptor{

	//ִ��Handler���ִ�д˷���
    //Ӧ�ó�����ͳһ�쳣����ͳһ��־����
	@Override
	public void afterCompletion(HttpServletRequest arg0, 
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("===1===HandlerInterceptor1====afterCompletion");
		
	}

	//����Handler����֮�󣬷���modelAndView֮ǰִ��
    //Ӧ�ó�����modelAndView�����������õ�ģ������(����˵�����)�����ﴫ����ͼ��
	//Ҳ����������ͳһָ����ͼ
	@Override
	public void postHandle(HttpServletRequest arg0, 
			HttpServletResponse arg1, Object arg2, 
			ModelAndView arg3)
			throws Exception {
		System.out.println("===1===HandlerInterceptor1====postHandle");
		
	}

	//���� Handler����֮ǰִ��
    //���������֤�������Ȩ
    //���������֤�������֤ͨ����ʾ��ǰ�û�û�е�½����Ҫ�˷������ز�������ִ��
	@Override
	public boolean preHandle(HttpServletRequest arg0, 
			HttpServletResponse arg1, 
			Object arg2) throws Exception {
		System.out.println("===1===HandlerInterceptor1====preHandle");
		//return false��ʾ���أ�������ִ��
        //return true��ʾ����
		return true;
	}

}
