package com.chanpay.cloud.common.security.key.impl;

import com.chanpay.cloud.common.security.des.impl.Des;
import com.chanpay.cloud.common.security.exception.KeyException;
import com.chanpay.cloud.common.security.key.AbstractKey;
import com.chanpay.cloud.common.security.key.Key;
import com.chanpay.cloud.common.security.utils.ByteUtil;

public class EcardKey extends AbstractKey {
	public EcardKey() {
	}

	public EcardKey(byte[] tpk, byte[] tak) {
		super(tpk, tak);
	}

	public byte[] getKey(byte[] _tmk) {
		Des des = new Des(_tmk);
		byte[] tpk2 = des.encrypt(this.tpk);
		byte[] tak2 = des.encrypt(this.tak);
		byte[] tpkcv = new Des(this.tpk).encrypt(Key.ZERO);
		byte[] takcv = new Des(this.tak).encrypt(Key.ZERO);
		byte[] result = new byte[24];
		System.arraycopy(tpk2, 0, result, 0, 8);
		System.arraycopy(tpkcv, 0, result, 8, 4);
		System.arraycopy(tak2, 0, result, 12, 8);
		System.arraycopy(takcv, 0, result, 20, 4);
		return result;
	}

	public boolean initialize(byte[] src, byte[] tmk) throws KeyException {
		try {
			if (src == null)
				throw new KeyException("数据为空,src = " + src);
			if (tmk == null)
				throw new KeyException("终端主密钥为空,tmk = " + tmk);
			if (src.length != 24)
				throw new KeyException("数据的长度有误[" + src.length + "],期望长度[24]");
			if (tmk.length != 8)
				throw new KeyException("密钥的长度有误[" + tmk.length + "],期望长度[8]");
			this.tpk = new byte[8];
			byte[] tpkcv = new byte[4];
			this.tak = new byte[8];
			byte[] takcv = new byte[4];
			System.arraycopy(src, 0, this.tpk, 0, 8);
			System.arraycopy(src, 8, tpkcv, 0, 4);
			System.arraycopy(src, 12, this.tak, 0, 8);
			System.arraycopy(src, 20, takcv, 0, 4);
			Des des = new Des(tmk);
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
			Des des = new Des(this.tpk);
			String tpkcv1 = ByteUtil.bytesToHexString(des.encrypt(Key.ZERO)).substring(0, 8);
			String tpkcv2 = ByteUtil.bytesToHexString(tpkcv);
			if (!tpkcv1.equals(tpkcv2))
				throw new KeyException("TPK验证失败");
			des = new Des(this.tak);
			String takcv1 = ByteUtil.bytesToHexString(des.encrypt(Key.ZERO)).substring(0, 8);
			String takcv2 = ByteUtil.bytesToHexString(takcv);
			if (!takcv1.equals(takcv2))
				throw new KeyException("TAK验证失败");
		} catch (Exception e) {
			throw new KeyException("TAK(TPK)验证出错," + e.getMessage());
		}

	}
}
