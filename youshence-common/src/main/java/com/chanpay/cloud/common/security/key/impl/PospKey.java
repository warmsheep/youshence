package com.chanpay.cloud.common.security.key.impl;

import com.chanpay.cloud.common.security.des.AbstractDes;
import com.chanpay.cloud.common.security.exception.KeyException;
import com.chanpay.cloud.common.security.key.AbstractKey;
import com.chanpay.cloud.common.security.key.Key;
import com.chanpay.cloud.common.security.utils.ByteUtil;

public class PospKey extends AbstractKey {
	public PospKey() {
	}

	public PospKey(byte[] tpk, byte[] tak) {
		super(tpk, tak);
	}

	public byte[] getKey(byte[] _tmk) {
		AbstractDes des = newDesInstance(_tmk);
		byte[] tak2 = des.encrypt(this.tak);
		byte[] tpk2 = des.encrypt(this.tpk);
		byte[] tpkcv = newDesInstance(this.tpk).encrypt(Key.ZERO);
		byte[] takcv = newDesInstance(this.tak).encrypt(Key.ZERO);
		byte[] data = new byte[40];
		System.arraycopy(tpk2, 0, data, 0, 16);
		System.arraycopy(tpkcv, 0, data, 16, 4);
		System.arraycopy(tak2, 0, data, 20, 8);
		System.arraycopy(takcv, 0, data, 36, 4);
		return data;
	}

	public boolean initialize(byte[] src, byte[] tmk) throws KeyException {
		try {
			if (src == null)
				throw new KeyException("数据为空,src = " + src);
			if (tmk == null)
				throw new KeyException("终端主密钥为空,tmk = " + tmk);
			if (src.length != 40)
				throw new KeyException("数据的长度有误[" + src.length + "],期望长度[40]");
			if (tmk.length != 16)
				throw new KeyException("密钥的长度有误[" + tmk.length + "],期望长度[16]");
			this.tpk = new byte[16];
			byte[] tpkcv = new byte[4];
			this.tak = new byte[8];
			byte[] takcv = new byte[4];
			System.arraycopy(src, 0, this.tpk, 0, 16);
			System.arraycopy(src, 16, tpkcv, 0, 4);
			System.arraycopy(src, 20, this.tak, 0, 8);
			System.arraycopy(src, 36, takcv, 0, 4);
			AbstractDes des = newDesInstance(tmk);
			this.tpk = des.decrypt(this.tpk);
			this.tak = des.decrypt(this.tak);
			check(tpkcv, takcv);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new KeyException("初始化密钥失败");
	}

	protected void check(byte[] tpkcv, byte[] takcv) throws KeyException {
		try {
			AbstractDes des = newDesInstance(this.tpk);
			String tpkcv1 = ByteUtil.bytesToHexString(des.encrypt(Key.ZERO)).substring(0, 8);
			String tpkcv2 = ByteUtil.bytesToHexString(tpkcv);
			if (!tpkcv1.equals(tpkcv2))
				throw new KeyException("TPK验证失败[" + tpkcv1 + "][" + tpkcv2 + "]");
			des = newDesInstance(this.tak);
			String takcv1 = ByteUtil.bytesToHexString(des.encrypt(Key.ZERO)).substring(0, 8);
			String takcv2 = ByteUtil.bytesToHexString(takcv);
			if (!takcv1.equals(takcv2))
				throw new KeyException("TAK验证失败[" + takcv1 + "][" + takcv2 + "]");
		} catch (Exception e) {
			throw new KeyException("TAK(TPK)验证出错," + e.getMessage());
		}

	}
}
