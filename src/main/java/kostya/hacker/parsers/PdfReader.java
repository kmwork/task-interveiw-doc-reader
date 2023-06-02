package kostya.hacker.parsers;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * Читатель PDF-документов
 */
public class PdfReader implements SingleDocTypeReader {
    private static final String DOC_TYPE = "pdf";

    @Override
    public String getDocType() {
        return DOC_TYPE;
    }


    @Override
    public int calcNumbersOfPages(File pdfFile) throws IOException {
        try (PDDocument pdfDoc = PDDocument.load(pdfFile)) {
            return pdfDoc.getPages().getCount();
        }
    }
}
