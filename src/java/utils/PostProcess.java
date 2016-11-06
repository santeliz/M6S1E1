/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Row;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author Salvador
 */
@ManagedBean
@SessionScoped
public class PostProcess implements Serializable {

    @PostConstruct
    public void init() {

    }
    
    public void preProcessPDF(Object document){
        Document pdf = (Document)document;
        pdf.setPageSize(PageSize.LETTER.rotate());        
        pdf.open();
    }
    
    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);  
        
        // Colores personalizado
        HSSFPalette palette = wb.getCustomPalette();
        palette.setColorAtIndex((byte) 41, (byte) 80, (byte) 80, (byte) 80); // Fondo encabezado
        //palette.setColorAtIndex((byte) 42, (byte) 184, (byte) 204, (byte) 228); // Fondo impar

        // Fuente encabezado 
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Arial");
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setItalic(false);       

        // Estilo de la fila de encabezado
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(palette.getColor(41).getIndex());
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);        
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        
        // Estilo celda con borde
        HSSFCellStyle cellBorderStyle = wb.createCellStyle();
        cellBorderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellBorderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellBorderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellBorderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
 
       

        // Aplicando el estilo al encabezado
        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);
            sheet.autoSizeColumn(i);
            cell.setCellStyle(cellStyle);            
        }
    }
    

}
