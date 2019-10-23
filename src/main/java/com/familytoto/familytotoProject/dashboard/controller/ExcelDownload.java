package com.familytoto.familytotoProject.dashboard.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.exp.domain.ExpVO;

@Component("statXls")
public class ExcelDownload extends AbstractXlsView{
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CellStyle numberCellStyle = workbook.createCellStyle();
		DataFormat numberDataFormat = workbook.createDataFormat();

		numberCellStyle.setDataFormat(numberDataFormat.getFormat("#,##0"));

		Sheet sheet = workbook.createSheet("info");

		Row row = createRow(sheet, 1);
		setCell(workbook, sheet, row,1,model.get("nickname")+"님의 정보" , false);
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 3500);
		sheet.setColumnWidth(2, 3500);
		sheet.setColumnWidth(3, 5000);
		
		sheet.setColumnWidth(5, 3500);
		sheet.setColumnWidth(6, 3500);
		sheet.setColumnWidth(7, 5000);
		
		sheet.setColumnWidth(9, 3500);
		sheet.setColumnWidth(10, 3500);
		sheet.setColumnWidth(11, 5000);
		
		// 보유 크레딧
		row = createRow(sheet, 2);
		setCell(workbook, sheet, row,2, "보유 크레딧", false);
		setCell(workbook, sheet, row,3, model.get("haveCredit").toString(),true);
		
		// 보유 마일리지
		row = createRow(sheet, 3);
		setCell(workbook, sheet, row,2, "보유 마일리지", false);
		setCell(workbook, sheet, row,3, model.get("haveMileage").toString(), true);
		
		// 레벨
		row = createRow(sheet, 4);
		setCell(workbook, sheet, row,2, "현재 레벨", false);
		setCell(workbook, sheet, row,3, model.get("haveLevel").toString(), true);
		
		// 경험치
		row = createRow(sheet, 5);
		setCell(workbook, sheet, row,2, "경험치", false);
		setCell(workbook, sheet, row,3, model.get("haveExp").toString(), true);
		
		// 작성한 게시글 개수
		row = createRow(sheet, 7);
		setCell(workbook, sheet, row,2, "작성한 게시글", false);
		setCell(workbook, sheet, row,3, model.get("haveBoard").toString(), true);
		
		// 작성한 댓글
		row = createRow(sheet, 8);
		setCell(workbook, sheet, row,2, "작성한 댓글", false);
		setCell(workbook, sheet, row,3, model.get("haveComment").toString(), true);
		
		// 상품평
		row = createRow(sheet, 9);
		setCell(workbook, sheet, row,2, "상품평", false);
		setCell(workbook, sheet, row,3, model.get("haveProductComment").toString(), true);
		
		// 전체 정보
		row = createRow(sheet, 11);
		setCell(workbook, sheet, row,2, "크레딧 정보", false);
		setCell(workbook, sheet, row,6, "마일리지 정보", false);
		setCell(workbook, sheet, row,10, "경험치 정보", false);
		
		row = createRow(sheet, 12);
		setCell(workbook, sheet, row,2, "상태",false);
		setCell(workbook, sheet, row,3, "값",false);
		setCell(workbook, sheet, row,4, "날짜",false);
		
		setCell(workbook, sheet, row,6, "상태",false);
		setCell(workbook, sheet, row,7, "값",false);
		setCell(workbook, sheet, row,8, "날짜",false);
		
		setCell(workbook, sheet, row,10, "상태",false);
		setCell(workbook, sheet, row,11, "값",false);
		setCell(workbook, sheet, row,12, "날짜",false);
		
		List<CreditVO> creditList = (List<CreditVO>) model.get("creditInfo");
		List<MileageVO> mileageList = (List<MileageVO>) model.get("mileageInfo");
		List<ExpVO> expList = (List<ExpVO>) model.get("expInfo");
		
		for (int x = 0; x < 100; x++) {
			row = createRow(sheet, 13+x);
			
			// 크레딧
			if(creditList.size() > x) {
				setCell(workbook, sheet, row,2,creditList.get(x).getCreditState(), false);	// 상태
				setCell(workbook, sheet, row,3, Integer.toString(creditList.get(x).getCreditValue()), true);	// 값
				setCell(workbook, sheet, row,4,creditList.get(x).getRegCreditDt(), false);	// 날짜
			}
			
			// 마일리지
			if(mileageList.size() > x) {
				setCell(workbook, sheet, row,6,mileageList.get(x).getMileageState(), false);	// 상태
				setCell(workbook, sheet, row,7, Integer.toString(mileageList.get(x).getMileageValue()), true);	// 값
				setCell(workbook, sheet, row,8,mileageList.get(x).getRegMileageDt(), false);	// 날짜
			}
			
			// 경험치
			if(expList.size() > x) {
				setCell(workbook, sheet, row,10,expList.get(x).getExpState(), false);	// 상태
				setCell(workbook, sheet, row,11, Integer.toString(expList.get(x).getExpValue()), true);	// 값
				setCell(workbook, sheet, row,12,expList.get(x).getRegExpDt(), false);	// 날짜
			}
		}

		try {
			response.setHeader("Content-Disposition", "attachement; filename=\""
					+ java.net.URLEncoder.encode(model.get("nickname") + "님의 대시보드.xls", "UTF-8") + "\";charset=\"UTF-8\"");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public Row createRow(Sheet sheet, int rowIdx) {
		rowIdx--;
		return sheet.createRow(rowIdx);
	}
	
	public void setCell(Workbook workbook, Sheet sheet,
			Row row, int columnIdx,
			String value, boolean numberOption) {
		columnIdx--;
		Cell cell = row.createCell(columnIdx);
		cell.setCellValue(value);
		
		if(numberOption == true) {
			CellStyle numberCellStyle = workbook.createCellStyle();
			DataFormat numberDataFormat = workbook.createDataFormat();
			numberCellStyle.setDataFormat(numberDataFormat.getFormat("#,##0"));
			cell.setCellStyle(numberCellStyle);
		}
	}
}
