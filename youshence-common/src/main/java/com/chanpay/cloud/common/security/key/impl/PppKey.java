package com.chanpay.cloud.common.security.key.impl;

import com.chanpay.cloud.common.security.des.AbstractDes;
import com.chanpay.cloud.common.security.exception.KeyException;
import com.chanpay.cloud.common.security.key.AbstractKey;
import com.chanpay.cloud.common.security.key.Key;
import com.chanpay.cloud.common.security.utils.ByteUtil;

public class PppKey extends AbstractKey {
	public PppKey() {
	}

	public PppKey(byte[] tpk, byte[] tak) {
		super(tpk, tak);
	}

	public byte[] getKey(byte[] _tmk, int length) throws KeyException {
		AbstractDes des = newDesInstance(_tmk);
		byte[] tak2 = des.encrypt(this.tak);
		byte[] tpk2 = des.encrypt(this.tpk);
		byte[] tpkcv = newDesInstance(this.tpk).encrypt(Key.ZERO);
		byte[] takcv = newDesInstance(this.tak).encrypt(Key.ZERO);
		byte[] data = new byte[length];
		if (length == 24) {
			System.arraycopy(tpk2, 0, data, 0, 8);
			System.arraycopy(tpkcv, 0, data, 8, 4);
			System.arraycopy(tak2, 0, data, 12, 8);
			System.arraycopy(takcv, 0, data, 20, 4);
		} else if (length == 40) {
			System.arraycopy(tpk2, 0, data, 0, 16);
			System.arraycopy(tpkcv, 0, data, 16, 4);
			System.arraycopy(tak2, 0, data, 20, 16);
			System.arraycopy(takcv, 0, data, 36, 4);
		} else {
			throw new KeyException("数据的长度有误[" + length + "],期望长度[24/40]");
		}
		return data;
	}

	public boolean initialize(byte[] src, byte[] tmk) throws KeyException {
		try {
			if (src == null)
				throw new KeyException("数据为空,src = " + src);
			if (tmk == null)
				throw new KeyException("终端主密钥为空,tmk = " + tmk);
			if (tmk.length == 8) {
				if (src.length == 24) {
					this.tpk = new byte[8];
					this.tak = new byte[8];
				} else {
					throw new KeyException("数据的长度有误[" + src.length + "],期望长度[24]");
				}
			} else if (tmk.length == 16) {
				if (src.length == 40) {
					this.tpk = new byte[16];
					this.tak = new byte[16];
				} else {
					throw new KeyException("数据的长度有误[" + src.length + "],期望长度[40]");
				}
			} else
				throw new KeyException("密钥的长度有误[" + tmk.length + "],期望长度[16或者8]");

			byte[] tpkcv = new byte[4];

			byte[] takcv = new byte[4];

			System.arraycopy(src, 0, this.tpk, 0, this.tpk.length);
			System.arraycopy(src, this.tpk.length, tpkcv, 0, tpkcv.length);
			System.arraycopy(src, this.tpk.length + tpkcv.length, this.tak, 0, this.tak.length);
			System.arraycopy(src, src.length - takcv.length, takcv, 0, takcv.length);
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

	public static void main(String[] args) {
		PppKey ek = new PppKey();
		try {
			System.out.println(ek.initialize(ByteUtil.hexStringToByte("8248924AE8A3D68B579F7E54E6D02D415A1E445D7D4243AFC9C30E5C00000000000000004B642F29"),
					ByteUtil.hexStringToByte("0123456789abcdef0123456789abcdef")));
			System.out.println(ByteUtil.bytesToHexString(ek.getTpk()));
			System.out.println(ByteUtil.bytesToHexString(ek.getTak()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
