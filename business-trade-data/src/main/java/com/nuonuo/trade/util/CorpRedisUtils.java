package com.nuonuo.trade.util;

import com.nuonuo.trade.entity.CorpInvoice;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName:      CorpRedisUtils
 * @Description:    获取企业资质工具类
 * 
 * 注意：
 * CorpInvoice新加字段时：由于redis中的数据序列化成对象时，新加属性在序列化成对象时为空,特别注意！！！
 * 处理方案：新加字段时，后台先进行redis数据维护(比如：更新企业资质)。
 * 
 * CorpInvoice删除字段时：不影响redis操作！
 * 
 * @author:          fht
 * @date:            2017年8月16日
 */
@Component
public class CorpRedisUtils
{

	/**
	 * @Description:    根据税号和资质状态获取企业资质基本信息(不包含证书内容)
	 * @param taxno		企业税号
	 * @return:
	 */
	public CorpInvoice getCorpInvoice(String taxno){
		//todo 为了不报错，减少牵扯，将实现去除
		return null;
	}


}
