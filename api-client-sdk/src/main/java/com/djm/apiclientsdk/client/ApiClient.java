package com.djm.apiclientsdk.client;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.djm.apiclientsdk.utils.SignUtils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 * @author dongjinmin
 * @date 2023/7/8
 * @Description
 */
public class ApiClient {
    private static String GATEWAY_HOST = "http://localhost:8090";
    private String accessKey;
    private String secretKey;

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void setGatewayHost(String gatewayHost) {
        GATEWAY_HOST = gatewayHost;
    }

    private Map<String, String> getHeaderMap(String body, String method) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("accessKey", accessKey);
        headerMap.put("nonce", RandomUtil.randomNumbers(4));
        headerMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        headerMap.put("sign", SignUtils.getSign(body, secretKey));
//        防止中文乱码
        body = URLUtil.encode(body, CharsetUtil.CHARSET_UTF_8);
        headerMap.put("body", body);
        headerMap.put("method", method);
        return headerMap;
    }

    public String invokeInterface(String params, String url, String method) throws UnsupportedEncodingException {
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + url)
                .header("Accept-Charset", CharsetUtil.UTF_8)
                .addHeaders(getHeaderMap(params, method))
                .body(params)
                .execute();
        String body = httpResponse.body();
        String result = JSONUtil.formatJsonStr(body);
        return result;
    }

}
