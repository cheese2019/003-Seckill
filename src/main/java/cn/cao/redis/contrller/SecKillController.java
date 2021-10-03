package cn.cao.redis.contrller;

import cn.cao.redis.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author caobo
 * @version 1.0.0
 * @ClassName SecKill
 * @Description
 * @createTime 2021年09月24日
 */
@Controller
public class SecKillController {
    @Autowired
    SecKillService secKillService;

    @RequestMapping("doseckill")
    public String deKill(Model model,String userId,String shopId){
        model.addAttribute("killResult",secKillService.doSecKill(userId,shopId));
        System.out.println("v2.2.2.2");
        System.out.println("this is hotfix");
        return "result";
    }
}
