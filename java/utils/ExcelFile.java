/*
package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelFile {

        public Sheet readExcel(String filePath,String fileName,String sheetName) throws IOException{
            File file =    new File(filePath+"\\"+fileName);
            FileInputStream inputStream = new FileInputStream(file);
            Workbook book = null;
            String fileExtensionName = fileName.substring(fileName.indexOf("."));
            if(fileExtensionName.equals(".xlsx")){
                book = new XSSFWorkbook(inputStream);
            }
            else if(fileExtensionName.equals(".xls")){
                book = new HSSFWorkbook(inputStream);
            }
            //Read sheet inside the workbook by its name
            Sheet sheet = book.getSheet(sheetName);
            return sheet;
        }
    }*/
