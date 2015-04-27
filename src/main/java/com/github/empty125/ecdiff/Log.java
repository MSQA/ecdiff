/**
 * simple logs
 * @author  xilei
 * @license  http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.empty125.ecdiff;

public class Log {
    
    static public void  error(String conent,boolean  exit){
          System.err.println("Error:"+conent);
          if (exit){
                System.err.println("Exit.");
                System.exit(0);
          }
    }
    
    static public void error(String conent){
            error(conent,true);
    }
    
    static public void info(String content){
         System.out.println("Info:"+content); 
    }
    
    static public  void warn(String content){
         System.out.println("Warn:"+content); 
    }
        
    
}
