/**
 * Comparing different columns of Excel 2003 between two files
 * @author  xilei
 * @license  http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.empty125.ecdiff;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Processor { 
    
    public static final Logger logger = LogManager.getLogger(Processor.class);
     
    protected Job job = null;
    
    protected Font font = null;
    
    public Processor(Job job){
        this.job = job;
    }
    
    public  void diff(){      
        try {
            logger.info("start ,src:"+job.getSrc()+",dist:"+job.getDist());
            Store store =  this.getStoreFromSrc();
            Workbook wb = readExcelFileByext(job.getDist()); 
            this.font = wb.createFont();
            this.font.setColor((short)0xa);
            int rows_len=0,i=0,max_cells_len = 0;
            Sheet sheet = wb.getSheetAt(job.getDistSheet());            
            rows_len = sheet.getPhysicalNumberOfRows();            
            logger.info("Dist,open "+sheet.getSheetName()+" with "+rows_len+" rows");
            for(i=0;i<rows_len;i++){
                   Row row = sheet.getRow(i);
                   String idKey = "";
                   max_cells_len = row.getPhysicalNumberOfCells();
                   if(!job.checkDistIndex(max_cells_len)){
                           logger.warn("Dist,The length of columns is too small at row "+i+",length:"+max_cells_len);
                           continue;
                   }
                   idKey = getStringCellValue(row.getCell(job.getDistColumnIdIndex()));
                   cellComparer(store.get(idKey),row.getCell(job.getDistColumnIndex()));       
            }
            try (FileOutputStream out = new FileOutputStream(job.getOutFileName())) {
                wb.write(out);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    
   public Store getStoreFromSrc() throws IOException{
        Workbook wb = readExcelFileByext(job.getSrc()); 
        Store store = new Store();
        Row row = null;
        int max_cells_len = 0;
        int rows_len = 0;
        Sheet sheet = wb.getSheetAt(job.getSrcSheet());            
        rows_len = sheet.getPhysicalNumberOfRows();            
        logger.info("Src,open "+sheet.getSheetName()+" with "+rows_len+" rows");
        for(int i=0;i<rows_len;i++){
                    row = sheet.getRow(i);
                    max_cells_len = row.getPhysicalNumberOfCells();
                    if(!job.checkSrcIndex(max_cells_len)){
                            logger.warn("Src,The length of columns is too small at row "+i+",length:"+max_cells_len);
                            continue;
                    }
                    store.put(
                            getStringCellValue(row.getCell(job.getSrcColumnIdIndex())),
                            row.getCell(job.getSrcColumnIndex())
                    );
        }
        return store;
   }
   
   private Workbook readExcelFileByext(String filename) throws IOException{
            String ext = getExt(filename);
            Workbook wb = null;
            switch(ext){
                case "xls":
                        wb = new HSSFWorkbook(new FileInputStream(filename));
                        break;
                case "xlsx":
                        wb = new XSSFWorkbook(new FileInputStream(filename));
                        break;
                 default:
                     throw new IOException("Does not recognize the extension:"+ext);
            }
           return  wb;
   }
   
   private static String getExt(String filename){
         int dot = filename.lastIndexOf('.');
         return (dot == -1) ? "" : filename.substring(dot+1);
   }
    
   public  boolean cellComparer(Cell src_cell,Cell dist_cell){
            if(dist_cell == null){
                logger.warn("comparer,dist_cell is null");
                return false;
            }
            if(src_cell == null || !getStringCellValue(dist_cell).trim()
                    .equals(getStringCellValue(src_cell))){
                    CellStyle style = dist_cell.getCellStyle();
                    style.setFont(this.font);
                   //dist_cell.setCellStyle(style);
            }
            return true;
    }
   
   
   public  String getStringCellValue(Cell cell){
            String value = "";
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_BLANK:
                        value =  "";
                        break;
                case Cell.CELL_TYPE_BOOLEAN:
                        value = String.valueOf(cell.getBooleanCellValue());
                        break;
                case Cell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        cell.getRichStringCellValue();
                        break;
                case Cell.CELL_TYPE_NUMERIC:
                        value = String.valueOf(cell.getNumericCellValue());
                        break;
                case Cell.CELL_TYPE_ERROR:
                        value = "";
                        break;
            }
            return value.trim().replace(" ", "");
   }
   
    
}
