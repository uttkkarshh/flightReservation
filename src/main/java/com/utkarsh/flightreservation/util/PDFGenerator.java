package com.utkarsh.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import com.utkarsh.flightreservation.entities.Reservation;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
@Component
public class PDFGenerator {
	private static final Logger LOGGER=LoggerFactory.getLogger(PDFGenerator.class);
	public void generateItinerary(Reservation reservation ,String filePath) {
 LOGGER.info("Inside generate Itinerary");
		 Document document =new Document();
		 try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			document.add(generateTable(reservation));
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			LOGGER.error("Failed to GEnerate Pdf:"+e);
			//e.printStackTrace();
		}
	}

	private PdfPTable generateTable(Reservation r) {
		PdfPTable table=new PdfPTable(2);
		
		PdfPCell cell;
		cell=new PdfPCell(new Phrase("Flight Ttinerary"));
		cell.setColspan(2);
		table.addCell(cell);
		table.addCell("Departure City");
		table.addCell(r.getFlight().getDepartureCity());
		
		table.addCell("arrival City");
		table.addCell(r.getFlight().getArrivalCity());
		
		table.addCell("Flight Number");
		table.addCell(r.getFlight().getFlightNumber());
		
		table.addCell("Departure Date");
		table.addCell(r.getFlight().getDateofDeparture().toString());
		
		table.addCell("Departure Time");
		table.addCell(r.getFlight().getEstimate().toString());
		
		table.addCell("First Name");
		table.addCell(r.getPassenger().getFirstName());
		
		table.addCell("Last Name");
		table.addCell(r.getPassenger().getLastName());
		
		table.addCell("Email");
		table.addCell(r.getPassenger().getEmail());
		
		
		return table;
	}
}
