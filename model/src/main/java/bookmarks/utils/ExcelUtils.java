package bookmarks.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * Created by liaoxiang on 2016/10/18.
 */
public class ExcelUtils {

    public static  HSSFSheet getRows(String caption, String[] titles, HSSFWorkbook wb) {
        HSSFSheet sheet = wb.createSheet(caption);
        sheet.setDefaultColumnWidth(23);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setTopBorderColor(HSSFColor.GREY_25_PERCENT.index);
        style.setBottomBorderColor(HSSFColor.GREY_25_PERCENT.index);
        style.setLeftBorderColor(HSSFColor.GREY_25_PERCENT.index);
        style.setRightBorderColor(HSSFColor.GREY_25_PERCENT.index);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFFont font = wb.createFont();
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);// 设置字体大小
        style.setFont(font);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，


        HSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }
        return sheet;
    }
}
