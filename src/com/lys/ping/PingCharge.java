package com.lys.ping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.App;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.ChargeCollection;
import com.sun.istack.internal.logging.Logger;



/**
 * Charge 对象相关示例
 * @author sunkai
 * 
 * 该实例程序演示了如何从 ping++ 服务器获得 charge ，查询 charge。
 * 
 * 开发者需要填写 apiKey 和 appId , apiKey 可以在 ping++ 管理平台【应用信息里面查看】
 * 
 * apiKey 有 TestKey 和 LiveKey 两种。 
 * 
 * TestKey 模式下不会产生真实的交易。
 */
public class PingCharge {
	Logger logger = Logger.getLogger(getClass());
	public PingCharge(){
		   Pingpp.apiKey = apiKey;
	}
	/**
	 * pingpp 管理平台对应的 API key
	 */
	public static String apiKey = "sk_live_zGMmfNLh87sghw4qjeWs4DnP";
	/**
	 * pingpp 管理平台对应的应用 ID
	 */
	public static String appId = "app_j9S4O4G00GC0jHWj";
	
    public static void main(String[] args) {
        Pingpp.apiKey = apiKey;
        PingCharge ce = new PingCharge();
        System.out.println("---------创建 charge");
        //Charge charge = ce.charge();
        System.out.println("---------查询 charge");
        //ce.retrieve(charge.getId());
        System.out.println("---------查询 charge列表");
        //ce.all();
    }

    /**
     * 创建 Charge
     * 
     * 创建 Charge 用户需要组装一个 map 对象作为参数传递给 Charge.create();
     * map 里面参数的具体说明请参考：https://pingxx.com/document/api#api-c-new
     * @return
     */
    public Charge charge(String channel,int amount,String Subject,String Body,String OrderId) {
    	logger.info("channel:"+channel+" amount:"+amount+" Subject:"+Subject+" Body:"+Body+" OrderId:"+OrderId);
        Charge charge = null;
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", amount*100);
        chargeMap.put("currency", "cny");
        chargeMap.put("subject", Subject);
        chargeMap.put("body", Body);
        chargeMap.put("order_no", OrderId);
        chargeMap.put("channel", channel);
        chargeMap.put("client_ip", "127.0.0.1");
        Map<String,Object> extra =new HashMap<String, Object>();
        if(channel.equals("alipay_wap")){
        	extra.put("success_url", "http://www.wangzhong.com/index/PayMoneyEnd");
        }else if(channel.equals("alipay_wap")){
        	
        }else if(channel.equals("upacp_wap")||channel.equals("upmp_wap")){
        	extra.put("result_url", "http://www.wangzhong.com/index/PayMoneyEnd");
        }
        //extra.put("cancel_url", "http://www.wangzhong.com/");
        chargeMap.put("extra", extra);
        Map<String, String> app = new HashMap<String, String>();
        app.put("id",appId);
        chargeMap.put("app", app);
        try {
            //发起交易请求
            charge = Charge.create(chargeMap);
            System.out.println(charge);
        } catch (PingppException e) {
            e.printStackTrace();
        }
        return charge;
    }

    /**
     * 查询 Charge
     * 
     * 该接口根据 charge Id 查询对应的 charge 。
     * 参考文档：https://pingxx.com/document/api#api-c-inquiry
     * 
     * 该接口可以传递一个 expand ， 返回的 charge 中的 app 会变成 app 对象。
     * 参考文档： https://pingxx.com/document/api#api-expanding
     * @param id
     */
    public void retrieve(String id) {
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            List<String> expande = new ArrayList<String>();
            expande.add("app");
            param.put("expand", expande);
            //Charge charge = Charge.retrieve(id);
            //Expand app
            Charge charge = Charge.retrieve(id, param);
            if (charge.getApp() instanceof App) {
                //App app = (App) charge.getApp();
                // System.out.println("App Object ,appId = " + app.getId());
            } else {
                // System.out.println("String ,appId = " + charge.getApp());
            }

            System.out.println(charge);

        } catch (PingppException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分页查询Charge
     * 
     * 该接口为批量查询接口，默认一次查询10条。
     * 用户可以通过添加 limit 参数自行设置查询数目，最多一次不能超过 100 条。
     * 
     * 该接口同样可以使用 expand 参数。
     * @return
     */
    public ChargeCollection all() {
        ChargeCollection chargeCollection = null;
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("limit", 3);

//增加此处设施，刻意获取app expande 
//        List<String> expande = new ArrayList<String>();
//        expande.add("app");
//        chargeParams.put("expand", expande);

        try {
            chargeCollection = Charge.all(chargeParams);
            System.out.println(chargeCollection);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
			e.printStackTrace();
		}
        return chargeCollection;
    }
}
