/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.ui;

/**
 *
 * @author rafi
 */
    import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.logging.Level;
    import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
public class Backup
{

    private static ResultSet res;
    private static Connection con;
    private Statement st;
    private int BUFFER = 99999;
    public Process process =null;
    public String getData(String host, String port, String user, String password, String db) {
        String Mysqlpath = getMysqlBinPath(user, password, db);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace() ;
            System.out.print("yaha dekho");
        }
        try {
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, user, password);
//            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            //System.out.print("I am here yaaar");
            e.printStackTrace();
        }


        System.out.println(Mysqlpath);
        Process run = null;
        try {
            System.out.println(Mysqlpath + "mysqldump --host=" + host + " --port=" + port + " --user=" + user + " --password=" + password + " --compact --complete-insert --extended-insert " + "--skip-comments --skip-triggers " + db);
            run = Runtime.getRuntime().exec(Mysqlpath + "mysqldump --host=" + host + " --port=" + port + " -u" + user + " -p" + password + "  " + "--add-drop-database -B -R " + db);
            
        } catch (Exception ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }


        InputStream in = run.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuffer temp = new StringBuffer();
  

        int count;
        char[] cbuf = new char[BUFFER];
        try {
            while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
                temp.append(cbuf, 0, count);
            }
        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return temp.toString();
    }

// Mysql path is required to locate the bin folder inside it because it contains the Mysqldump which performs a //main role while taking backup.
/*Function to find MySql Path*/
    public  String getMysqlBinPath(String user, String password, String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.print("yaha dekho");
        }
        StringBuffer a = new StringBuffer();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, user, password);
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        


        

        
            res = st.executeQuery("select @@basedir");
            while (res.next()) {
                a.append(res.getString(1));
            }
        } catch (Exception eee) {
            eee.printStackTrace();
        }finally {
            try {
                con.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        a = a.append("bin\\");
        System.err.println("Mysql path is :" + a);
        return a.toString();
    }
    
    public int restoreData(String host, String port, String user, String password, String db, String path) {
        int result=0;
        String Mysqlpath = getMysqlBinPath(user, password, db);
        
        Process run = null;
        ProcessBuilder builder = null;
        File batchFile=null;
        File data = null;
        try {
            if(path.endsWith(".zip")){
                data = getUnzip(path);
                System.out.println(data.getAbsolutePath());
                path = data.getAbsolutePath();
            }
                
            String restoreCmd = "mysql -u"+user+" -p"+password+" <"+path;
            System.out.println(restoreCmd);
            String[] cmd = {"cd /d "+Mysqlpath,System.lineSeparator(),restoreCmd,System.lineSeparator(),"exit 0"};
            
            batchFile = createFile(cmd);
            Path directory = Paths.get(batchFile.getAbsolutePath());
            directory.getParent();
            ProcessBuilder pb = new ProcessBuilder(batchFile.getAbsolutePath());

            pb.directory(new File(directory.getParent().toString()));
            pb.redirectErrorStream(true);
            Process p1 =  null;
            InputStream ip = null;
            String temp =null;
            BufferedReader br = null;
            StringBuffer result1 = new StringBuffer();
            try {
                p1 = pb.start();
                ip = p1.getInputStream();
                result = p1.waitFor();
                
                br = new BufferedReader(new InputStreamReader(ip));
                while((temp = br.readLine())!=null)
                {
                    result1.append(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                if(ip !=null)
                    ip.close();
                if(br !=null)
                    br.close();
                
            }
            
            System.out.println(" Restore done.."+result1.toString());
        } catch (Throwable ex) {
            ex.printStackTrace();
            result = 1;
        } finally{
            batchFile.delete();
            data.delete();
        }
        return result;
    }

    private File createFile(String... string) {
        File f = new File("restore.bat");
        try {
            FileWriter fileWriter = new FileWriter(f);
            for (int i = 0; i < string.length; i++) {
                System.out.println(string[i]);
                fileWriter.write(string[i]);
                
            }
            
            fileWriter.flush();
            fileWriter.close();
            
        } catch (IOException ex) {
//            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            
        }
        
        
        return f;
    }

    private File getUnzip(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            File newFile = null ;
       ZipInputStream zip = new ZipInputStream(fis);
       ZipEntry ze = zip.getNextEntry();
       byte[] buffer = new byte[BUFFER];
       while(ze!=null){
           String fileName = ze.getName();
           newFile = new File(fileName);
           System.out.println("Unziping file "+path);
           FileOutputStream fos = new FileOutputStream(newFile);
           int len;
           while((len = zip.read(buffer)) >0 ){
               fos.write(buffer, 0, len);
           }
           fos.close();
           zip.closeEntry();
           ze = zip.getNextEntry();
       }
       zip.closeEntry();
       zip.close();
       fis.close();
       return newFile; 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}