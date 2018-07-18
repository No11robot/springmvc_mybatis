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
		        throw new CustomException("service:�޸ĵ���Ʒ��Ϣ������!");
		 }
		 else {
			//�м����Ʒ��Ϣ����ҵ����
			    //....
			    //����ItemsCustom
			    itemsCustom = new ItemsCustom();
			    //��items������ֵ������itemsCustom
			    BeanUtils.copyProperties(items, itemsCustom);
			    
		 }
		 return itemsCustom;
	    
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		//���ҵ��У�飬ͨ����service�ӿڶԹؼ���������У��
	    //У�� id�Ƿ�Ϊ�գ����Ϊ���׳��쳣

	    //������Ʒ��Ϣʹ��updateByPrimaryKeyWithBLOBs����id����items���������ֶΣ����� ���ı������ֶ�
	    //updateByPrimaryKeyWithBLOBsҪ�����ת��id
	    itemsCustom.setId(id);
	    itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}
}
