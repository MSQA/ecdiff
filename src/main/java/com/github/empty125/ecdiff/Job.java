/**
 * A job parameters
 * @author xilei
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.github.empty125.ecdiff;

/**
 *
 * @author xilei
 */
public class Job {
    
    // src filename
    public String src = "";
    //dist filename
    public String dist = "";
    
    // id column
    public  int  srcColumnIdIndex = 0;
    
    // id column
    public int  distColumnIdIndex = 0;
    
    // the column which need comparing
    public int srcColumnIndex = 0;
    // the column which need comparing
    public int distColumnIndex = 0;
    
      
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

}
