package ldz.ssm.mapper;

import java.util.List;
import ldz.ssm.po.ItemsCustom;
import ldz.ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {
	//商品查询列表
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}