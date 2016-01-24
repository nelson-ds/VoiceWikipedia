
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainClass //implements Runnable
{

    MailParse objMailParse;
    String Errtext0 = null;
    String Errtext = null;
    String Errno = "0";
    String phno="7411270191";
    VoiceTest objVoiceTest;
    int count=1;
   
    
    Calendar currentDate = Calendar.getInstance();
    SimpleDateFormat formatter=  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
    String dateNow = formatter.format(currentDate.getTime());

    @SuppressWarnings("static-access")
    public MainClass() 
        {                 
            
            objMailParse = new MailParse(); 
            
            try {
                
                FileInputStream fis=new FileInputStream("C:\\tmp\\errmail.txt");
                InputStreamReader isr=new InputStreamReader(fis);
                BufferedReader br=new BufferedReader(isr);
                
                FileInputStream fis0=new FileInputStream("C:\\tmp\\errmail.txt");
                InputStreamReader isr0=new InputStreamReader(fis0);
                BufferedReader br0=new BufferedReader(isr0);
                
                      
                while((Errtext0=br0.readLine())!=null)
                    {
                        if(Errtext0.startsWith("Total number of"))
                        {
                            Errno=Errtext0.substring(27);
                            break;
                        }
                    }
                
                if(Errno.equals("0")) 
                {
                    System.out.println("No processis down according to current mail");
                    System.exit(0);
                }
                String NewErrtext="Mail Error Status on " +dateNow+
                                  " is as follows: \nTotal number of errors are " + Errno + ":     \n";
                System.out.println(NewErrtext); 
                         
                while((Errtext=br.readLine())!=null)
                    {          
                        if(Errtext.startsWith("Results for"))
                            Errtext=("(Error in  " + Errtext.substring(27));
                        
                        if(Errtext.startsWith("Total number of"))
                            break;
                                     
                    NewErrtext+=(count + ":    " + Errtext + ";    ");
                    System.out.println(count+" : "+Errtext);
                    count+=1;
                    }               
                    
                    System.out.println("-    Sorry for waking you up.Bye. \n \n");
               
                //Thread myThread = new Thread(this);
                //myThread.start();
                
                /*try 
                    {
                    Thread.sleep(10000);
                    }
                catch (InterruptedException ex) 
                    {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                System.out.println(NewErrtext);
                //System.out.println(dateNow);
                objVoiceTest = new VoiceTest();
                objVoiceTest.SpeakNOW(NewErrtext.trim().substring(0) + "-     Sorry for waking you up.Bye.");
            
                

                }

                catch (Exception e) 
                {
                e.printStackTrace();
                } 
        }

    public static void main(String[] args) 
        {
        new MainClass();
        }

 /* public void run() 
        {
        try 
            {
            //Runtime.getRuntime().exec("VoiceWikiCall COM5 8600873804");
            Runtime.getRuntime().exec("VoiceWikiCall COM5 " + phno);
            } 
        catch (IOException ex) 
            {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/ 
}
