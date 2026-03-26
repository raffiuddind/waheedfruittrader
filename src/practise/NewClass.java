/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practise;

import java.util.Scanner;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.eclipse.persistence.config.CacheUsageIndirectionPolicy;

/**
 *
 * @author raffiuddin
 */
public class NewClass {
    public static void main(String[] args) throws Exception {
     
OkHttpClient client = new OkHttpClient();
RequestBody body = new FormBody.Builder() 
			.add("token", "4nn0xv2gmhvqrf5r")
			.add("to", "+919652050028")
			.add("filename", "hello.pdf")
			.add("document", "https://file-example.s3-accelerate.amazonaws.com/documents/cv.pdf")
			.add("caption", "document caption")


            .build();

Request request = new Request.Builder()
  .url("https://api.ultramsg.com/instance75113/messages/document")
  .post(body)
  .addHeader("content-type", "application/x-www-form-urlencoded")
  .build();

Response response = client.newCall(request).execute();
 
 System.out.println(response.body().string());
    }
    
}
