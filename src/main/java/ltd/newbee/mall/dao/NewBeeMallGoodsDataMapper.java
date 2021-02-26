package ltd.newbee.mall.dao;


import ltd.newbee.mall.entity.NewBeeMallShoppingCartItem;
import org.apache.ibatis.annotations.Param;
import ltd.newbee.mall.entity.NewBeeMallGoodsData;
import java.util.List;

public interface NewBeeMallGoodsDataMapper {

    int insert(NewBeeMallGoodsData record);

    int insertSelective(NewBeeMallGoodsData record);

    NewBeeMallGoodsData selectByPrimaryKey(Long dataId);

    NewBeeMallGoodsData selectByUserIdAndGoodsId(@Param("newBeeMallUserId") Long newBeeMallUserId, @Param("goodsId") Long goodsId);

    List<NewBeeMallGoodsData> selectByUserId(@Param("newBeeMallUserId") Long newBeeMallUserId, @Param("number") int number);

    int updateByPrimaryKey(NewBeeMallGoodsData record);

    int updateByPrimaryKeySelective(NewBeeMallGoodsData record);

    int deleteByPrimaryKey(Long cartItemId);

}
