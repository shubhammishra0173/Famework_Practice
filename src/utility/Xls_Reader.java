package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;


public class Xls_Reader {
	
	public String File_Name;
	public Xls_Reader(String File_Name)
	{
		this.File_Name=File_Name;
	}

    public void Create_Excel(String Sheet_Name,int Row_Num,int Col_Num) throws IOException{
        HSSFWorkbook wb = new HSSFWorkbook();
       // HSSFSheet sheet=wb.createSheet(Sheet_Name);
       // HSSFRow row = sheet.createRow(0);
        //HSSFCell cell = row.createCell(0);
        
        File f = new File(File_Name);
        if(!f.exists()) 
        { 
        FileOutputStream fileOut = new FileOutputStream(File_Name);
        
        try {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        fileOut.close();
        }
    	
    	FileInputStream fileIn = new FileInputStream(File_Name);
        HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
        //HSSFSheet sheet=workbook.getSheetAt(0);
        int totalsheets= workbook.getNumberOfSheets();
       //HSSFSheet sheet
       // HSSFRow row = sheet.createRow(0);
        HSSFSheet sheet;
        boolean flag=true;
        for(int i=0;i<totalsheets; i++)
        {
        	sheet=workbook.getSheetAt(i);
            if(sheet.getSheetName().equals(Sheet_Name))
            {
               sheet=workbook.getSheet(Sheet_Name); 
               flag=false;
            }
           
            //File_Name.close();
            
        }
        
       if(flag)
        {
       	sheet=workbook.createSheet(Sheet_Name);
       	HSSFRow row = sheet.createRow(0);
       	 for(int k=1;k<=Row_Num;k++)
       	 {
            for(int j=1;j<=Col_Num;j++)
            {
                row.createCell(j-1);
            }
            row=sheet.createRow(k);
        }
        }
        try {
        	FileOutputStream fileout=new FileOutputStream(File_Name);
        	workbook.write(fileout);
        } catch (IOException e) {
            e.printStackTrace();
        }
   	
   	
       //HSSFWorkbook wb = new HSSFWorkbook();
      // HSSFSheet sheet=wb.createSheet(Sheet_Name);
       //HSSFRow row = sheet.createRow(0);
       //HSSFCell cell = row.createCell(0);
       //FileOutputStream fileOut = new FileOutputStream(File_Name);
       
       
       
    }
    public void Write_Excel(int Sheet_Num,int Row_Num,int Col_Num,String Cell_Text) throws IOException{
            
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir")+"\\src\\Results\\"+File_Name+".xls");
            HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
            HSSFSheet sheet=workbook.getSheetAt(Sheet_Num-1);
            HSSFRow row=sheet.getRow(Row_Num);
            HSSFCell cell=sheet.getRow(Row_Num).getCell(Col_Num);
            if (row==null || cell==null){
                if (row==null)
                row=sheet.createRow(Row_Num);
                
                cell=row.createCell(Col_Num);
                cell.setCellValue(Cell_Text);
            }    
            else{
                cell=sheet.getRow(Row_Num).getCell(Col_Num);
                cell.setCellValue(Cell_Text);
            }
            fileIn.close();
            try {
                FileOutputStream fileOut=new FileOutputStream(System.getProperty("user.dir")+"\\"+File_Name+".xlsx");
                workbook.write(fileOut);
                fileOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }    

}
    public int getrowcount(String Sheet_Name) throws IOException{
        
        FileInputStream fileIn = new FileInputStream(File_Name);
        HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
        //HSSFSheet sheet=workbook.getSheetAt(0);
        int totalsheets= workbook.getNumberOfSheets();
        
        int row=0;
        for(int i=0;i<totalsheets; i++){
            HSSFSheet sheet=workbook.getSheetAt(i);
            if(sheet.getSheetName().equals(Sheet_Name)){
                row=sheet.getLastRowNum()+1;
                //System.out.println("from getrowcount last row num in "+Sheet_Name+" sheet is "+sheet.getLastRowNum());
                break;
            }
            
        }
        /*sheet.getSheetName()
        workbook.g*/
        
        fileIn.close();
        return row;
        }
    public String getCellData(String Sheet_Name, int Row_Num, int Col_Num) throws IOException{
        
        FileInputStream fileIn = new FileInputStream(File_Name);
        HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
        
        int totalsheets= workbook.getNumberOfSheets();
        String celldata=null;
        for(int i=0; i<totalsheets; i++){
            HSSFSheet sheet=workbook.getSheetAt(i);
        if(sheet.getSheetName().equals(Sheet_Name)){
        HSSFRow row = sheet.getRow(Row_Num-1);
        HSSFCell cell= row.getCell(Col_Num-1);
        if(cell!=null){
        celldata= cell.getStringCellValue();
        break;
        }
        else
        {
            celldata="";
            break;
        }
        }
        }
        
        fileIn.close();
        return celldata;
        
        }
    public int getColumncount(String Sheet_Name) throws IOException{
        
        FileInputStream fileIn = new FileInputStream(File_Name);
        HSSFWorkbook workbook = new HSSFWorkbook(fileIn);     
        int columncount=0;     
        int totalsheets= workbook.getNumberOfSheets();
        for(int i=0; i<totalsheets; i++){
            
        HSSFSheet sheet=workbook.getSheetAt(i);
        if(sheet.getSheetName().equals(Sheet_Name)){
        HSSFRow row = sheet.getRow(0);
        columncount = row.getLastCellNum();
        break;
        }
        }
        fileIn.close();
        return columncount;
        
        }
    public int getCellRowNumber(int Sheet_Num, String Cell_Text) throws IOException{
    
        FileInputStream fileIn = new FileInputStream(File_Name);
        HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
        HSSFSheet sheet=workbook.getSheetAt(Sheet_Num-1);
        HSSFRow row = sheet.getRow(0);
        int cellnum=0;
        int totalrow= sheet.getLastRowNum();
        int totalcolumn = row.getLastCellNum();
        for(int i =0; i<=totalrow-1; i++){
            for(int j=0; j<=totalcolumn-1;j++){
                HSSFRow row1=sheet.getRow(i);
                HSSFCell cell = row1.getCell(j);
                String celldata=cell.getStringCellValue();
                if(celldata.equals(Cell_Text)){
                    cellnum=i+1;
                    break;
                }
            }
        }
        fileIn.close();
        return cellnum;
        
        }
    public String getCellData(String Sheet_Name, int Row_Num, String Col_Name) throws IOException{
        
        FileInputStream fileIn = new FileInputStream(File_Name);
        HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
        
        int totalsheets= workbook.getNumberOfSheets();
        String celldata=null;
        int Col_Num=0;
        for(int i=0; i<totalsheets; i++){
            HSSFSheet sheet=workbook.getSheetAt(i);
        if(sheet.getSheetName().equals(Sheet_Name)){
        HSSFRow row = sheet.getRow(Row_Num-1);
        
        //to find the col name
        HSSFRow row1 = sheet.getRow(0);
        
        for(int j=0; j<row1.getLastCellNum(); j++){
            HSSFCell cell1= row1.getCell(j);
            if(cell1.getStringCellValue().equals(Col_Name)){
                Col_Num=cell1.getColumnIndex();
            }
            
        }
        HSSFCell cell= row.getCell(Col_Num);
        if(cell==null)
        	return "";
        else if(cell.getCellType()==0){
        celldata= String.valueOf(cell.getNumericCellValue());
        break;
        }
        else if(cell.getCellType()==1){
        	celldata= cell.getStringCellValue();
            break;
        }
        else if(cell.getCellType()==2){
        	celldata= cell.getCellFormula();
            break;
        	
        }
        else if(cell.getCellType()==3){
        	celldata="";
            break;
        }
        else if(cell.getCellType()==4){
        	celldata= String.valueOf(cell.getBooleanCellValue());
            break;
        	
        }
        
        else
        {
            celldata="";
            break;
        }
        }
        }
        
        fileIn.close();
        return celldata;
        
        }
    public boolean isSheetExist(String Sheet_Name) throws IOException{
        
        FileInputStream fileIn = new FileInputStream(File_Name);
        HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
        
        int totalsheets= workbook.getNumberOfSheets();
        boolean exist=false;
        for(int i=0; i<totalsheets; i++){
            HSSFSheet sheet=workbook.getSheetAt(i);
           // System.out.println(Sheet_Name);
        if(sheet.getSheetName().equals(Sheet_Name)){
           // System.out.println(Sheet_Name+" is present");
            exist=true;
            break;
        }
        else
        {
            //System.out.println(Sheet_Name+" is not present at "+File_Name);
            exist=false;
        }
        }
        
        
        fileIn.close();
        return exist;
        
        }
    
    
    public boolean setCellData(String sheetName,String colName,int rowNum, String data){
        try{
            FileInputStream fis = new FileInputStream(File_Name);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);

        if(rowNum<=0)
            return false;
       
        int index = workbook.getSheetIndex(sheetName);
        int colNum=-1;
        if(index==-1)
            return false;
       
       
        HSSFSheet  sheet = workbook.getSheetAt(index);
       

      HSSFRow  row=sheet.getRow(0);
        for(int i=0;i<row.getLastCellNum();i++){
            //System.out.println(row.getCell(i).getStringCellValue().trim());
            if(row.getCell(i).getStringCellValue().trim().equals(colName))
                colNum=i;
        }
        if(colNum==-1)
            return false;

        sheet.autoSizeColumn(colNum);
        row = sheet.getRow(rowNum-1);
        if (row == null)
            row = sheet.createRow(rowNum-1);
       
      HSSFCell  cell = row.getCell(colNum);   
        if (cell == null)
            cell = row.createCell(colNum);

        // cell style
        //CellStyle cs = workbook.createCellStyle();
        //cs.setWrapText(true);
        //cell.setCellStyle(cs);
        cell.setCellValue(data);

        FileOutputStream  fileOut = new FileOutputStream(File_Name);

        workbook.write(fileOut);

        fileOut.close();   

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    public boolean setCellData(String sheetName,int colNum, String data){
        try{
            FileInputStream fis = new FileInputStream(File_Name);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
		    int index = workbook.getSheetIndex(sheetName);
		    HSSFSheet  sheet = workbook.getSheetAt(index);
		    HSSFRow  row=sheet.getRow(0);
		    HSSFCell  cell = row.getCell(colNum);   
		    if (cell == null)
            cell = row.createCell(colNum);

	        cell.setCellValue(data);
	        FileOutputStream  fileOut = new FileOutputStream(File_Name);
	        workbook.write(fileOut);
	        fileOut.close();   

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean addColumn(String sheetName,String colName){
        //System.out.println("**************addColumn*********************");
       
        try{               
            FileInputStream fis = new FileInputStream(File_Name);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if(index==-1)
                return false;
           
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
       // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       
       HSSFSheet sheet=workbook.getSheetAt(index);
       HSSFCell cell ;
       
      HSSFRow  row = sheet.getRow(0);
        if (row == null)
            row = sheet.createRow(0);
       
        //cell = row.getCell();   
        //if (cell == null)
        //System.out.println(row.getLastCellNum());
        if(row.getLastCellNum() == -1)
            cell = row.createCell(0);
        else
            cell = row.createCell(row.getLastCellNum());
           
            cell.setCellValue(colName);
            cell.setCellStyle(style);
           
            FileOutputStream   fileOut = new FileOutputStream(File_Name);
            workbook.write(fileOut);
            fileOut.close();           

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
       
        return true;
       
       
    }
    
    public void AttachScreenshot( String sheetname, String image, int row, int col)
    {
    	try {

			   Workbook wb = new HSSFWorkbook();
			   Sheet sheet = wb.createSheet("Anand");
			   

			   //FileInputStream obtains input bytes from the image file
			   InputStream inputStream = new FileInputStream("D:\\Tech Bodhi\\Selenium\\src\\Screenshots\\Link10.png");
			   //Get the contents of an InputStream as a byte[].
			   byte[] bytes = IOUtils.toByteArray(inputStream);
			   //Adds a picture to the workbook
			   int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			   //close the input stream
			   inputStream.close();

			   //Returns an object that handles instantiating concrete classes
			   CreationHelper helper = wb.getCreationHelper();

			   //Creates the top-level drawing patriarch.
			   Drawing drawing = sheet.createDrawingPatriarch();

			   //Create an anchor that is attached to the worksheet
			   ClientAnchor anchor = helper.createClientAnchor();
			   //set top-left corner for the image
			   anchor.setCol1(1);
			   anchor.setRow1(2);

			   //Creates a picture
			   Picture pict = drawing.createPicture(anchor, pictureIdx);
			   //Reset the image to the original size
			   //pict.resize();
			   pict.resize(0.5, 1);

			   //Write the Excel file
			   FileOutputStream fileOut = null;
			   fileOut = new FileOutputStream("D:\\Tech Bodhi\\Selenium\\Anand2.xls");
			   wb.write(fileOut);
			   fileOut.close();

			  }
			  catch (Exception e) {
			   System.out.println(e);
			  }

    }
    
    
}
