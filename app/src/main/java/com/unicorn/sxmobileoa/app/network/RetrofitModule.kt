package com.unicorn.sxmobileoa.app.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.unicorn.sxmobileoa.BuildConfig
import com.unicorn.sxmobileoa.app.config.ConfigModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun providerLoggingInterceptor(): LoggingInterceptor = LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("Request")
            .response("Response")
            .addHeader("version", BuildConfig.VERSION_NAME)
            .addQueryParam("query", "0")
            //              .enableAndroidStudio_v3_LogsHack(true) /* enable fix for logCat logging issues with pretty format */
            //              .logger(new Logger() {
            //                  @Override
            //                  public void log(int level, String key, String msg) {
            //                      Log.w(key, msg);
            //                  }
            //              })
            //              .executor(Executors.newSingleThreadExecutor())
            .build()


    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: LoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(loggingInterceptor)
            // TODO 可能需要配置更多参数
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

    @Suppress("DEPRECATION")
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(ConfigModule.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providerGson(): Gson = Gson()

}
