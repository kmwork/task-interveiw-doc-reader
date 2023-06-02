package kostya.hacker.parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DocxAndPdfFactory implements AbstractDocTypeFactory {

    private final Map<String, SingleDocTypeReader> readerMap = new HashMap<>();

    /**
     * Регистрация обработчиков разных типов файлов
     */
    public DocxAndPdfFactory() {
        addReader(new DocxReader());
        addReader(new PdfReader());
    }

    private void addReader(SingleDocTypeReader reader) {
        readerMap.put(reader.getDocType(), reader);
    }

    @Override
    public SingleDocTypeReader getParserByDocType(String docType) {
        return readerMap.get(docType);
    }

    @Override
    public Set<String> getAllDocTypes() {
        return readerMap.keySet();
    }
}
