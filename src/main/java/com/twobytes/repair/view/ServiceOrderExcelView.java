package com.twobytes.repair.view;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.BorderLineStyle;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.twobytes.repair.form.ServiceOrderDocForm;

public class ServiceOrderExcelView extends AbstractJExcelView {
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			WritableWorkbook wb, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		Workbook wwb = getTemplateSource("serviceOrder.xls", request);
//		Workbook wwb = getTemplateSource("serviceOrderDocExcel", request);
//		WritableSheet sheet = wb.createSheet("Spring", 0);
//        sheet.addCell(new Label(0, 0, "Spring-Excel jexcel"));
//        List words = (List) model.get("wordList");
//        for (int i = 0; i < words.size(); i++) {
//            sheet.addCell(new Label(2 + i, 0, (String) words.get(i)));
//        }

		WritableFont normalFont = new WritableFont(WritableFont.ARIAL, 10);
		
		WritableCellFormat normal = new WritableCellFormat(normalFont);
		normal.setVerticalAlignment(VerticalAlignment.CENTRE);
		
		WritableCellFormat border_top_bottom = new WritableCellFormat();
		border_top_bottom.setBorder(Border.TOP, BorderLineStyle.THIN);
		border_top_bottom.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		border_top_bottom.setVerticalAlignment(VerticalAlignment.CENTRE);
		
		WritableCellFormat border_bottom_dotted = new WritableCellFormat();
		border_bottom_dotted.setBorder(Border.BOTTOM, BorderLineStyle.DOTTED);
		
		WritableCellFormat wrap = new WritableCellFormat();
		wrap.setVerticalAlignment(VerticalAlignment.TOP);
		wrap.setWrap(true);
		
		ServiceOrderDocForm form = (ServiceOrderDocForm) model.get("form");
        WritableSheet sheet1 = wb.getSheet(0);
        sheet1.addCell(new Label(20, 3, form.getServiceOrderID(), normal));
        sheet1.addCell(new Label(15, 5, form.getServiceOrderDate(), border_top_bottom));
        sheet1.addCell(new Label(15, 6, form.getServiceOrderTime(), border_top_bottom));
        sheet1.addCell(new Label(15, 7, form.getAppointmentDate(), border_top_bottom));
        
