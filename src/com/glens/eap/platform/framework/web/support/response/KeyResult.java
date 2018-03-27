/**

 * @Title: KeyResult.java

 * @Package com.glens.eap.platform.framework.web.support.response

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * 

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:38:26

 * @version V1.0

 */

package com.glens.eap.platform.framework.web.support.response;

/**

 * @ClassName: KeyResult

 * @Description: TODO

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:38:26

 *
 */

public class KeyResult extends ResponseResult {

	private Object generateKey;

	public Object getGenerateKey() {
		return generateKey;
	}

	public void setGenerateKey(Object generateKey) {
		this.generateKey = generateKey;
	}
	
}
