package ldz.ssm.service;
import java.util.List;

import ldz.ssm.po.*;
public interface ItemsService {

	//��Ʒ��ѯ�б�
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
    //����id��ѯ��Ʒ
    public ItemsCustom findItemsById(int id) throws Exception;
    
  //�޸���Ʒ��Ϣ
    /**
     *
     * <p>Title: updateItems</p>
     * <p>Description: </p>
     * @param id �޸���Ʒ��id
     * @param itemsCustom �޸ĵ���Ʒ��Ϣ
     * @throws Exception
     */
     void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
}
