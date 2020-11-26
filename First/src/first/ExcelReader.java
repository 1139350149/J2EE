package first;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelReader {
    private static int LIST_SIZE = 10;

    private Workbook workbook = null;
    private ArrayList<UnderGraduate> underGraduateArrayList = null;


    public ExcelReader(String excelPath){

        underGraduateArrayList = new ArrayList<>();

        try {
            this.workbook = new HSSFWorkbook(new FileInputStream(excelPath));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean isInUnderGraduateList(String id) {	// 避免重复
        for (UnderGraduate i : underGraduateArrayList){
            if (i.isEquals(id)){
                return true;
            }
        }
        return false;
    }

    private boolean isUnderGraduateListFull() {
        return underGraduateArrayList.size() >= LIST_SIZE;
    }

    public ArrayList<UnderGraduate> search(String query){
        int sheet_number = workbook.getNumberOfSheets();
        int i = 0,j = 0;
        for (int sheet_index = 0; sheet_index < sheet_number;sheet_index++){
            Sheet sheet = workbook.getSheetAt(i);
            for (i = 1; i < sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                for (j = 0; j < row.getPhysicalNumberOfCells(); j++){
                    Cell cell = row.getCell(j);
                    String cell_info = "";
                    switch (cell.getCellType()){
                        case STRING:
                            cell_info = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            cell_info = (int)cell.getNumericCellValue() + "";
                            break;
                    }

                    if(cell_info.contains(query)) {	//查到了是这个人
                        String id = "";
                        Cell cell_id = row.getCell(0);
                        switch (cell_id.getCellType()){
                            case STRING:
                                id = cell_id.getStringCellValue();
                                break;
                            case NUMERIC:
                                id = (int)cell_id.getNumericCellValue() + "";
                                break;
                        }
                        //如果已经在UnderGraduateList里了
                        if(isInUnderGraduateList(id))
                            continue;
                        UnderGraduate manTemp = new UnderGraduate(id,
                                row.getCell(1).getStringCellValue() ,
                                (int)row.getCell(2).getNumericCellValue() + "",
                                (int)row.getCell(3).getNumericCellValue() == 0 ? "" :
                                        (int)row.getCell(3).getNumericCellValue() + "",
                                row.getCell(4).getStringCellValue());
                        if(this.isUnderGraduateListFull())
                            continue;
                        underGraduateArrayList.add(manTemp);
                    }
                }
            }
        }
        return underGraduateArrayList;
    }
}
