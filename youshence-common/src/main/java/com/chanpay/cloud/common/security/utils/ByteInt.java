package com.chanpay.cloud.common.security.utils;

public class ByteInt {
	public static int bytes2int(byte[] b) {
		int mask = 255;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < b.length; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	public static byte[] int2bytes(int byteLength, int num) {
		byte[] b = new byte[byteLength];
		int mask = 255;
		for (int i = 0; i < byteLength; i++) {
			b[i] = ((byte) (num >>> 8 * (byteLength - 1) - i * 8));
		}
		return b;
	}
}

