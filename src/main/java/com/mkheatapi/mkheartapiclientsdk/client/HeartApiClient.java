package com.mkheatapi.mkheartapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mkheatapi.mkheartapiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.mkheatapi.mkheartapiclientsdk.utils.SignUtils.genSign;


public class HeartApiClient {

    private static final String GATEWAY_HOST = "http://localhost:8090";

    private String accessKey;

    private String secretKey;

    public HeartApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }



    public String getNameByGet ( String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result3= HttpUtil.get(GATEWAY_HOST + "/api/name/getName", paramMap);
        return result3;
    }

    public String getNameByPost ( String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result3= HttpUtil.post(GATEWAY_HOST + "/api/name/PostName", paramMap);
        return result3;
    }

    private Map<String,String> getHeaderMap (String body) {

        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("accessKey",accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body",body);
        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis()));
        hashMap.put("sign", genSign(body,secretKey));
        return hashMap;



    }


    public String getNameByPostJsonName ( User user) {
        String json = JSONUtil.toJsonStr(user);
        String result2 = HttpRequest.post(GATEWAY_HOST + "/api/name/PostJsonName")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute().body();
        return result2;
    }

}
