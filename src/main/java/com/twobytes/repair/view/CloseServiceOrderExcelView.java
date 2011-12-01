package com.twobytes.repair.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.twobytes.model.IssuePart;
import com.twobytes.model.ServiceList;
import com.twobytes.repair.form.ServiceOrderDocForm;

public class CloseServiceOrderExcelView extends AbstractJExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			WritableWorkbook wb, HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		WritableCellFormat border_bottom_dotted = new WritableCellFormat();
		border_bottom_dotted.setBorder(Border.BOTTOM, BorderLineStyle.DOTTED);
		
		WritableCellFormat border_bottom_dotted_left_right_thin = new WritableCellFormat();
		border_bottom_dotted_left_right_thin.setBorder(Border.BOTTOM, BorderLineStyle.DOTTED);
		border_bottom_dotted_left_right_thin.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		border_bottom_dotted_left_right_thin.setBorder(Border.LEFT, BorderLineStyle.THIN);
		
		WritableCellFormat border_bottom_double_left_right_thin_top_dotted = new WritableCellFormat();
		border_bottom_double_left_right_thin_top_dotted.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
		border_bottom_double_left_right_thin_top_dotted.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		border_bottom_double_left_right_thin_top_dotted.setBorder(Border.LEFT, BorderLineStyle.THIN);
		border_bottom_double_left_right_thin_top_dotted.setBorder(Border.TOP, BorderLineStyle.DOTTED);
		
		WritableCellFormat wrap = new WritableCellFormat();
		wrap.setVerticalAlignment(VerticalAlignment.TOP);
		wrap.setWrap(true);
		
		ServiceOrderDocForm form = (ServiceOrderDocForm) model.get("form");
		List<IssuePart> issuePartList = form.getIssuePartList();
		List<ServiceList> serviceList = form.getServiceList();
		
		WritableSheet sheet1 = wb.getSheet(0);
		
		sheet1.mergeCells(2, 41, 9, 42);
		sheet1.mergeCells(2, 43, 9, 44);
		sheet1.mergeCells(2, 45, 9, 48);
		
		sheet1.addCell(new Label(2, 41, form.getRealProblem(), wrap));
		sheet1.addCell(new Label(2, 43, form.getCause(), wrap));
		sheet1.addCell(new Label(2, 45, form.getFixDesc(), wrap));
		
		int row = 0;
		for(int i=0; i<issuePartList.size(); i++){
			if(i>0){
				row = i+1;
				sheet1.mergeCells(15, 30+row, 19, 30+row);
			}else{
				row = i;
				sheet1.mergeCells(15, 30+row, 19, 30+row+1);
				sheet1.mergeCells(20, 30+row, 20, 30+row+1);
				sheet1.mergeCells(21, 30+row, 21, 30+row+1);
			}
			
			IssuePart ip = issuePartList.get(i);
			sheet1.addCell(new Label(11, 30+row, ip.getCode()));
			sheet1.addCell(new Label(15, 30+row, ip.getName()));
			sheet1.addCell(new Number(20, 30+row, ip.getQuantity()));
			sheet1.addCell(new Number(21, 30+row, ip.getPrice()));
		}
		
		for(int j=0; j<serviceList.size(); j++){
			ServiceList sl = serviceList.get(j);
			sheet1.addCell(new Number(11, 43+j, j+1));
			sheet1.mergeCells(14, 43+j, 20, 43+j);
			sheet1.addCell(new Label(14, 43+j, sl.getServiceName()));
			sheet1.addCell(new Number(21, 43+j, sl.getPrice()));
		}
		
		sheet1.addCell(new Number(21, 47, form.getTotalPrice()));
	}

}