        if(form.getServiceType() == 1){
        	sheet1.addImage(new WritableImage(1, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_check.png"))));
        }else{
        	sheet1.addImage(new WritableImage(1, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice.png"))));
        }
        if(form.getServiceType() == 2){
        	sheet1.addImage(new WritableImage(6, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_check.png"))));
        }else{
        	sheet1.addImage(new WritableImage(6, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice.png"))));
        }
        if(form.getServiceType() == 3){
        	sheet1.addImage(new WritableImage(9, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_check.png"))));
        }else{
        	sheet1.addImage(new WritableImage(9, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice.png"))));
        }
        if(form.getServiceType() == 4){
        	sheet1.addImage(new WritableImage(13, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_check.png"))));
        }else{
        	sheet1.addImage(new WritableImage(13, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice.png"))));
        }
        if(form.getServiceType() == 5){
        	sheet1.addImage(new WritableImage(18, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_check.png"))));
        }else{
        	sheet1.addImage(new WritableImage(18, 27, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice.png"))));
        }
        
        
        if(form.getServiceType() == 1){
        	if(form.getGuaranteeNo() == 1){
        		sheet1.addImage(new WritableImage(1, 29, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small_check.png"))));
        	}else{
        		sheet1.addImage(new WritableImage(1, 29, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));	
        	}
        	
        	if(form.getGuaranteeNo() == 2){
        		sheet1.addImage(new WritableImage(2, 29, .82, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small_check.png"))));	
        	}else{
        		sheet1.addImage(new WritableImage(2, 29, .82, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));	
        	}
        	
        	if(form.getGuaranteeNo() == 3){
        		sheet1.addImage(new WritableImage(3, 29, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small_check.png"))));	
        	}else{
        		sheet1.addImage(new WritableImage(3, 29, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));	
        	}
        	
        	if(form.getGuaranteeNo() == 4){
        		sheet1.addImage(new WritableImage(4, 29, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small_check.png"))));
        	}else{
        		sheet1.addImage(new WritableImage(4, 29, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
        	}
        	
        	if(form.getGuaranteeNo() == 5){
        		sheet1.addImage(new WritableImage(1, 30, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small_checked.png"))));
        	}else{
        		sheet1.addImage(new WritableImage(1, 30, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
        	}
        	
        	if(form.getGuaranteeNo() == 6){
        		sheet1.addImage(new WritableImage(2, 30, .82, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small_check.png"))));
        	}else{
        		sheet1.addImage(new WritableImage(2, 30, .82, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
        	}
        	
        	if(form.getGuaranteeNo() == 7){
        		sheet1.addImage(new WritableImage(3, 30, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small_check.png"))));
        	}else{
        		sheet1.addImage(new WritableImage(3, 30, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
        	}
        }else{
	        sheet1.addImage(new WritableImage(1, 29, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
	        sheet1.addImage(new WritableImage(2, 29, .82, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
	        sheet1.addImage(new WritableImage(3, 29, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
	        sheet1.addImage(new WritableImage(4, 29, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
	        sheet1.addImage(new WritableImage(1, 30, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
	        sheet1.addImage(new WritableImage(2, 30, .82, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
	        sheet1.addImage(new WritableImage(3, 30, 1, 1, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_small.png"))));
        }
        
        if(form.getServiceType() == 1 || form.getServiceType() == 5){
        	sheet1.addImage(new WritableImage(11, 48, 1.90, 2, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_free_check.png"))));
        }else{
        	sheet1.addImage(new WritableImage(11, 48, 1.90, 2, new File(this.getWebApplicationContext().getServletContext().getRealPath("images/choice_free.png"))));
        }
        
        sheet1.addCell(new Label(3, 11, form.getCustomerID(), border_bottom_dotted));
        sheet1.addCell(new Label(2, 12, form.getName(), border_bottom_dotted));
        sheet1.addCell(new Label(10, 12, form.getEmail(), border_bottom_dotted));
        sheet1.addCell(new Label(16, 12, form.getTel(), border_bottom_dotted));
        sheet1.addCell(new Label(20, 12, form.getMobileTel(), border_bottom_dotted));
        
        sheet1.addCell(new Label(2, 13, form.getAddress(), border_bottom_dotted));
        sheet1.addCell(new Label(8, 13, form.getSubdistrict(), border_bottom_dotted));
        sheet1.addCell(new Label(13, 13, form.getDistrict(), border_bottom_dotted));
        sheet1.addCell(new Label(17, 13, form.getProvince(), border_bottom_dotted));
        sheet1.addCell(new Label(21, 13, form.getZipcode(), border_bottom_dotted));
        
        sheet1.addCell(new Label(4, 14, form.getDeliveryCustomer(), border_bottom_dotted));
        sheet1.addCell(new Label(10, 14, form.getDeliveryEmail(), border_bottom_dotted));
        sheet1.addCell(new Label(16, 14, form.getDeliveryTel(), border_bottom_dotted));
        sheet1.addCell(new Label(20, 14, form.getDeliveryMobileTel(), border_bottom_dotted));
        
        sheet1.addCell(new Label(3, 18, form.getType(), border_bottom_dotted));
        sheet1.addCell(new Label(2, 19, form.getBrand(), border_bottom_dotted));
        sheet1.addCell(new Label(2, 20, form.getModel(), border_bottom_dotted));
        sheet1.addCell(new Label(4, 21, form.getAccessories(), border_bottom_dotted));
        sheet1.addCell(new Label(4, 22, form.getDesc(), border_bottom_dotted));
        
        sheet1.addCell(new Label(15, 18, form.getSerialNo(), border_bottom_dotted));
        sheet1.addCell(new Label(15, 19, form.getWarrantyDate(), border_bottom_dotted));
        sheet1.addCell(new Label(15, 20, form.getWarrantyExpire(), border_bottom_dotted));
        
        sheet1.mergeCells(2, 32, 9, 39);
        sheet1.addCell(new Label(2, 32, form.getProblem(), wrap));
	}

}
