/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practise;

import Fruit.Model.MessageStatus;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.swing.JOptionPane;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Raffiuddin
 */
public class WhAPIMessageSender implements Runnable{
    private static String Id =  config.Config.getInstancce().getProperty("msgID") ;//"nuYnWqDyuJ7DrLV5h3yN3LKjVIuwXn29";
    private static String To = "919550961471";
    private static final String FILEPATH = "WFC.pdf";
    
    private static Logger logger = LogManager.getLogger(WhAPIMessageSender.class);
    private  String filePath;
    private  String toMobile;
    private  String customerName;
    private  boolean isTextMsg;
    private String textData = null;
    private boolean isValidCheck;

    public WhAPIMessageSender() {
    }

    public WhAPIMessageSender(String mobileNo){
        if(null!=mobileNo && mobileNo.length()==10){
            this.toMobile = "91"+mobileNo;
        } else
            this.toMobile = mobileNo;
//        this.isValidCheck = isValidCheck;
        
    }
    public WhAPIMessageSender(boolean isTextMsg, String textData, String toMobile, String customerName) {
        this.customerName = customerName;
        this.textData = textData;
        if(null!=toMobile && toMobile.length()==10){
            this.toMobile = "91"+toMobile;
        } else 
            this.toMobile = toMobile;
        this.isTextMsg  = isTextMsg;
        
    }
    
    
    public WhAPIMessageSender(String filePath, String toMobile, String customerName, boolean isText) {
        this.customerName = customerName;
        if(isText)
            this.textData= filePath;
        else
            this.filePath = filePath;
        if(null!=toMobile && toMobile.length()==10){
            this.toMobile = "91"+toMobile;
        } else
            this.toMobile = toMobile;
        this.isTextMsg  = isText;
        
    }
    
    
    public static void main(String q[]) throws IOException{
        
        
//        sendDocument(FILEPATH);
        WhAPIMessageSender wapims  = new WhAPIMessageSender();
        wapims.toMobile = "919642916198";
        wapims.textData="Hi world";
////        final String checkValid = wapims.checkValid(wapims.toMobile);
////        checkValid("9642916198");
//        System.out.println("Is "+wapims.toMobile+" is valid : "+checkValid);
        if("valid".equalsIgnoreCase(wapims.checkValid())){
            //To = "919642916198";
            wapims.sendTextMsg();
        } else {
            System.out.println("in valid No "+wapims.toMobile);
        }
    }

    public boolean sendTextMsg() {
        
        Response response = null;
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            System.out.println("msg before send "+toMobile+" msg: "+textData);
            RequestBody body = RequestBody.create(mediaType, "{\"typing_time\":0,\"to\":\""+toMobile+"@s.whatsapp.net\",\"body\":\""+textData+"\",\"view_once\":true}");
                Request request = new Request.Builder()
                .url("https://gate.whapi.cloud/messages/text")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+Id)
                .build();
        
