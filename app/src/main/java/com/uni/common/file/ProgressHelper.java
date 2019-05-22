package com.uni.common.file;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by ljd on 4/12/16.
 */
public class ProgressHelper {

    private static FileProgress     sFileProgress = new FileProgress();
    private static ProgressHandler mProgressHandler;

    public static OkHttpClient.Builder addProgress(OkHttpClient.Builder builder){

        if (builder == null){
            builder = new OkHttpClient.Builder();
        }

        final ProgressListener progressListener = new ProgressListener() {
            //该方法在子线程中运行
            @Override
            public void onProgress(long progress, long total, boolean done) {
                Log.d("progress:",String.format("%d%% done\n",(100 * progress) / total));
                if (mProgressHandler == null){
                    return;
                }
                sFileProgress.setBytesRead(progress);
                sFileProgress.setContentLength(total);
                sFileProgress.setDone(done);
                mProgressHandler.sendMessage(sFileProgress);
            }
        };

        //添加拦截器，自定义ResponseBody，添加下载进度
        builder.networkInterceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(
                        new ProgressResponseBody(originalResponse.body(), progressListener))
                        .build();

            }
        });
        return builder;
    }

    public static void setProgressHandler(ProgressHandler progressHandler){
        mProgressHandler = progressHandler;
    }
}