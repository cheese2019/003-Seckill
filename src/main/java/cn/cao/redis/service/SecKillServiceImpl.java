package cn.cao.redis.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Random;

/**
 * @author caobo
 * @version 1.0.0
 * @ClassName SecKillServiceImpl
 * @Description
 * @createTime 2021年09月24日
 */
@Service
public class SecKillServiceImpl implements  SecKillService{
    @Override
    public String doSecKill(String usid, String sid) {
        String message = "";
         String uid = new Random().nextInt()+"";
        String rUId = "uid"+uid;
        String rSID = "sid"+sid;
        String alShop = "shoped"+sid;
        if(uid==null||sid==null){
            message =  "不可为空";
        }else{
            Jedis jedis = new Jedis("39.105.37.118",6379);
            jedis.auth("20210990@cao");
            String ss = jedis.get(rSID);
            if(ss==null){
                ss = "0";
            }
            Integer nums =Integer.parseInt(ss);
            if(nums <= 0 ){
                message = "库存不足";
            }else{
                boolean sTemp = jedis.sismember(alShop,rUId);
                if(sTemp){
                    message = rUId+"不可重复购买";
                }else{
                    Transaction multi = jedis.multi();
                    multi.decr(rSID);
                    multi.sadd(alShop,rUId);
                    List<Object> result = multi.exec();
                    if(result.isEmpty()){
                        message = "秒杀结束";
                    }else{
                        message="购买成功,当前库存：";
                    }

                }
            }
        }
        System.out.println(message);
        return message;
    }
}
