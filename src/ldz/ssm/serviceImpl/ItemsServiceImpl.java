package ldz.ssm.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ldz.ssm.exception.CustomException;
import ldz.ssm.mapper.ItemsMapper;
import ldz.ssm.mapper.ItemsMapperCustom;
import ldz.ssm.po.Items;
import ldz.ssm.po.ItemsCustom;
import ldz.ssm.po.ItemsQueryVo;
import ldz.ssm.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService{

	@Autowired
    private ItemsMapperCustom itemsMapperCustom;
	@Autowired
	private ItemsMapper itemsMapper;
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

	@Override
	public ItemsCustom findItemsById(int id) throws Exception {
		ItemsCustom itemsCustom=null;
		Items items = itemsMapper.selectByPrimaryKey(id);
		 if(items==null){
		        throw new CustomException("service:修改的商品信息不存在!");
		 }
		 else {
			//中间对商品信息进行业务处理
			    //....
			    //返回ItemsCustom
			    itemsCustom = new ItemsCustom();
			    //将items的属性值拷贝到itemsCustom
			    BeanUtils.copyProperties(items, itemsCustom);
			    
		 }
		 return itemsCustom;
	    
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		//添加业务校验，通常在service接口对关键参数进行校验
	    //校验 id是否为空，如果为空抛出异常

	    //更新商品信息使用updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段，包括 大文本类型字段
	    //updateByPrimaryKeyWithBLOBs要求必须转入id
	    itemsCustom.setId(id);
	    itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}
}
