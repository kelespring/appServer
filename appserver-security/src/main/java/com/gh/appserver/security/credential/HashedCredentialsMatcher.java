package com.gh.appserver.security.credential;

import com.gh.appserver.security.authc.AuthenticationInfo;
import com.gh.appserver.security.mgt.AuthenticationToken;

public class HashedCredentialsMatcher implements CredentialsMatcher {
	private String hashAlgorithm;
    private int hashIterations;
    private boolean hashSalted;
    private boolean storedCredentialsHexEncoded;
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        Object accountCredentials = getCredentials(info);
        
        //加密算法（密码+盐【时间戳或者随机数最佳】）迭代次数
        
        return equals(tokenHashedCredentials, accountCredentials);
        
        Object tokenCredentials = getCredentials(token);
        Object accountCredentials = getCredentials(info);
        return equals(tokenCredentials, accountCredentials);
	}
	
	 protected Object getCredentials(AuthenticationToken token) {
	        return token.get;
	    }
	 
	public String getHashAlgorithm() {
		return hashAlgorithm;
	}
	public void setHashAlgorithm(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}
	public int getHashIterations() {
		return hashIterations;
	}
	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}
	public boolean isHashSalted() {
		return hashSalted;
	}
	public void setHashSalted(boolean hashSalted) {
		this.hashSalted = hashSalted;
	}
	public boolean isStoredCredentialsHexEncoded() {
		return storedCredentialsHexEncoded;
	}
	public void setStoredCredentialsHexEncoded(boolean storedCredentialsHexEncoded) {
		this.storedCredentialsHexEncoded = storedCredentialsHexEncoded;
	}
	
}
