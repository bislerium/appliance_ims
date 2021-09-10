/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.appliance;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author bisha
 */
public class ApplianceIO {

    private final String DELIMITER;
    private HashMap<Integer, ApplianceBean> appliancesData;
    private String[] data;

    public ApplianceIO() {
        this.DELIMITER = "<>";
    }   

    private String convertToSingleLine(String s) {
        return s.replaceAll("\n", "{}");
    }

    private String convertToMultiLine(String s) {
        return s.replace("{}", "\n");
    }

    public HashMap<Integer, ApplianceBean> openFile(File textFile) throws Exception {
        appliancesData = new HashMap<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String dataLine = br.readLine();
            while (dataLine != null) {
                data = new String[17];
                int startDelimiter = 0;
                int dataIndex = 0;
                while (true) {
                    int endDelimiter = dataLine.indexOf(DELIMITER, startDelimiter + 1);
                    data[dataIndex] = dataLine.substring(startDelimiter + 2, endDelimiter);
                    dataIndex++;
                    startDelimiter = endDelimiter;
                    if (dataIndex == data.length) {
                        break;
                    }
                }
                addData();
                dataLine = br.readLine();
            }
        } catch (Exception e) {
            throw new Exception("Exception Occured while Reading File " + textFile.getName() + "\n" + e.getMessage());
        }
        return appliancesData;
    }

    public void saveFile(HashMap<Integer, ApplianceBean> appliancesData, File f) throws Exception {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (ApplianceBean rowData : appliancesData.values()) {
                String[] columnData = getAppliancesColumnData(rowData);
                bw.write(DELIMITER + columnData[0] + DELIMITER + columnData[1]
                        + DELIMITER + columnData[2] + DELIMITER + columnData[3] 
                        + DELIMITER + columnData[4] + DELIMITER + columnData[5] 
                        + DELIMITER + columnData[6] + DELIMITER + columnData[7] 
                        + DELIMITER + columnData[8] + DELIMITER + columnData[9] 
                        + DELIMITER + columnData[10] + DELIMITER + columnData[11]
                        + DELIMITER + columnData[12] + DELIMITER + columnData[13]
                        + DELIMITER + columnData[14] + DELIMITER + columnData[15] 
                        + DELIMITER+ columnData[16] + DELIMITER);
                bw.newLine();
            }
        } catch (Exception e) {
            throw new Exception("Exception occured while saving " + f.getName() + "\n" + e.getMessage());
        }
    }

    public HashMap<Integer, ApplianceBean> importExcelFile(File excelFile) throws Exception {
        appliancesData = new HashMap<>();
        FileInputStream excelFIS;
        XSSFWorkbook excelImport;
        try {
            excelFIS = new FileInputStream(excelFile);
            excelImport = new XSSFWorkbook(excelFIS);
            XSSFSheet excelSheet = excelImport.getSheetAt(0);
            for (int row = 0; row <= excelSheet.getLastRowNum(); row++) {
                data = new String[17];
                Row excelRow = excelSheet.getRow(row);
                for (int column = 0; column < excelRow.getLastCellNum(); column++) {
                    data[column] = excelRow.getCell(column).toString();
                }
                addData();
            }
        } catch (IOException ex) {
            throw new Exception("Exception Occured while Reading File " + excelFile.getName() + "\n" + ex.getMessage());
        }
        return appliancesData;
    }

    public void exportExcelFile(HashMap<Integer, ApplianceBean> appliancesData, File f) throws Exception {
        XSSFWorkbook excelexport = new XSSFWorkbook();
        XSSFSheet excelSheet = excelexport.createSheet("Appliances Data");
        XSSFRow row;
        int rowCount = 0;
        for (ApplianceBean rowData : appliancesData.values()) {
            row = excelSheet.createRow(rowCount++);
            int cellCount = 0;
            for (String cellData : getAppliancesColumnData(rowData)) {
                Cell cell = row.createCell(cellCount++);
                cell.setCellValue(cellData);
            }
        }
        try ( FileOutputStream writeFile = new FileOutputStream(f)) {
            excelexport.write(writeFile);
        }
    }

    public String[] getAppliancesColumnData(ApplianceBean rowData) {
        String applianceID = String.valueOf(rowData.getApplianceID());
        String applianceName = rowData.getApplianceName();
        String category = rowData.getCategory();
        String subCategory = rowData.getSubCategory();
        String modelNumber = rowData.getModelNumber();
        String weight = rowData.getWeight();
        String brand = rowData.getBrand();
        String service = rowData.getService();
        String price = String.valueOf(rowData.getPrice());
        String stocks = String.valueOf(rowData.getStocks());
        String availability = String.valueOf(rowData.isAvailability());
        String applianceSKU = rowData.getApplianceSKU();
        String discontinued = String.valueOf(rowData.isDiscontinued());
        String regDateTime = rowData.getRegDateTime().toString();
        String discription = convertToSingleLine(rowData.getDescription());
        String comment = convertToSingleLine(rowData.getComment());
        String addedBy = String.valueOf(rowData.getAddedBy());

        return new String[] {applianceID, applianceName, category, subCategory,
            modelNumber, weight, brand, service, price, stocks, availability, applianceSKU,
            discontinued, regDateTime, discription, comment, addedBy};        
    }

    private void addData() {
        ApplianceBean applianceBean =  new ApplianceBean();
        int id = Double.valueOf(data[0]).intValue(); 
        applianceBean.setApplianceID(id);
        applianceBean.setApplianceName(data[1]);
        applianceBean.setCategory(data[2]);
        applianceBean.setSubCategory(data[3]);
        applianceBean.setModelNumber(data[4]);
        applianceBean.setWeight(data[5]);
        applianceBean.setBrand(data[6]);
        applianceBean.setService(data[7]);
        applianceBean.setPrice(Double.valueOf(data[8]));
        applianceBean.setStocks(Double.valueOf(data[9]).intValue());
        applianceBean.setAvailability(Boolean.valueOf(data[10]));
        applianceBean.setApplianceSKU(data[11]);
        applianceBean.setDiscontinued(Boolean.valueOf(data[12]));
        applianceBean.setRegDateTime(Timestamp.valueOf(data[13]));
        applianceBean.setDescription(convertToMultiLine(data[14]));
        applianceBean.setComment(convertToMultiLine(data[15]));
        applianceBean.setAddedBy(Double.valueOf(data[16]).intValue());
        appliancesData.put(id, applianceBean);
    }
}
