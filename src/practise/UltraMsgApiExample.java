/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practise;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Raffiuddin
 */
public class UltraMsgApiExample {
     private static final String INSTANCE_ID = "instance75113"; // Replace with your actual instance ID
    private static final String TOKEN = "4nn0xv2gmhvqrf5r"; // Replace with your actual token

    public static void main(String[] args) {
        sendMessage();
    }
    public static void sendMessage() {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("token", TOKEN)
                .add("to", "+919642916198") // Add the actual 'to' value
                .add("body", "WhatsApp API on UltraMsg.com works good")
                .build();

        Request request = new Request.Builder()
                .url("https://api.ultramsg.com/" + INSTANCE_ID + "/messages/chat")
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
}
