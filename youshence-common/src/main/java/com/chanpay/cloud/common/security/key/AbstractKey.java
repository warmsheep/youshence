package com.chanpay.cloud.common.security.key;

import com.chanpay.cloud.common.security.des.AbstractDes;
import com.chanpay.cloud.common.security.des.impl.DESede;
import com.chanpay.cloud.common.security.des.impl.Des;
import com.chanpay.cloud.common.security.exception.KeyException;
import com.chanpay.cloud.common.security.utils.ByteUtil;

public class AbstractKey {
	protected byte[] tak;
	protected byte[] tpk;

	public AbstractKey() {
	}

	public AbstractKey(byte[] tpk, byte[] tak) {
		this.tak = tak;
		this.tpk = tpk;
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

	public byte[] getTak() {
		return this.tak;
	}

	public byte[] getTpk() {
		return this.tpk;
	}

	protected AbstractDes newDesInstance(byte[] key) {
		AbstractDes des = null;
		if (key.length == 8)
			des = new Des(key);
		else if (key.length == 16)
			des = DESede.newInstance16(key);
		else if (key.length == 24) {
			des = DESede.newInstance24(key);
		}
		return des;
	}
}
