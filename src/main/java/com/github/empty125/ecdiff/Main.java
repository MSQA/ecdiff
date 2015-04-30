/**
 * 
 * @author  xilei
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.empty125.ecdiff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineParser;

/**
 *
 * @author xilei
 */
public class Main {
    
    public static final  Logger logger = LogManager.getLogger(Main.class);
    
    public static void main(String args[]){
        logger.trace("Hello,ecdiff");
        Job job = new Job();
        CmdLineParser parser = new CmdLineParser(job);
        try {
            parser.parseArgument(args);
            Processor processor = new Processor(job);
            processor.diff();
        } catch (Exception e) {
            parser.printUsage(System.out);
            logger.fatal(e.getMessage());
        }
    }   
  
    
}
