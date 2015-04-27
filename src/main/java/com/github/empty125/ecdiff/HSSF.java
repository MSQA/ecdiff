/**
 * Comparing different columns of Excel 2003 between two files
 * @author  xilei
 * @license  http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.empty125.ecdiff;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class HSSF { 
    
    protected Job job = null;
    
    public HSSF(Job job){
        this.job = job;
    }
    
    public  void diff(){      
        try {
            Log.info("start ,src:"+job.getSrc()+",dist:"+job.getDist());
            Store store =  this.getStoreFromSrc();
            HSSFWorkbook wb = readExcelFile(job.getDist()); 
            int max_cells_len = 0;
            for (int k = 0,rows_len=0,i=0; k < wb.getNumberOfSheets(); k++) {
                 HSSFSheet sheet = wb.getSheetAt(k);            
                 rows_len = sheet.getPhysicalNumberOfRows();            
                 Log.info("Dist,open "+sheet.getSheetName()+" with "+rows_len+" rows");
                 for(i=0;i<rows_len;i++){
                        HSSFRow row = sheet.getRow(i);
                        String idKey = "";
                        max_cells_len = row.getPhysicalNumberOfCells();
                        if(!job.checkDistIndex(max_cells_len)){
                                Log.warn("Dist,The length of columns is too small at row "+i+",length:"+max_cells_len);
                                continue;
                        }
                        idKey = getStringCellValue(row.getCell(job.getSrcColumnIdIndex()));
                        cellComparer(store.get(idKey),row.getCell(job.getDistColumnIndex()));       
                 }
            }        
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    
   public Store getStoreFromSrc() throws IOException{
        HSSFWorkbook wb = readExcelFile(job.getSrc()); 
        Store store = new Store();
        HSSFRow row = null;
        int max_cells_len = 0;
        for (int k = 0,rows_len=0,i=0; k < wb.getNumberOfSheets(); k++) {
             HSSFSheet sheet = wb.getSheetAt(k);            
             rows_len = sheet.getPhysicalNumberOfRows();            
             Log.info("Src,open "+sheet.getSheetName()+" with "+rows_len+" rows");
             for(i=0;i<rows_len;i++){
                    row = sheet.getRow(i);
                    max_cells_len = row.getPhysicalNumberOfCells();
                    if(!job.checkSrcIndex(max_cells_len)){
                            Log.warn("Src,The length of columns is too small at row "+i+",length:"+max_cells_len);
                            continue;
                    }
                    store.put(
                            getStringCellValue(row.getCell(job.getSrcColumnIdIndex())),
                            row.getCell(job.getSrcColumnIndex())
                    );
             }
        }        
        return store;
   }
    /**
     * open a workbook
     * @param filename
     * @return
     * @throws IOException 
     */
   private HSSFWorkbook readExcelFile(String filename) throws IOException {
            return new HSSFWorkbook(new FileInputStream(filename));
    }
    
   public  boolean cellComparer(HSSFCell src_cell,HSSFCell dist_cell){
            if(src_cell == null){
                Log.warn("comparer,src_cell is null at row");
                return false;
            }
            if(dist_cell == null){
                Log.warn("comparer,dist_cell is null at row");
                return false;
            }
            if(!src_cell.getStringCellValue().trim()
                    .equals(getStringCellValue(src_cell))){
                   HSSFCellStyle style = dist_cell.getCellStyle();
                   style.setFillBackgroundColor(new HSSFColor.RED().getIndex());
                   dist_cell.setCellStyle(style);
            }
            return true;
    }
   
   
   public  String getStringCellValue(HSSFCell cell){
            String value = "";
            switch(cell.getCellType()){
                case HSSFCell.CELL_TYPE_BLANK:
                        value =  "";
                        break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                        value = String.valueOf(cell.getBooleanCellValue());
                        break;
                case HSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                        value = String.valueOf(cell.getNumericCellValue());
                        break;
                case HSSFCell.CELL_TYPE_ERROR:
                        value = "";
                        break;
            }
            return value.trim().replace(" ", "");
   }
   
    
}
