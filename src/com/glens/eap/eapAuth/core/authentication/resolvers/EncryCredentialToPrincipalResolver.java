package com.glens.eap.eapAuth.core.authentication.resolvers;

import com.glens.eap.eapAuth.core.authentication.Credential;
import com.glens.eap.eapAuth.core.authentication.Principal;
import com.glens.eap.eapAuth.core.authentication.impl.DefaultUserPrincipal;
import com.glens.eap.eapAuth.core.authentication.impl.EncryCredential;
import com.glens.eap.eapAuth.core.model.EncryCredentialInfo;

/** 
 * <p>实现了加密后的凭据转换为对应的用户主体对象的解析器</p>
 * @author  <a href="mailto: xxx@xxx.com">作者中文名</a>
 * @version $Revision$ 
 */ 
public class EncryCredentialToPrincipalResolver implements
		CredentialToPrincipalResolver {

	/**
     * Default class to support if one is not supplied.
     */
    private static final Class<EncryCredential> DEFAULT_CLASS = EncryCredential.class;

    /**
     * Class that this instance will support.
     */
    private Class<?> classToSupport = DEFAULT_CLASS;

    /**
     * Boolean to determine whether to support subclasses of the class to
     * support.
     */
    private boolean supportSubClasses = true;
	
	public final void setSupportSubClasses(boolean supportSubClasses) {
		this.supportSubClasses = supportSubClasses;
	}

	@Override
	public Principal resolvePrincipal(Credential credential) {
		// TODO Auto-generated method stub
		//若类型匹配，则进行转换。
        if (credential != null && this.supports(credential)) {
            EncryCredential encryCredential = (EncryCredential) credential;
            DefaultUserPrincipal principal = new DefaultUserPrincipal();
            //解析加密后凭据信息。
            EncryCredentialInfo encryCredentialInfo = encryCredential.getEncryCredentialInfo();
            //设置用户名为唯一标识。
            if (encryCredentialInfo != null) {
                principal.setId(encryCredentialInfo.getUserId());
                //设置参数表为用户属性。
                principal.setAttributes(encryCredential.getParameters());
            }
            return principal;
        }
        return null;
	}

	@Override
	public boolean supports(Credential credential) {
		// TODO Auto-generated method stub
		return credential != null
        && (this.classToSupport.equals(credential.getClass()) || (this.classToSupport
        .isAssignableFrom(credential.getClass()))
        && this.supportSubClasses);
	}

}
