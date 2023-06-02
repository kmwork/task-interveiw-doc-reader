package kostya.hacker.parsers;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.IOException;

/**
 * Читатель Docx(Word) форматов
 */
public class DocxReader implements SingleDocTypeReader {
    private static final String DOC_TYPE = "docx";

    @Override
    public String getDocType() {
        return DOC_TYPE;
    }

    @Override
    public int calcNumbersOfPages(File docFile) throws IOException {
        try (XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(docFile.getAbsolutePath()))) {
            return docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        }
    }
}
