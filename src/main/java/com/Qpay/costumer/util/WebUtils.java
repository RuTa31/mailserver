package com.Qpay.costumer.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class WebUtils {

    public enum WebServiceMethodEnum {
        GET,
        POST,
        PUT,
        DELETE;
    }

    public static String callPostRequest(String pUrl, String pUrlParameters) throws Exception {
        return callMethod(WebServiceMethodEnum.POST, pUrl, pUrlParameters, null);
    }

    public static String callPostRequest(String pUrl, String pUrlParameters, Map<String, String> pHeaderMap)
            throws Exception {
        return callMethod(WebServiceMethodEnum.POST, pUrl, pUrlParameters, pHeaderMap);
    }

    public static String callGetRequest(String pUrl) throws Exception {
        return callMethod(WebServiceMethodEnum.GET, pUrl, null, null);
    }

    public static String callGetRequest(String pUrl, Map<String, String> pHeaderMap) throws Exception {
        return callMethod(WebServiceMethodEnum.GET, pUrl, null, pHeaderMap);
    }

    public static String callMethod(
            WebServiceMethodEnum pMethodEnum,
            String pUrl,
            String pUrlParameters,
            Map<String, String> pHeaderMap) throws Exception {

        try {
            URL url = new URL(pUrl);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setInstanceFollowRedirects(false);
            httpUrlConnection.setConnectTimeout(3 * 10000); // 30 SECOND
            httpUrlConnection.setReadTimeout(60 * 30000); // 30 MINUT

            httpUrlConnection.setRequestMethod(pMethodEnum.name());

            if (ValidatorUtils.isNull(pHeaderMap)) {
                pHeaderMap = new HashMap<>();
            } else {

                if (!StringUtils.isNullOrEmpty(pHeaderMap.get("username"))
                        && !StringUtils.isNullOrEmpty((pHeaderMap.get("password")))) {

                    String username = pHeaderMap.get("username");
                    String password = pHeaderMap.get("password");

                    pHeaderMap.put("Authorization", getBasicAuthenticationHeader(username, password));
                }

                for (Map.Entry<String, String> entrySet : pHeaderMap.entrySet()) {
                    httpUrlConnection.setRequestProperty(entrySet.getKey(), entrySet.getValue());
                }
            }

            if (!pHeaderMap.containsKey("Content-Type") && !pHeaderMap.containsKey("content-type")) {
                httpUrlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            }

            if (!pHeaderMap.containsKey("Accept") && !pHeaderMap.containsKey("accept")) {
                httpUrlConnection.setRequestProperty("Accept", "*/*");
            }

            httpUrlConnection.setUseCaches(false);

            if (pMethodEnum != WebServiceMethodEnum.GET) {
                byte[] postData = pUrlParameters.getBytes(StandardCharsets.UTF_8);
                int postDataLength = postData.length;
                httpUrlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));

                try (DataOutputStream dataOutputStream = new DataOutputStream(
                        httpUrlConnection.getOutputStream())) {
                    dataOutputStream.write(postData);
                    dataOutputStream.flush();
                }
            }

            int responseCode = httpUrlConnection.getResponseCode();
            String Messege = httpUrlConnection.getResponseMessage();
            System.out.println("responseCode: " + responseCode);
            System.out.println("Response Message" + Messege);

            InputStreamReader inputStreamReader = null;
            try {
                if (HttpURLConnection.HTTP_BAD_REQUEST <= responseCode) {
                    inputStreamReader = new InputStreamReader(httpUrlConnection.getErrorStream(),
                            Charset.forName("UTF-8"));
                } else {
                    inputStreamReader = new InputStreamReader(httpUrlConnection.getInputStream(),
                            Charset.forName("UTF-8"));
                }
            } catch (NullPointerException ex) {

            }

            StringBuilder stringBuilder = new StringBuilder();
            if (inputStreamReader != null) {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } else {
                throw new Exception("InputStreamReader is null service response : " + responseCode);
            }
            httpUrlConnection.disconnect();

            if (responseCode < HttpURLConnection.HTTP_OK) { // not success
                String errorMessage = stringBuilder.toString();
                // UserLogUtil.writeLog("response error : " + errorMessage);
                // if (ValidatorInput.isEmpty(errorMessage)) {
                // errorMessage = "Response code : " + responseCode;
                // }
                throw new Exception(errorMessage);
            }

            return stringBuilder.toString();
        } catch (Exception ex) {
            System.out.println("core ERROR" + ex.getMessage());
            throw new Exception(ex.getMessage());

        }
    }

    public static final String getBasicAuthenticationHeader(String pUsername, String pPassword) {

        String authString = pUsername + ":" + pPassword;
        return "Basic " + java.util.Base64.getEncoder().encodeToString(authString.getBytes());
    }

}
