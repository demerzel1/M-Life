package com.dhu.utils;

import com.dhu.model.MovieEntity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by demerzel on 2018/4/19.
 */
public class ExcelUtils {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ExcelUtils(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }

    /**
     * 读EXCEL文件，获取信息集合
     * @param mFile
     * @return
     */
    public List<MovieEntity> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名

        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            List<MovieEntity> userList = createExcel(mFile.getInputStream(), isExcel2003);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<MovieEntity> createExcel(InputStream is, boolean isExcel2003) {
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            List<MovieEntity> userList = readExcelValue(wb);// 读取Excel里面客户的信息
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<MovieEntity> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<MovieEntity> movieEntityList = new ArrayList<MovieEntity>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            MovieEntity movieEntity = new MovieEntity();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                    } else if (c == 15) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String str = String.valueOf(cell.getNumericCellValue());
                            movieEntity.setName(str.substring(0, str.length()-2>0?str.length()-2:1));
                        }else{
                            movieEntity.setName(cell.getStringCellValue());
                        }
                    } else if (c == 2){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String str = String.valueOf(cell.getNumericCellValue());
                            movieEntity.setDirector(str.substring(0, str.length()-2>0?str.length()-2:1));
                        }else{
                            movieEntity.setDirector(cell.getStringCellValue());
                        }
                    }else if(c==3){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String str = String.valueOf(cell.getNumericCellValue());
                            movieEntity.setMovieType(str.substring(0, str.length()-2>0?str.length()-2:1));
                        }else{
                            movieEntity.setMovieType(cell.getStringCellValue());
                        }
                    }else if(c==4){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String str = String.valueOf(cell.getNumericCellValue());
                            movieEntity.setDescription(str.substring(0, str.length()-2>0?str.length()-2:1));
                        }else{
                            movieEntity.setDescription(cell.getStringCellValue());
                        }
                    }else if(c==5){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            Double score=cell.getNumericCellValue();
                            movieEntity.setScore(score);
                        }else{
                            movieEntity.setScore(Double.valueOf(cell.getStringCellValue()) );
                        }
                    }else if(c==8){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            Double score=cell.getNumericCellValue();
                            movieEntity.setDuration(score.intValue());
                        }else{
                            movieEntity.setDuration(Double.valueOf(cell.getStringCellValue()).intValue());
                        }
                    }else if(c==12){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String str = String.valueOf(cell.getNumericCellValue());
                            movieEntity.setCast(str.substring(0, str.length()-2>0?str.length()-2:1));
                        }else{
                            movieEntity.setCast(cell.getStringCellValue());
                        }
                    }else if(c==16){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String str = String.valueOf(cell.getNumericCellValue());
                            movieEntity.setEnglishname(str.substring(0, str.length()-2>0?str.length()-2:1));
                        }else{
                            movieEntity.setEnglishname(cell.getStringCellValue());
                        }
                    }
                }
            }
            movieEntity.setPoster("/static/images/posts/post.jpg");
            Random rand = new Random();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.set(2018, 3, 1);
            long start = cal.getTimeInMillis();
            cal.set(2018, 6, 1);
            long end = cal.getTimeInMillis();
            Date d = new Date(start + (long)(rand.nextDouble() * (end - start)));
            System.out.println(format.format(d));
            String str=format.format(d);
            Date date=CommonUtils.me().String2Date(str);
            movieEntity.setBeginTime(date);
            Date endTime=date;
            for(int i=0;i<7;++i){
                endTime=CommonUtils.me().getNextDay(endTime);
            }
            movieEntity.setEndTime(endTime);
            Double t=rand.nextDouble();
            String country;
            if(t<0.333){
               country="美国";
            }else if(t>0.666){
                country="中国";
            }else{
                country="日本";
            }
            movieEntity.setCountry(country);
            // 添加到list
            movieEntityList.add(movieEntity);
        }
        return movieEntityList;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
