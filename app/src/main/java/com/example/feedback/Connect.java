package com.example.feedback;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;

/**
 * Created by trmehta on 1/29/2015.
 */
public class Connect {
    private static final String API_URL = "https://10.57.73.200:12886/v1/identity/devices/3168/meta_data/push_notification";
    private static final String X_PAYPAL_SECURITY_CONTEXT = "{\"actor\":{\"account_number\":\"1180359614087514009\", \"auth_claims\":[\"CLIENT_ID\",\"CLIENT_SECRET\"]},\"scopes\":[\"https://api.paypal.com/v1/identity/devices\"],\"subjects\":[{\"subject\":{\"account_number\":\"1180359614087514009\",\"auth_claims\":[\"CLIENT_ID\",\"CLIENT_SECRET\"],\"auth_state\":\"REMEMBERED\"}}]}";
    //private static final String  PAYPAL_BODY = "{\\"pushDeviceTokenUri\\": \\"ThisIsJustSamplePushDeviceToken_Chnaged_Once\"}";

    private final static HttpClient httpClient = getNewHttpClient();
    //static HttpClient httpClient = new DefaultHttpClient();

    public static HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
            return new DefaultHttpClient(ccm, params);

        } catch (Exception e) {
            return new DefaultHttpClient();
        }

    }

    public static JSONObject fundingOptionsRequest() {
        String postPath = API_URL;
        HttpPost httpPost = new HttpPost(postPath);
        Log.d("PATH", postPath);
        HttpResponse httpResponse;
        JSONObject response = null;
        try {
            DataResponse dres = new DataResponse();
            dres.setPushDeviceTokenUri("sample");
            Gson gson = new Gson();
            String x= gson.toJson(dres);
            httpPost.setEntity(new StringEntity(x));
            //Log.d("BODY",PAYPAL_BODY);
            httpPost.setHeader("X-PAYPAL-SECURITY-CONTEXT", X_PAYPAL_SECURITY_CONTEXT);
            Log.d("SECURITY_CONTEXT", X_PAYPAL_SECURITY_CONTEXT);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            try {
                //Log.d("Try:","try");
                httpResponse = httpClient.execute(httpPost);
                Log.d("Try:","try");
                Log.d("Try:", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {

            try {
                response = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        } else {
            try {
                Log.e("FAILED RESPONSE", EntityUtils.toString(httpResponse.getEntity()));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return response;
    }
}
