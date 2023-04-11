package com.mkheatapi.mkheartapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 签名工具
 *
 * @author Administrator
 * @date 2023/02/18
 */
public class SignUtils {

    public static String genSign(String body,String secretKey) {

        Digester md5 = new Digester(DigestAlgorithm.MD5);

        String content = body + "." + secretKey;

        return md5.digestHex(content);

    }

}
