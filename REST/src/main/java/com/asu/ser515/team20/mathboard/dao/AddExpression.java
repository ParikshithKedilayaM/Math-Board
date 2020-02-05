import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author Kunal Sharma
 */

public class AddExpression {

    /**
     *
     * @param args
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static void main(String[] args) throws IOException, InvalidFormatException {

        String[] userDetails = {"Mahesh", "a+b(x-y-1+4)-2-3-4-5-6"};
        SaveExpression(userDetails);
    }


    /**
     * Method To SaveExpression in database
     * @param details
     * @return
     */
    public static int SaveExpression(String details[])
    {
        try{
            
            String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";  // file path
            FileInputStream inputStream = new FileInputStream(SAMPLE_XLSX_FILE_PATH);
            int numberOfcolumns = details.length;
        // Obtain a workbook from the excel file
        Workbook workbook = WorkbookFactory.create(inputStream);        // creating object for workbook
        Sheet sheet = workbook.getSheet("Expression");          //  creating sheet name- Expression
        if(sheet !=null)
        {
            
            int rowCount = sheet.getLastRowNum();
           Row row = sheet.createRow(rowCount+1);
           for(int i =0; i<numberOfcolumns;i++)
           {
               Cell cell = row.createCell(i);
               cell.setCellValue(details[i]);       // setting cell values 
               sheet.autoSizeColumn(i);             // autosizing for better readability
           }
           inputStream.close();
           FileOutputStream fileOut = new FileOutputStream(SAMPLE_XLSX_FILE_PATH);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
        return 1;
        }
            
        }
        catch(Exception ex)
        {
           System.out.println(ex);
        }
        return 0;
    }
}
