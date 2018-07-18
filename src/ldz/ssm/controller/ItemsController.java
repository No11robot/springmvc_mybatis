package ldz.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ldz.ssm.exception.CustomException;
import ldz.ssm.po.ItemsCustom;
import ldz.ssm.po.ItemsQueryVo;
import ldz.ssm.service.ItemsService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

//ʹ��@Controller����ʶ����һ��������
@Controller
//Ϊ�˶�url���з������ �����������ﶨ���·�������շ���url�Ǹ�·��+��·��
//���磺��Ʒ�б�/items/queryItems.action
//@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    //��Ʒ��ѯ�б�
    @RequestMapping("/queryItems")
    //ʵ�� ��queryItems������url����ӳ�䣬һ��������Ӧһ��url
    //һ�㽨�齫url�ͷ���д��һ��
    public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception{
        //����service�������ݿ⣬��ѯ��Ʒ�б�
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

        //����ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //�൱��request��setAttribute����,��jspҳ����ͨ��itemsListȡ����
        modelAndView.addObject("itemsList",itemsList);

        //ָ����ͼ
        //�±ߵ�·�����������ͼ������������jsp��·��ǰ׺�ͺ�׺���޸�Ϊitems/itemsList
        //modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        //�±ߵ�·�����þͿ��Բ��ڳ�����ָ��jsp·����ǰ׺�ͺ�׺
        modelAndView.setViewName("items/itemsList");

        return modelAndView;
    }
    
    
    @RequestMapping("/editItems")
  //@RequestParam���ָ��request����������ƺ��βν��а󶨡�
  //ͨ��required����ָ�������Ƿ����Ҫ����
  //ͨ��defaultValue��������Ĭ��ֵ�����id����û�д��룬��Ĭ��ֵ���βΰ󶨡�
  //public String editItems(Model model, @RequestParam(value="id",required=true) Integer items_id)throws Exception {
  public String editItems(Model model,
		  @RequestParam(value="id") Integer items_id)throws Exception {

      //����service������Ʒid��ѯ��Ʒ��Ϣ
      ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
      //�ж���Ʒ�Ƿ�Ϊ�գ�����idû�в�ѯ����Ʒ���׳��쳣����ʾ�û���Ʒ��Ϣ������
//      if(itemsCustom == null){
//          throw new CustomException("controller:�޸ĵ���Ʒ��Ϣ������!");
//      }
      //ͨ���β��е�model��model���ݴ���ҳ��
      //�൱��modelAndView.addObject����
      model.addAttribute("itemsCustom", itemsCustom);

      return "items/editItems";
  }

    /**
	 * form���ύ Date�������ݰ�
	 * <������ϸ����>
	 * @param binder
	 * @see [�ࡢ��#��������#��Ա]
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}

    //��Ʒ��Ϣ�޸��ύ
	//����ҪУ���pojoǰ�����@Validated���������BindingResult bindingResult����У�������Ϣ
	//ע�⣺@Validated��BindingResult bindingResult�ɶԳ��֣�һǰһ��
    @RequestMapping(value="/editItemsSubmit")
    public String editItemsSubmit(HttpServletRequest request,
    		Model model, Integer id,
    		@Validated ItemsCustom itemsCustom,BindingResult bindingResult,
    		MultipartFile items_pic)throws Exception {
    	//ԭʼ����
    	String originalFilename = items_pic.getOriginalFilename();
    	//�ϴ�ͼƬ
    	if(items_pic!=null && originalFilename!=null && originalFilename.length()>0){

    	    //�洢ͼƬ������·��
    	    String pic_path = "C:\\Users\\������\\Desktop\\tem\\";


    	    //�µ�ͼƬ����
    	    String newFileName = id + originalFilename.substring(originalFilename.lastIndexOf("."));
    	    //��ͼƬ
    	    File newFile = new File(pic_path+newFileName);

    	    //���ڴ��е�����д�����
    	    items_pic.transferTo(newFile);

    	    //����ͼƬ����д��itemsCustom��
    	    itemsCustom.setPic(newFileName);

    	}
    	//��ȡУ�������Ϣ
    	if(bindingResult.hasErrors()){
    	    // ���������Ϣ
    	    List<ObjectError> allErrors = bindingResult.getAllErrors();

    	    for (ObjectError objectError :allErrors){
    	        // ���������Ϣ
    	        System.out.println(objectError.getDefaultMessage());
    	    }
    	    // ��������Ϣ����ҳ��
    	    model.addAttribute("allErrors", allErrors);

    	    //����ֱ��ʹ��model���ύpojo���Ե�ҳ��
    	    model.addAttribute("itemsCustom", itemsCustom);

    	    // �������µ���Ʒ�޸�ҳ��
    	    return "items/editItems";
    	}
//    	System.out.println(id);
//    	System.out.println("================"+itemsCustom);
        //����service������Ʒ��Ϣ��ҳ����Ҫ����Ʒ��Ϣ�����˷���
        itemsService.updateItems(id, itemsCustom);
        
        //����ModelAndView
//        ModelAndView modelAndView = new ModelAndView();
//        //����һ���ɹ�ҳ��
//        modelAndView.setViewName("success");
//        return "success";
    	//�ض���
    	return "redirect:queryItems.action";
    }
//    ����ɾ�� ��Ʒ��Ϣ
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id) throws Exception{
    	//����service����ɾ����Ʒ
    	//....
		return "success";
    	
    }
  //��Ʒ��ѯ�б�
    @RequestMapping("/editItemsQuery")
    //ʵ�� ��queryItems������url����ӳ�䣬һ��������Ӧһ��url
    //һ�㽨�齫url�ͷ���д��һ��
    public ModelAndView editItemsQuery(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception{
        //����service�������ݿ⣬��ѯ��Ʒ�б�
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

        //����ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //�൱��request��setAttribute����,��jspҳ����ͨ��itemsListȡ����
        modelAndView.addObject("itemsList",itemsList);

        //ָ����ͼ
        //�±ߵ�·�����������ͼ������������jsp��·��ǰ׺�ͺ�׺���޸�Ϊitems/itemsList
        //modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        //�±ߵ�·�����þͿ��Բ��ڳ�����ָ��jsp·����ǰ׺�ͺ�׺
        modelAndView.setViewName("items/editItemsQuery");

        return modelAndView;
    }
 // �����޸���Ʒ�ύ
 // ͨ��ItemsQueryVo���������ύ����Ʒ��Ϣ������Ʒ��Ϣ�洢��itemsQueryVo��itemsList�����С�
 @RequestMapping("/editItemsAllSubmit")
 public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {

     return "success";
 }
	 //��ѯ��Ʒ��Ϣ�����json
	//itemsView/{id}��ߵ�{id}��ʾռλ����ͨ��@PathVariable��ȡռλ���еĲ�����
	//@PathVariable������Ҫ��ռλ��һ�£��β����������һ��
	//���ռλ���е����ƺ��β���һ�£���@PathVariable���Բ�ָ������
//	@RequestMapping("/itemsView/{id}")
//	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer items_id)throws Exception{
//	
//	   //����service��ѯ��Ʒ��Ϣ
//	   ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
//	
//	   return itemsCustom;
//	
//	}

}
