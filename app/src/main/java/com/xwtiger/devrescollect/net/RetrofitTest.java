package com.xwtiger.devrescollect.net;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * author:xw
 * Date:2018-07-06 17:17
 * Description:
 */
public class RetrofitTest {

    public static final String API_URL = "https://api.github.com";

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    //创建接口
    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Observable<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);

        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors1(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }


    public static void test() throws IOException {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //动态生成一个代理对象
        GitHub github = retrofit.create(GitHub.class);

        try {
            Method contributors1 = github.getClass().getMethod("contributors1", new Class[]{String.class, String.class});
            Log.d("testanno","getDeclaringClass = "+contributors1.getDeclaringClass().toString());
            Log.d("testanno","getGenericReturnType = "+contributors1.getGenericReturnType().toString());
            Log.d("testanno","getReturnType = "+contributors1.getReturnType().toString());
            Log.d("testanno","getGenericParameterTypes = "+contributors1.getGenericParameterTypes()[0].toString());
            Log.d("testanno","getGenericParameterTypes length= "+contributors1.getGenericParameterTypes().length);
            Log.d("testanno","getParameterAnnotations length= "+contributors1.getParameterAnnotations().length);
            Log.d("testanno","getParameterAnnotations "+contributors1.getParameterAnnotations()[0].toString());
            Log.d("testanno","getAnnotations length="+contributors1.getAnnotations().length);
            Log.d("testanno","getAnnotations GET="+contributors1.getAnnotation(GET.class));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }




        Log.d("RetrofitTest", "callAdapterFactories size :"+retrofit.callAdapterFactories().size());

        //生成一个OKHttpCall的代理对象
        //Observable<List<Contributor>> call = github.contributors("square", "retrofit");
        Call<List<Contributor>> call1 = github.contributors1("square", "retrofit");

        call1.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                Log.d("RetrofitTest", "test: responseBody ="+response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });

        //返回结果
//        call.subscribeOn(Schedulers.io()).subscribe(new Action1<List<Contributor>>() {
//            @Override
//            public void call(List<Contributor> responseBody) {
//                try {
//                    Log.d("RetrofitTest", "test: responseBody ="+responseBody.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                Log.d("RetrofitTest", "test:throwable)");
//            }
//        });

    }




}
