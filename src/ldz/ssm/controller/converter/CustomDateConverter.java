package ldz.ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String,Date>{
    public Date convert(String s) {
        //ʵ�� �����ڴ�ת����������(��ʽ��yyyy-MM-dd HH:mm:ss)

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        
            //ת��ֱ�ӷ���
            try {
				return simpleDateFormat.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
        //���������ʧ�ܷ���null
        return null;

    }
}