/**
 * Processor Unit test cases
 * @author xilei
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.github.empty125.ecdiff;

import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author xilei
 */
public class ProcessorTest{
    
        @Test
        public void diff() throws IOException{
               Job job = new Job();
               job.setSrc("src/test/resources/test_1.xlsx");
               job.setSrcColumnIdIndex(0);
               job.setSrcColumnIndex(1);
               job.setDist("src/test/resources/test_2.xlsx");
               job.setDistColumnIdIndex(0);
               job.setDistColumnIndex(1);
               //Processor  processor = new Processor(job);        
               //processor.diff();
        }

}
