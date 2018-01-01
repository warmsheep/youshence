package com.chanpay.cloud.common.security.key;

import com.chanpay.cloud.common.security.exception.KeyException;

public abstract interface Key {

	public static final byte[] ZERO = new byte[8];
	public static final int ENCRYPT_MODE_DES = 1;
	public static final int ENCRYPT_MODE_DESEDE = 3;

	public abstract boolean initialize(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws KeyException;

	public abstract byte[] getKey(byte[] paramArrayOfByte);
}
