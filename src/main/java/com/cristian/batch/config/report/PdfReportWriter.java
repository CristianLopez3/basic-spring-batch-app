package com.cristian.batch.config.report;
import com.cristian.batch.entity.CovidReport;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfReportWriter implements ItemWriter<CovidReport> {

    @Override
    public void write(Chunk<? extends CovidReport> chunk) throws Exception {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("covid_report.pdf"));
            document.open();

            for (CovidReport report : chunk.getItems()) {
                document.add(new Paragraph("Date: " + report.getDate()));
                document.add(new Paragraph("Confirmed Cases: " + report.getConfirmedCases()));
                document.add(new Paragraph("New Admissions: " + report.getNewAdmissions()));
                document.add(new Paragraph("Discharges: " + report.getDischarges()));
                document.add(new Paragraph("New Cases: " + report.getNewCases()));
                document.add(new Paragraph(" "));
            }

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            throw new Exception("Error generating PDF report", e);
        } finally {
            document.close();
        }
    }
}