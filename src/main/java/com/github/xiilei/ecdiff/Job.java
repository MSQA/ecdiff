/**
 * A job parameters
 * @author xilei
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.github.xiilei.ecdiff;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.kohsuke.args4j.Option;

/**
 *
 * @author xilei
 */
public class Job {
    
    // src file    
    private File src = null;
    //dist file  
    private File dist = null;
    
    // id column    
    private  int  srcColumnIdIndex = 0;
    
    // id column
    private int  distColumnIdIndex = 0;
    
    //by same rows 
    private boolean  byrow = false;   
    
    // the column which need comparing
    private int srcColumnIndex = 1;
    
    // the column which need comparing  
    private int distColumnIndex = 1;
    
    private  int srcSheet = 0;
   
    private  int distSheet = 0;  
    
    private String _outfile = "";
    
    public Job() {
        
    }
    
    public boolean checkDistIndex(int maxLen){
            return (this.isByrow() || maxLen>= getDistColumnIdIndex()) 
                    && maxLen>= getDistColumnIndex();
                                     
    }
    
    public boolean checkSrcIndex(int maxLen){
            return(this.isByrow() || maxLen>= getSrcColumnIdIndex())
                    && maxLen>=getSrcColumnIndex();  
    }
    
    public File getSrc() {
        return src;
    }
    
    @Option(name="-a",required = true, usage ="the first excel file")
    public void setSrc(String src) throws IOException {
        this.src = checkFile(src);
    }

    public File getDist() {
        return dist;
    }
    
    @Option(name="-b",required = true,usage = "the second excel file")
    public void setDist(String dist) throws IOException {
        checkFile(dist);
         int dot = dist.lastIndexOf('.');
         String base = (dot == -1) ? dist : dist.substring(0, dot);
         String extension = (dot == -1) ? "" : dist.substring(dot+1);
         this._outfile =  base+"-"+(new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()))+"."+extension;
         this.dist = checkFile(dist);
    }

    public int getSrcColumnIdIndex() {
        return srcColumnIdIndex;
    }
    
    @Option(name="-aid",usage = "the id column of first excel,default 0")
    public void setSrcColumnIdIndex(int srcColumnIdIndex) {
        this.srcColumnIdIndex = srcColumnIdIndex;
    }

    public int getDistColumnIdIndex() {
        return distColumnIdIndex;
    }
    @Option(name="-bid",usage = "the id column of second excel ,default 0")
    public void setDistColumnIdIndex(int distColumnIdIndex) {
        this.distColumnIdIndex = distColumnIdIndex;
    }

    public int getSrcColumnIndex() {
        return srcColumnIndex;
    }
    
    @Option(name="-ai",usage = "the column of first excel ,default 1")
    public void setSrcColumnIndex(int srcColumnIndex) {
        this.srcColumnIndex = srcColumnIndex;
    }

    public int getDistColumnIndex() {
        return distColumnIndex;
    }
    
    @Option(name="-bi",usage = "the column of second excel,default 1 ")
    public void setDistColumnIndex(int distColumnIndex) {
        this.distColumnIndex = distColumnIndex;
    }  
    
    public int getSrcSheet() {
        return srcSheet;
    }

    @Option(name="-asheet",usage = "the sheet of first excel,default 0")
    public void setSrcSheet(int srcSheet) {
        this.srcSheet = srcSheet;
    }

    public int getDistSheet() {
        return distSheet;
    }
    
    @Option(name="-bsheet",usage = "the sheet of second excel,default 0")
    public void setDistSheet(int disSheet) {
        this.distSheet = disSheet;
    }
    
    public boolean isByrow() {
        return byrow;
    }
    
    @Option(name="-byrow",usage = "by same rows instead of column ")
    public void setByrow(boolean byrow) {
        this.byrow = byrow;
    }
    
    public String getOutFileName(){
        return _outfile;
    }
    
    public static File checkFile(String filename) throws IOException{
        File f = new File(filename);
        if(!f.exists()){
                throw new IOException("File "+filename+" not found");
        }
        if(!f.canRead()){
                throw new IOException("File "+filename+" not readable");
        }
        if(!filename.endsWith(".xlsx") && !filename.endsWith(".xls")){
               throw new IOException("File "+filename+" not a valid excel file");
        }
        return f;
    }
    
}
