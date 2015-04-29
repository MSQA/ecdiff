/**
 * 
 * @author xilei
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.github.empty125.ecdiff;

import java.util.HashMap;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 
 * @author xilei
 */
public class Store {
    
        protected HashMap<String,Cell> cells = new HashMap<>();
        
        public void put(String key,Cell cell){
                cells.put(hk(key), cell);            
        }        
        
        public Cell get(String key){
               return cells.get(hk(key));
        }
        
        public String hk(String key){
                return DigestUtils.md5Hex(key);
        }
        
        public int size(){
                return cells.size();
        }

}
