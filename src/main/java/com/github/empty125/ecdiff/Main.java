/**
 * 
 * @author  xilei
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.empty125.ecdiff;

/**
 *
 * @author xilei
 */
public class Main { 
    
    public static void main(String args[]){
        Job job = new Job();
        job.setSrc("/home/xilei/downloads/test_src.xls");
        job.setSrcColumnIdIndex(0);
        job.setSrcColumnIndex(2);
        job.setDist("/home/xilei/downloads/test_dist.xls");
        job.setDistColumnIdIndex(0);
        job.setDistColumnIndex(2);
       HSSF  processer = new HSSF(job);        
       processer.diff();
    }   
  
    
}
