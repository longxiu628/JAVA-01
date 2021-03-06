package com.java.study;

import okhttp3.*;

import java.io.IOException;

public class OkHttpRequest {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    public String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public void asynchronousGet(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {//异步请求
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println("【" + responseHeaders.name(i) + "】" + responseHeaders.value(i));
                }
                System.out.println("【响应结果】" + response.body().string());
            }
        });
    }


    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void main(String[] args) {
        OkHttpRequest okHttpRequest = new OkHttpRequest();
        //生成accessToken的接口URL
        String accessUrl = "http://localhost:8088/";
        try {
            //发送请求
            String resBody = okHttpRequest.get(accessUrl);
            System.out.println(resBody);

            okHttpRequest.asynchronousGet(accessUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