            response = client.newCall(request).execute();
            System.out.println("triggered sent..waiting for response...!");
            if(response.isSuccessful() && response.body() != null){

                System.out.print("Msg sent "+response.body().string());
                response.close();
                return true;
            }else {
                return false;
            }
            
        
        
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception while sending msg ",e);
        } finally{
            if(response!=null)
                response.close();
        }
        return false;
        
        
        
        
    }

    public String checkValid(){
        Response response = null;
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"blocking\":\"no_wait\",\"force_check\":false,\"contacts\":[\""+toMobile+"@s.whatsapp.net\"]}");
            Request request = new Request.Builder()
              .url("https://gate.whapi.cloud/contacts")
              .post(body)
              .addHeader("accept", "application/json")
              .addHeader("content-type", "application/json")
              .addHeader("authorization", "Bearer "+Id)
              .build();

            response = client.newCall(request).execute();
            System.out.println("check no "+toMobile);
            if (response.isSuccessful() && response.body() != null) {
                final String responseStr = response.body().string(); // This line is modified
                System.out.println("Response: " + responseStr);
                logger.info("Response to check valid mobile "+ responseStr);
                final JSONObject obj = new JSONObject(responseStr);
                final JSONArray geodata = obj.getJSONArray("contacts");
                final int n = geodata.length();
                for (int i = 0; i < n; ++i) {
                  final JSONObject person = geodata.getJSONObject(i);

                  System.out.println("str "+person.getString("status"));
                  return person.getString("status");
                }
            } else {
                System.out.println("Response not successful or has no body to "+toMobile );
                logger.info("Response not successful or has no body");
                return null;
            }
            
            
        } catch (Exception e) {
            logger.warn(e);
            e.printStackTrace();
        } finally{
            if(null != response)
                response.close();
        }
        return null;
        
    }
    
    public static String checkValid(String mobileNo){
        Response response = null;
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"blocking\":\"no_wait\",\"force_check\":false,\"contacts\":[\""+mobileNo+"@s.whatsapp.net\"]}");
            Request request = new Request.Builder()
              .url("https://gate.whapi.cloud/contacts")
              .post(body)
              .addHeader("accept", "application/json")
              .addHeader("content-type", "application/json")
              .addHeader("authorization", "Bearer "+Id)
              .build();

            response = client.newCall(request).execute();
            System.out.println("check no "+mobileNo);
            if (response.isSuccessful() && response.body() != null) {
                final String responseStr = response.body().string(); // This line is modified
                System.out.println("Response: " + responseStr);
                logger.info("Response to check valid mobile "+ responseStr);
                final JSONObject obj = new JSONObject(responseStr);
                final JSONArray responseData = obj.getJSONArray("contacts");
                final int n = responseData.length();
                for (int i = 0; i < n; ++i) {
                  final JSONObject person = responseData.getJSONObject(i);

                  System.out.println("str "+person.getString("status"));
                  return person.getString("status");
                }
            } else {
                System.out.println("Response not successful or has no body to "+mobileNo );
                logger.info("Response not successful or has no body");
                return null;
            }
            
            
        } catch (Exception e) {
            logger.warn(e);
            e.printStackTrace();
        } finally{
            if(null != response)
                response.close();
        }
        return null;
        
    }
    public void sendDocument() {
       Response response = null;
        try {
            OkHttpClient client = new OkHttpClient();
            String encodedString = convertToBase64(filePath);
            
            System.out.println("File encoding completed...");

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"to\":\""+toMobile+"@s.whatsapp.net\",\"media\":\"data:application/pdf;name="+customerName+";base64,"+encodedString+"\"}");
            Request request = new Request.Builder()
              .url("https://gate.whapi.cloud/messages/document")
              .post(body)
              .addHeader("accept", "application/json")
              .addHeader("content-type", "application/json")
              .addHeader("authorization", "Bearer "+Id)
              .build();

            response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("res: "+ response);
                String responseBodyString = response.body().string();
                System.out.println(responseBodyString);
                parseResponse(responseBodyString);
//                JOptionPane.showConfirmDialog(null, "");
            } else {
                System.out.println("Response not successful or has no body");
            }
            logger.info("Response "+response);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            if(response!=null)
                response.close();
        }
        
        
    }

    public static String convertToBase64(String filePath) throws IOException {
        File f = new File(filePath);
        byte[] fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(f);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        f.delete();
        return encodedString;
    }

    @Override
    public void run() {
        try {
            if(!"true".equalsIgnoreCase(config.Config.getInstancce().getProperty("isMsgEnabled"))){
                JOptionPane.showMessageDialog(null, "Messages are not enabled");
                return;
            }
           
            if(isTextMsg){

                sendTextMsg();
                
            } else{
                sendDocument();
            }
         } catch (Exception ex) {
             logger.warn(ex);
         }
    }

    private void parseResponse(String responseBodyString) {
        final JSONObject obj = new JSONObject(responseBodyString);
        JSONObject geodata = obj.getJSONObject("message");
        
//    final JSONArray responseData = obj.getJSONArray("message");
    final int n = geodata.length();
    String messageId = geodata.getString("id");
           
           MessageStatus messageStatus = new MessageStatus();
           
           messageStatus.setId(messageId);
           messageStatus.setMessageType(geodata.getString("type"));
           messageStatus.setFromNo(geodata.getString("from"));
           messageStatus.setToNo(geodata.getString("chat_id"));
           messageStatus.setStatus(obj.get("sent").toString());
        System.out.println(messageStatus);
        logger.info(messageStatus);
        
//    for (int i = 0; i < n; ++i) {
//      final JSONObject person = responseData.getJSONObject(i);
//      System.out.println(person.getInt("id"));
//      System.out.println(person.getString("name"));
//      System.out.println(person.getString("gender"));
//      System.out.println(person.getDouble("latitude"));
//      System.out.println(person.getDouble("longitude"));
//    }
  
    }
    
}
