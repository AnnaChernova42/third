package su.jet.webautotests.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static su.jet.webautotests.utils.TestProperties.prop;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Класс для разбора xls-файла с тестовыми данными
 * Created by vv.drobot on 18.03.2016.
 */
public class ExcelReader {

    private String fileName;
    private HSSFSheet sheet;
    private int transactionColumnNumber;
    private int rowNumber;

    public ExcelReader() {
        try {
            this.fileName = prop.getProperty("xlsDataFile");
            HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(new File(fileName)));
            int sheetNumber = Integer.parseInt(prop.getProperty("sheetNumber"));
            this.sheet = workBook.getSheetAt(sheetNumber - 1);
            this.transactionColumnNumber = Integer.parseInt(prop.getProperty("transactionColumnNumber"));
            this.rowNumber = Integer.parseInt(prop.getProperty("startStringNumber"));
        } catch (IOException e) {
            log.error("Can't read xls test-data file!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @return Метод возвращает список ID транзакций из xls-файла.
     */
    @Step("Получить список всех проверяемых транзакций из xls-файла")
    public ArrayList<String> getTransactionList(){
        log.info("------------------------------------------------------------------");
        log.info("Читаем список проверяемых транзакций из файла " + fileName + "...");
        ArrayList<String> list = new ArrayList<>();

        boolean b = true;
        int countOfReadedRows = 0;
        do{
            try{
                HSSFRow row = sheet.getRow(rowNumber - 1);
                HSSFCell cell = row.getCell(transactionColumnNumber - 1);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String idString = cell.getStringCellValue().trim();
                if(idString.isEmpty()) throw new Exception();
                list.add(idString);
                ++countOfReadedRows;
            }catch (Exception e){
                log.warn("В строке №" + rowNumber + " ID транзакции не распознан - " + e.getMessage());
                log.info("Прочитано всего " + countOfReadedRows + " транзакций из файла " + fileName + ".");
                b = false;
            }
            ++rowNumber;
        }while(b);

        return list;
    }

    /**
     * @param transactionID - ID транзакции
     * @return Метод возвращает карту значений полей транзакции, где ключ - кодовое обозначение поля данных.
     */
    @Step("Получить карты всех полей данных из xls-файла")
    public Map<String, String> getTransactionFields(String transactionID){
        log.info("------------------------------------------------------------------");
        log.info("Составляем карту значений полей данных транзакции с ID " + transactionID + " из файла " + fileName + "...");
        Map<String, String> map = new HashMap<>();
        if(transactionID.contains(transactionID)){
            int rowNumber = getRowIndex(transactionID);
            HSSFRow row = sheet.getRow(rowNumber);
            HSSFRow rowWithFieldIDs = sheet.getRow(0);
            int column = transactionColumnNumber;
            int countOfReadedFields = 0;
            int rowSize = rowWithFieldIDs.getLastCellNum();

            do{
                HSSFCell cell;
                try{

                    cell = rowWithFieldIDs.getCell(column);
                    if(cell != null){
                        //получаем id поля
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String id = cell.getStringCellValue().trim();
                        if(id.isEmpty()) {
                            log.error("В колонке №" + column + " кодовое обозначение поля не распознано.");
                            throw new Exception();
                        }

                        //получаем значение поля
                        cell = row.getCell(column);
                        String value;
                        try{
                            if(id.contains("DATA") || id.contains("DATE")){
                                Date date = cell.getDateCellValue();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                                value = sdf.format(date);
                            }else {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                value = cell.getStringCellValue().trim();}
                        }catch (Exception e){
                            value = "0";
                        }

                        if(id.contains("ACC_B")){
                            value = value.replaceAll(" ", "");
                        }else if(id.contains("BIK_S")){
                            if(value.equals("")){
                                value = "0";
                            }else
                                while(value.length() < 9) value = "0" + value;
                        }

                        if(value.equals("") || value.toLowerCase().equals("(null)")) {value = "0";}

                        map.put(id, value);
                        ++countOfReadedFields;
                    }
                }catch (Exception e){
                    log.info("Прочитано всего " + (countOfReadedFields - 1) + " значений полей данных для транзакции с ID = " + transactionID + ".");
                }
                ++column;
            }while(column < rowSize);
        }else {
            log.error("Транзакция с ID = '" + transactionID + "' не найдена.");
            return null;
        }
        return map;
    }

    /**
     * @param transactionNumber - номер строки c транзакцией в xls-файле
     * @return Метод возвращает карту значений полей транзакции, где ключ - кодовое обозначение поля данных.
     */
    @Step("Получить карты всех полей данных из xls-файла")
    public Map<String, String> getTransactionFields(int transactionNumber){
        log.info("------------------------------------------------------------------");
        Map<String, String> map = new HashMap<>();

        int rowNumber = transactionNumber + Integer.parseInt(prop.getProperty("startStringNumber")) - 1;
        HSSFRow row = sheet.getRow(rowNumber);
        HSSFCell cellTr = row.getCell(0);
        cellTr.setCellType(Cell.CELL_TYPE_STRING);
        String transactionID = cellTr.getStringCellValue();
        log.info("Составляем карту значений полей данных транзакции с ID " + transactionID + " из файла " + fileName + "...");

        HSSFRow rowWithFieldIDs = sheet.getRow(0);
        int column = transactionColumnNumber;
        int countOfReadedFields = 0;
        int rowSize = rowWithFieldIDs.getLastCellNum();

        do{
            HSSFCell cell;
            try{

                cell = rowWithFieldIDs.getCell(column);
                if(cell != null){
                    //получаем id поля
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String id = cell.getStringCellValue().trim();
                    if(id.isEmpty()) {
                        log.error("В колонке №" + column + " кодовое обозначение поля не распознано.");
                        throw new Exception();
                    }

                    //получаем значение поля
                    cell = row.getCell(column);
                    String value;
                    try{
                        if(id.contains("DATA") || id.contains("DATE")){
                            Date date = cell.getDateCellValue();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            value = sdf.format(date);
                        }else {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            value = cell.getStringCellValue().trim();}
                    }catch (Exception e){
                        value = "0";
                    }

                    if(id.contains("ACC_B")){
                        value = value.replaceAll(" ", "");
                    }else if(id.contains("BIK_S")){
                        if(value.equals("")){
                            value = "0";
                        }else
                            while(value.length() < 9) value = "0" + value;
                    }

                    if(value.equals("") || value.toLowerCase().equals("(null)")) {value = "0";}

                    map.put(id, value);
                    ++countOfReadedFields;
                }
            }catch (Exception e){
                log.info("Прочитано всего " + (countOfReadedFields - 1) + " значений полей данных для транзакции с ID = " + transactionID + ".");
            }
            ++column;
        }while(column < rowSize);

        return map;
    }

    private int getRowIndex (String transactionID){
        List<String> list = new ArrayList<>();
        int transactionColumnNumber = Integer.parseInt(prop.getProperty("transactionColumnNumber"));
        int rowNumber = Integer.parseInt(prop.getProperty("startStringNumber"));

        boolean b = true;
        do{
            try{
                HSSFRow row = sheet.getRow(rowNumber - 1);
                HSSFCell cell = row.getCell(transactionColumnNumber - 1);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String idString = cell.getStringCellValue().trim();
                if(idString.isEmpty()) throw new Exception();
                list.add(idString);
            }catch (Exception e){
                b = false;
            }
            ++rowNumber;
        }while(b);
        return Integer.parseInt(prop.getProperty("startStringNumber")) + list.indexOf(transactionID) - 1;
    }

    //public static void main(String[] args){
    //    ExcelReader reader = new ExcelReader();
    //    List<String> list = reader.getTransactionList();
    //    Map<String, String> map = reader.getTransactionFields("2084");
    //    System.out.println("End.");
    //}
}
