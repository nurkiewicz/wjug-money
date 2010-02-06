package com.blogspot.nurkiewicz.money;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tomasz Nurkiewicz
 * @since 2010-01-05, 20:11:38
 */
public class SpreadsheetToMoneyTransferListTransformer extends AbstractTransformer {

	private static final Logger log = LoggerFactory.getLogger(SpreadsheetToMoneyTransferListTransformer.class);

	public SpreadsheetToMoneyTransferListTransformer() {
		registerSourceType(InputStream.class);
		setReturnClass(List.class);
	}

	@Override
	protected List<MoneyTransfer> doTransform(Object src, String encoding) throws TransformerException {
		try {
			List<MoneyTransfer> transferList = new ArrayList<MoneyTransfer>();
			Workbook workbook = WorkbookFactory.create((InputStream) src);
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				MoneyTransfer transfer = parseRow(row);
				if(transfer != null)
					transferList.add(transfer);
			}
			return transferList;
		} catch (Exception e) {
			throw new TransformerException(this, e);
		}
	}

	private MoneyTransfer parseRow(Row row) {
		try {
			log.debug("Processing row: {}", row.getRowNum() + 1);
			if(row.getLastCellNum() != 3){
				log.warn("Row {} does not have exactly 3 columns but {}", row.getRowNum(), row.getLastCellNum() + 1);
				return null;
			}
			if (row.getCell(0).getCellType() != Cell.CELL_TYPE_STRING) {
				log.warn("Name column is not of String type but: {}", row.getCell(0).getCellType());
				return null;
			}
			if (row.getCell(1).getCellType() != Cell.CELL_TYPE_STRING) {
				log.warn("Account number column is not of String type but: {}", row.getCell(1).getCellType());
				return null;
			}
			if (row.getCell(2).getCellType() != Cell.CELL_TYPE_NUMERIC) {
				log.warn("Money transfer amount is not of number type but: {}", row.getCell(2).getCellType());
				return null;
			}
			final String name = row.getCell(0).getRichStringCellValue().getString();
			final String accountNo = row.getCell(1).getRichStringCellValue().getString();
			final double amountNumber = row.getCell(2).getNumericCellValue();
			BigDecimal amount = new BigDecimal((long)(amountNumber * 100));
			amount = amount.divide(BigDecimal.valueOf(100));
			return new MoneyTransfer(name, accountNo, amount);
		} catch (Exception e) {
			log.warn("Exception while processing the row", e);
			return null;
		}

		/*if (DateUtil.isCellDateFormatted(cell)) {
					 System.out.println(cell.getDateCellValue());
				 } else {
					 System.out.println(cell.getNumericCellValue());
				 }*/
	}
}
