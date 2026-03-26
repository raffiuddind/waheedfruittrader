/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practise;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Raffiuddin
 */
public class MessageSender {

    private static String URL = "https://api.ultramsg.com/instance75113/";
    private static String TOKEN = "4nn0xv2gmhvqrf5r";
    private static String INSTANCE = "instance75113";
    private static String TO = "+919642916198";
    public MessageSender() {
    }
    
    public static void main(String a[]) throws IOException{
        
        sendMessage(TO,"");
    }
 
    public static void sendMessage(String toList, String msg) throws IOException{
//        OkHttpClient client = new OkHttpClient();
         OkHttpClient client = new OkHttpClient.Builder()
            .sslSocketFactory(createTrustAllSSLFactory(), (X509TrustManager) createTrustAllManager())
//            .hostnameVerifier((hostname, session) -> true)
            .build();

        RequestBody body = new FormBody.Builder() 
                                .add("token", TOKEN)
                                .add("to", toList)
                                .add("body", "WhatsApp API works good")
                                .build();

        Request request = new Request.Builder()
          .url("https://api.ultramsg.com/instance75113/messages/chat")
          .post(body)
          .addHeader("content-type", "application/x-www-form-urlencoded")
          .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
        

         
    }
    
    private static SSLSocketFactory createTrustAllSSLFactory() {
    try {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };

        // Install the all-trusting trust manager
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        // Create an SSL socket factory with our all-trusting manager
        return sslContext.getSocketFactory();
    } catch (Exception e) {
        throw new RuntimeException("Error creating TrustAllSSLSocketFactory", e);
    }
}

    private static TrustManager createTrustAllManager() {
        return new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

  
            public X509Certificate[] getAcceptedIssuers() {

                return new X509Certificate[]{};

            }


    };
    }
}
