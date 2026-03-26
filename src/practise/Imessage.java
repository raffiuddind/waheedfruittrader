/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practise;




import java.util.logging.Level;
import java.util.logging.Logger;
import kong.unirest.Headers;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;



/**
 *
 * @author Raffi
 */
public class Imessage {
    
    private static String myNo = "919642916198";
    private static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCIgOiAiMWIyMjRiYzAtZTVhNC00ZTM5LTgzMTItMjc3ZDJhMWYyN2IwIiwgInJvbGUiIDogImFwaSIsICJ0eXBlIiA6ICJhcGkiLCAibmFtZSIgOiAiUmFmZmkiLCAiZXhwIiA6IDIwNDY5MzQyODkuMDk4NzQ3LCAiaWF0IiA6IDE3MzE0MDE0ODkuMDk4NzQ3LCAic3ViIiA6ICIxMDAwMTQ4Yy0xYTE2LTQ2MjYtOTZhMC02ODU5ODhhMWIwZmIiLCAiaXNzIiA6ICJwZXJpc2tvcGUuYXBwIn0.I_s7JE318akT-aY037mkKBoWqECxAh9p7CwlibF1sG4";
    
   static void sendMessage(String myNo, String message, String toNo){
       try {
           String df = WhAPIMessageSender.convertToBase64("C:\\Users\\Raffi\\Desktop\\1.pdf");
           HttpResponse<String> response = Unirest.post("https://api.periskope.app/v1/message/send")
     .header("x-phone", myNo)
     .header("Authorization", "Bearer "+token)
     .header("Content-Type", "application/json")
     .body("{\n  \"message\": \""+df+"\",\n  \"chat_id\": \""+toNo+"@c.us\"\n}")
  
     .asString();
           System.out.println("response "+response.getBody());
       } catch (Exception ex) {
           Logger.getLogger(Imessage.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }
   
    static void sendMediaMessage(String myNo, String message, String toNo){
       try {
           HttpResponse<String> response = Unirest.post("https://api.periskope.app/v1/message/send")
     .header("x-phone", myNo)
     .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCIgOiAiMWIyMjRiYzAtZTVhNC00ZTM5LTgzMTItMjc3ZDJhMWYyN2IwIiwgInJvbGUiIDogImFwaSIsICJ0eXBlIiA6ICJhcGkiLCAibmFtZSIgOiAiUmFmZmkiLCAiZXhwIiA6IDIwNDY5MzQyODkuMDk4NzQ3LCAiaWF0IiA6IDE3MzE0MDE0ODkuMDk4NzQ3LCAic3ViIiA6ICIxMDAwMTQ4Yy0xYTE2LTQ2MjYtOTZhMC02ODU5ODhhMWIwZmIiLCAiaXNzIiA6ICJwZXJpc2tvcGUuYXBwIn0.I_s7JE318akT-aY037mkKBoWqECxAh9p7CwlibF1sG4")
     .header("Content-Type", "application/json")
    // .body("{\n  \"message\": \""+message+"\",\n  \"chat_id\": \""+toNo+"@c.us\"\n}")
     .body("{\n  \"media\": {\n    \"type\": \"document\",\n    \"url\": \"C:/Users/Raffi/Desktop/State Bank of India.pdf\"\n  },\n  \"chat_id\": \""+toNo+"@c.us\"\n}")
     .asString();
           System.out.println("response "+response.getBody());
       } catch (Exception ex) {
           Logger.getLogger(Imessage.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }
   
    public static void main(String[] arg){
//        sendMessage("919642916198","Hello, Raffiuddin!","917358046489");
//        sendMediaMessage(myNo,"Hello, Raffiuddin!","917358046489");
        getMessageQueue();
    }
    
    
    public static void getMessageQueue(){
        HttpResponse<String> response = Unirest.get("https://api.periskope.app/v1/queue/message")
  .header("x-phone", myNo)
  .header("Authorization", "Bearer "+token)
  .asString();
        System.out.println("response: "+response.getBody());
        Headers obj = response.getHeaders();
        System.out.println(obj.all().get(0).getName());
    }
}
