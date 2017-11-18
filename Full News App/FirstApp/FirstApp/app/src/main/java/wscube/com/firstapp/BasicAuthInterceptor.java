package wscube.com.firstapp;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wscube on 4/1/17.
 */

public class BasicAuthInterceptor implements Interceptor{

    String credentials;
    public BasicAuthInterceptor(String user,String pass)
    {
        this.credentials= Credentials.basic(user,pass);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request=chain.request();
        Request authenticatedRequest=request.newBuilder().header("Authorization",credentials).build();
        return chain.proceed(authenticatedRequest);
    }
}
