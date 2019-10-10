package cn.itsource;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**  
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
@Slf4j
public class GetCodeUtil {
	
	//用户名
	private static String Uid = "hzwxx";
	
	//接口安全秘钥
	private static String Key = "d41d8cd98f00b204e980";
	
	/*//手机号码，多个号码如13800000000,13800000001,13800000002
	private static String smsMob = "15719478614";
	
	//短信内容
	private static String smsText = "验证码：8888";*/
	

	public static void sendCode(String phone,String text){
		HttpClientUtil client = HttpClientUtil.getInstance();
		int result =client.sendMsgUtf8(Uid, Key, text, phone);
		if(result>0){
			log.info("UTF8成功发送条数=="+result);
		}else{
			log.debug(client.getErrorMsg(result));
		}
	}
}
