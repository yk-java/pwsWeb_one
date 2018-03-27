/**

 * @Title: TokenResult.java

 * @Package com.glens.eap.platform.framework.web.support.response

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * 

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:48:50

 * @version V1.0

 */

package com.glens.eap.platform.framework.web.support.response;

/**

 * @ClassName: TokenResult

 * @Description: TODO

 * @author Comsys-Administrator

 * @date 2016-5-6 下午5:48:50

 *
 */

public class TokenResult extends BeanResult {

	private String ticket;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
