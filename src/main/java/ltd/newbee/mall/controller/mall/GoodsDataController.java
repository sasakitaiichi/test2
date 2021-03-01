package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDataVO;
import ltd.newbee.mall.controller.vo.NewBeeMallShoppingCartItemVO;
import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.entity.NewBeeMallGoodsData;
import ltd.newbee.mall.entity.NewBeeMallShoppingCartItem;
import ltd.newbee.mall.service.NewBeeMallGoodsDataService;
import ltd.newbee.mall.service.NewBeeMallShoppingCartService;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GoodsDataController {

    @Resource
    private NewBeeMallGoodsDataService newBeeMallGoodsDataService;

    @GetMapping("/recode")
    public String dataListPage(HttpServletRequest request,
                               HttpSession httpSession) {
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);

        List<NewBeeMallGoodsDataVO> myGoodsDatas = newBeeMallGoodsDataService.getGoodsDataS(user.getUserId());
        request.setAttribute("myGoodsDatas",myGoodsDatas);
        return "mall/index";
    }

    @PostMapping("/recode")
    @ResponseBody
    public Result saveNewBeeMallGoodsData(@RequestBody NewBeeMallGoodsData newBeeMallGoodsData,
                                                 HttpSession httpSession) {
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        newBeeMallGoodsData.setUserId(user.getUserId());
        //todo 判断数量
        String saveResult = newBeeMallGoodsDataService.saveNewBeeMallGoodsData(newBeeMallGoodsData);
        //添加成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(saveResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //添加失败
        return ResultGenerator.genFailResult(saveResult);
    }

    @PutMapping("/recode")
    @ResponseBody
    public Result updateNewBeeMallGoodsData(@RequestBody NewBeeMallGoodsData newBeeMallGoodsData,
                                                   HttpSession httpSession) {
        NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        newBeeMallGoodsData.setUserId(user.getUserId());
        //todo 判断数量
        String updateResult = newBeeMallGoodsDataService.insertNewBeeMallGoodsData(newBeeMallGoodsData);
        //修改成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(updateResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //修改失败
        return ResultGenerator.genFailResult(updateResult);
    }
}
