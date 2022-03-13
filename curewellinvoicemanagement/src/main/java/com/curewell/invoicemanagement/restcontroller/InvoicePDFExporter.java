package com.curewell.invoicemanagement.restcontroller;

import java.awt.Color;
import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.curewell.invoicemanagement.vo.CurewellInvoiceOrderVO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class InvoicePDFExporter {
	private List<CurewellInvoiceOrderVO> invoiceList;

	@Autowired
	ResourceLoader resourceLoader;

	public InvoicePDFExporter(List<CurewellInvoiceOrderVO> invoiceList) {
		this.invoiceList = invoiceList;
	}

	private void writeTableHeader(PdfPTable table) throws IOException {

		Image img = Image.getInstance(this.getClass().getResource("/curewellLogo.jpg"));

		img.scaleAbsolute(500, 100);

		Phrase phrase = new Phrase();
		phrase.add(new Chunk(img, 0, 25));

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.WHITE);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.RED);
		font.setSize(15);

		cell.setPhrase(phrase);

		table.addCell(cell);

		PdfPCell cell2 = new PdfPCell();
		cell2.setBackgroundColor(Color.WHITE);
		cell2.setPadding(5);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

		font.setColor(Color.BLACK);
		font.setSize(15);
		cell2.setPhrase(new Phrase("Tax Invoice", font));
		table.addCell(cell2);

	}

	private void writeTableData(PdfPTable table) throws BadElementException, IOException {
		int counter = 0;
		double totalAmountBeforeTax = 0.0;
		double totalAmountAfterTax = 0.0;
		double totalAmountCGST = 0.0;
		double totalAmountSGST = 0.0;
		double totalAdvanceAmount = 0.0;
		double totalOutStanding = 0.0;
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		PdfPTable tabledetail = new PdfPTable(2);
		PdfPTable tabledetailrow = new PdfPTable(8);
		PdfPTable tableSummaryrow = new PdfPTable(2);

		PdfPCell summaryCell = new PdfPCell();
		PdfPCell cellrheader = new PdfPCell();

		PdfPCell itemcell = new PdfPCell();
		itemcell.setBackgroundColor(Color.WHITE);
		itemcell.setPadding(5);
		PdfPCell itemheader = new PdfPCell();
		itemheader.setBackgroundColor(Color.WHITE);
		itemheader.setPadding(5);
		font.setColor(Color.BLACK);
		font.setSize(5);

		font.setColor(Color.BLACK);
		font.setSize(5);

		cellrheader.setPhrase(new Phrase("SL NO", font));

		tabledetailrow.addCell(cellrheader);

		cellrheader.setPhrase(new Phrase("Product Description", font));
		tabledetailrow.addCell(cellrheader);

		cellrheader.setPhrase(new Phrase("Quantity", font));
		tabledetailrow.addCell(cellrheader);

		cellrheader.setPhrase(new Phrase("Rate", font));
		tabledetailrow.addCell(cellrheader);

		cellrheader.setPhrase(new Phrase("Taxable Amount", font));
		tabledetailrow.addCell(cellrheader);

		cellrheader.setPhrase(new Phrase("CGST", font));
		tabledetailrow.addCell(cellrheader);

		cellrheader.setPhrase(new Phrase("SGST", font));
		tabledetailrow.addCell(cellrheader);

		cellrheader.setPhrase(new Phrase("Total Amount", font));
		tabledetailrow.addCell(cellrheader);

		for (CurewellInvoiceOrderVO list : invoiceList) {
			double totalAmountCgst = 0.0;
			double totalAmountSgst = 0.0;
			totalOutStanding = list.getOutstandingamount();

			totalAmountCgst = ((list.getProductprice() * list.getQuantity()) * list.getCgstpercent()) / 100;
			totalAmountSgst = ((list.getProductprice() * list.getQuantity()) * list.getSgstpercent()) / 100;
			totalAmountCGST = totalAmountCGST + totalAmountCgst;
			totalAmountSGST = totalAmountSGST + totalAmountSgst;
			totalAmountBeforeTax = totalAmountBeforeTax + list.getTotalpricebeforetax();
			totalAmountAfterTax = totalAmountAfterTax + list.getTotalpriceaftertax();

			if (counter == 0) {

				itemheader.setPhrase(new Phrase("Invoice No:" + list.getInvoicenumber(), font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("PO No:" + list.getPonumber(), font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("Invoice Date:" + list.getInvoicedate(), font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("Po Date:" + list.getPodate(), font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("Bill To Party", font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("Ship to Party", font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("Name:" + list.getPartytobill(), font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("Name:" + list.getShiptopartyname(), font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("Address:" + list.getPartyaddress(), font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("Shipping Address:" + list.getSupplyplace(), font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(new Phrase("GSTN:" + list.getPartygstn(), font));
				tabledetail.addCell(itemheader);

				itemheader.setPhrase(
						new Phrase("State:" + list.getPartystate() + "     State Code:" + list.getStatecode(), font));
				tabledetail.addCell(itemheader);

				totalAdvanceAmount = list.getAdvancepaid();
				itemcell.setPhrase(new Phrase(Integer.toString(counter + 1), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(list.getProductcode(), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(Integer.toString((list.getQuantity())), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(Double.toString(list.getProductprice()), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(Double.toString(list.getTotalpricebeforetax()), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(
						new Phrase("@%" + list.getCgstpercent() + "-" + Double.toString(totalAmountCgst), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(
						new Phrase("@%" + list.getSgstpercent() + "-" + Double.toString(totalAmountSgst), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(Double.toString(list.getTotalpriceaftertax()), font));
				tabledetailrow.addCell(itemcell);
				counter++;

			} else {

				itemcell.setPhrase(new Phrase(Integer.toString(counter + 1), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(list.getProductcode(), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(Integer.toString((list.getQuantity())), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(Double.toString(list.getProductprice()), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(Double.toString(list.getTotalpricebeforetax()), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(
						new Phrase("@%" + list.getCgstpercent() + "-" + Double.toString(totalAmountCgst), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(
						new Phrase("@%" + list.getSgstpercent() + "-" + Double.toString(totalAmountSgst), font));
				tabledetailrow.addCell(itemcell);
				itemcell.setPhrase(new Phrase(Double.toString(list.getTotalpriceaftertax()), font));
				tabledetailrow.addCell(itemcell);
				counter++;

			}

		}
		summaryCell.setPhrase(new Phrase("Total Amount Before Tax", font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase(Double.toString(totalAmountBeforeTax), font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase("Total Amount CGST ", font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase(Double.toString(totalAmountCGST), font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase("Total Amount SGST ", font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase(Double.toString(totalAmountSGST), font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase("Total Amount After Tax ", font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase(Double.toString(totalAmountAfterTax), font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase("Total Advance Amount Paid ", font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase(Double.toString(totalAdvanceAmount), font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase("Total Invoice Amount ", font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase(Double.toString(totalAmountAfterTax), font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase("Total Outstanding Amount ", font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase(Double.toString(totalOutStanding), font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase("Bank Details: ", font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase("ICICI BANK Account No:036505500038\n IFSC CODE:ICIC0000365\n", font));
		tableSummaryrow.addCell(summaryCell);
		summaryCell.setPhrase(new Phrase(
				"Terms & Conditions\n # Goods once sold will not be taken back\n# The company not responsible for any\n physical damage of the product after delivery.\n"
						+ "# Interest @24% will be charged if payment is\nnot paid within the payment term.\nPayment right after delivery of product and\n"
						+ "post installation.\n#Freight Charges are extra borne by the party to billed.",
				font));
		tableSummaryrow.addCell(summaryCell);

		Image img2 = Image.getInstance(this.getClass().getResource("/Curewell_Signature.jpg"));
		img2.scaleAbsolute(250, 50);

		Phrase phrase = new Phrase();
		phrase.add(new Chunk(img2, 0, 20));

		summaryCell.setPhrase(phrase);
		tableSummaryrow.addCell(summaryCell);
		table.addCell(tabledetail);
		table.addCell(tabledetailrow);
		table.addCell(tableSummaryrow);
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Tax Invoice", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 2.5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}
