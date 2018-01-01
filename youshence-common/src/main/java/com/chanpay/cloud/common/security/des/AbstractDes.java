package com.chanpay.cloud.common.security.des;


public abstract class AbstractDes {
	
	
	public abstract byte[] encrypt(byte[] paramArrayOfByte);

	public abstract byte[] decrypt(byte[] paramArrayOfByte);
}
