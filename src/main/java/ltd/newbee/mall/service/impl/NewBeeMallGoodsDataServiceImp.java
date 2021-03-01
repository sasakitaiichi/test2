package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDataVO;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexConfigGoodsVO;
import ltd.newbee.mall.controller.vo.NewBeeMallShoppingCartItemVO;
import ltd.newbee.mall.dao.NewBeeMallGoodsDataMapper;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.dao.NewBeeMallShoppingCartItemMapper;
import ltd.newbee.mall.entity.IndexConfig;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.NewBeeMallGoodsData;
import ltd.newbee.mall.entity.NewBeeMallShoppingCartItem;
import ltd.newbee.mall.service.NewBeeMallGoodsDataService;
import ltd.newbee.mall.service.NewBeeMallShoppingCartService;
import ltd.newbee.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NewBeeMallGoodsDataServiceImp implements NewBeeMallGoodsDataService {

    @Autowired
    private NewBeeMallGoodsDataMapper newBeeMallGoodsDataMapper;

    @Autowired
    private NewBeeMallGoodsMapper newBeeMallGoodsMapper;

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Override
    public String saveNewBeeMallGoodsData(NewBeeMallGoodsData newBeeMallGoodsData) {
        NewBeeMallGoodsData temp = newBeeMallGoodsDataMapper.selectByUserIdAndGoodsId(newBeeMallGoodsData.getUserId(), newBeeMallGoodsData.getGoodsId());

        if (newBeeMallGoodsDataMapper.insertSelective(newBeeMallGoodsData) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String insertNewBeeMallGoodsData(NewBeeMallGoodsData newBeeMallGoodsData) {


        if (newBeeMallGoodsDataMapper.insert(newBeeMallGoodsData) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public NewBeeMallGoodsData getNewBeeMallGoodsDataById(Long newBeeMallGoodsDataId) {
        return newBeeMallGoodsDataMapper.selectByPrimaryKey(newBeeMallGoodsDataId);
    }

    @Override
    public List<NewBeeMallGoodsDataVO> getGoodsDataS(Long newBeeMallUserId) {
        List<NewBeeMallGoodsDataVO> newBeeMallGoodsDataVOS = new ArrayList<>();
        List<NewBeeMallGoodsData> newBeeMallGoodsDataS = newBeeMallGoodsDataMapper.selectByUserId(newBeeMallUserId);
        if (!CollectionUtils.isEmpty(newBeeMallGoodsDataS)) {
            //查询商品信息并做数据转换
            List<Long> goodsIds = newBeeMallGoodsDataS.stream().map(NewBeeMallGoodsData::getGoodsId).collect(Collectors.toList());
            List<NewBeeMallGoods> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(goodsIds);
            newBeeMallGoodsDataVOS = BeanUtil.copyList(newBeeMallGoods, NewBeeMallGoodsDataVO.class);
            for (NewBeeMallGoodsDataVO newBeeMallGoodsDataVO : newBeeMallGoodsDataVOS) {
                String goodsName = newBeeMallGoodsDataVO.getGoodsName();
                String goodsIntro = newBeeMallGoodsDataVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 30) {
                    goodsName = goodsName.substring(0, 30) + "...";
                    newBeeMallGoodsDataVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 22) {
                    goodsIntro = goodsIntro.substring(0, 22) + "...";
                    newBeeMallGoodsDataVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        return newBeeMallGoodsDataVOS;
    }

//            Map<Long, NewBeeMallGoods> newBeeMallGoodsMap = new HashMap<>();
//            if (!CollectionUtils.isEmpty(newBeeMallGoods)) {
//                newBeeMallGoodsMap = newBeeMallGoods.stream().collect(Collectors.toMap(NewBeeMallGoods::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
//            }
//
//            for (NewBeeMallGoodsData newBeeMallGoodsData : newBeeMallGoodsDataS) {
//                NewBeeMallGoodsDataVO newBeeMallGoodsDataVO = new NewBeeMallGoodsDataVO();
//                BeanUtil.copyProperties(newBeeMallGoodsData, newBeeMallGoodsDataVO);
//                if (newBeeMallGoodsMap.containsKey(newBeeMallGoodsData.getGoodsId())) {
//                    NewBeeMallGoods newBeeMallGoodsTemp = newBeeMallGoodsMap.get(newBeeMallGoodsData.getGoodsId());
//                    newBeeMallGoodsDataVO.setGoodsCoverImg(newBeeMallGoodsTemp.getGoodsCoverImg());
//                    String goodsName = newBeeMallGoodsTemp.getGoodsName();
//                    String goodsIntro = NewBeeMallGoodsDataVO.getGoodsIntro();
//                    // 字符串过长导致文字超出的问题
//                    if (goodsName.length() > 28) {
//                        goodsName = goodsName.substring(0, 28) + "...";
//                    }
//                    newBeeMallGoodsDataVO.setGoodsName(goodsName);
//                    newBeeMallGoodsDataVO.setSellingPrice(newBeeMallGoodsTemp.getSellingPrice());
//                    newBeeMallGoodsDataVOS.add(newBeeMallGoodsDataVO);
//                }
//            }
//        }
//        return newBeeMallGoodsDataVOS;
//    }
}
