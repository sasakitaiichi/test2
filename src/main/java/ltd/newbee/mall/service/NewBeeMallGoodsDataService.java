package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDataVO;
import ltd.newbee.mall.controller.vo.NewBeeMallShoppingCartItemVO;
import ltd.newbee.mall.entity.NewBeeMallGoodsData;
import ltd.newbee.mall.entity.NewBeeMallShoppingCartItem;

import java.util.List;

public interface NewBeeMallGoodsDataService {

    /**
     * 保存商品
     *
     * @param newBeeMallGoodsData
     * @return
     */
    String saveNewBeeMallGoodsData(NewBeeMallGoodsData newBeeMallGoodsData);

    /**
     * 修改属性
     *
     * @param newBeeMallGoodsData
     * @return
     */
    String insertNewBeeMallGoodsData(NewBeeMallGoodsData newBeeMallGoodsData);

    /**
     * 获取商品详情
     *
     * @param newBeeMallGoodsDataId
     * @return
     */
    NewBeeMallGoodsData getNewBeeMallGoodsDataById(Long newBeeMallGoodsDataId);

    /**
     * 获取我的购物车中的列表数据
     *
     * @param newBeeMallUserId
     * @return
     */
    List<NewBeeMallGoodsDataVO> getGoodsDataS(Long newBeeMallUserId);
}
