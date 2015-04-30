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
    
        protected HashMap<String,Cell> cells_hash = null;
        
        
        public Store(){
             cells_hash = new HashMap<>();
        }
        
        public Store(int i){
            cells_hash = new HashMap<>(i,0.1f);
        }
        
        public void put(String key,Cell cell){
                cells_hash.put(hk(key), cell);            
        }
        
        public void put(int key,Cell cell){
                cells_hash.put(hk(key), cell);
        }
        
        public Cell get(String key){
               return cells_hash.get(hk(key));
        }
        
        public Cell get(int key){
                return cells_hash.get(hk(key));
        }
        
        public String hk(String key){
                return DigestUtils.md5Hex(key);
        }
        
        public String hk(int  key){
                return "key_"+String.valueOf(key);
        }
        
        public int size(){
                return cells_hash.size();
        }

}
