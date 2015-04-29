/**
 * A job parameters
 * @author xilei
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.github.empty125.ecdiff;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author xilei
 */
public class Job {
    
    // src filename
    private String src = "";
    //dist filename
    private String dist = "";
    
    // id column
    private  int  srcColumnIdIndex = 0;
    
    // id column
    private int  distColumnIdIndex = 0;
    
    // the column which need comparing
    private int srcColumnIndex = 0;
    // the column which need comparing
    private int distColumnIndex = 0;
    
    private  int srcSheet = 0;
    
    private  int distSheet = 0;  
    
    private String _outfile = "";
    
      
    public Job() {
        
    }
    
    public boolean checkDistIndex(int maxLen){
            return maxLen>= getDistColumnIdIndex()
                    && maxLen>= getDistColumnIndex();
                                     
    }
    
    public boolean checkSrcIndex(int maxLen){
            return maxLen>= getSrcColumnIdIndex()
                    && maxLen>=getSrcColumnIndex();  
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
         int dot = dist.lastIndexOf('.');
         String base = (dot == -1) ? dist : dist.substring(0, dot);
         String extension = (dot == -1) ? "" : dist.substring(dot+1);
         this._outfile =  base+"-"+(new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()))+"."+extension;
         this.dist = dist;
    }

    public int getSrcColumnIdIndex() {
        return srcColumnIdIndex;
    }

    public void setSrcColumnIdIndex(int srcColumnIdIndex) {
        this.srcColumnIdIndex = srcColumnIdIndex;
    }

    public int getDistColumnIdIndex() {
        return distColumnIdIndex;
    }

    public void setDistColumnIdIndex(int distColumnIdIndex) {
        this.distColumnIdIndex = distColumnIdIndex;
    }

    public int getSrcColumnIndex() {
        return srcColumnIndex;
    }

    public void setSrcColumnIndex(int srcColumnIndex) {
        this.srcColumnIndex = srcColumnIndex;
    }

    public int getDistColumnIndex() {
        return distColumnIndex;
    }

    public void setDistColumnIndex(int distColumnIndex) {
        this.distColumnIndex = distColumnIndex;
    }  
    
    public int getSrcSheet() {
        return srcSheet;
    }

    public void setSrcSheet(int srcSheet) {
        this.srcSheet = srcSheet;
    }

    public int getDistSheet() {
        return distSheet;
    }

    public void setDistSheet(int disSheet) {
        this.distSheet = disSheet;
    }
    
    public String getOutFileName(){
        return _outfile;
    }

}
