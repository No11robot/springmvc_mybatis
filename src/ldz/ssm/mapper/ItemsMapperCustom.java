package ldz.ssm.mapper;

import java.util.List;
import ldz.ssm.po.ItemsCustom;
import ldz.ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {
	//��Ʒ��ѯ�б�
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}