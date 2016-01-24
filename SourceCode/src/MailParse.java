
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nelson.dsouza
 */
public class MailParse
{
    FileWriter outputtext = null;        
    String errstr="MONM-000011: Process count (0) is NOT EQUAL to expected process count of (=";
    String estr="!";
    String errname="Results for search text";
    int count;
    
    public MailParse()
    {
        try 
        {
            
        File file = new File("C:\\tmp\\mail.txt"); 
        
        File oldoutputfile = new File("C:\\tmp\\errmail.txt");
        oldoutputfile.delete();
        File outputfile = new File("C:\\tmp\\errmail.txt");
        
        outputtext  = new FileWriter(outputfile,true);
        
        FileInputStream fis =new FileInputStream(file);
        InputStreamReader isr=new InputStreamReader(fis);
        BufferedReader br=new BufferedReader(isr);
    
        String str= "";
    
        while((str = br.readLine())!=null)
            {
            if(str.startsWith(errname) | str.equals(errstr)|str.endsWith(estr) )
                {
                //System.out.println(str);  
                outputtext.write(str + "   ");
                count+=1;
                
                if ((count%3)==0)
                outputtext.write(System.getProperty( "line.separator" ));
            
                }              
            }
        
        //System.out.println("Total number of errors are:  "+ (count/2));
        outputtext.write("Total number of errors are:" +(count/3));
        isr.close();
        fis.close();
        outputtext.close();
            
        }
        catch (Exception e){e.printStackTrace();}
    }
  }
