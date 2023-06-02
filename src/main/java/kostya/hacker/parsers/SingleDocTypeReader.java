package kostya.hacker.parsers;

import java.io.File;
import java.io.IOException;

/**
 * Интерфейс для чтения странниц по определенному типу документов
 */
public interface SingleDocTypeReader {
    /**
     * расширение файлов которое подлежит обработке данным обработчиком
     *
     * @return
     */
    String getDocType();

    /**
     * @param docFile - файл, для которого нужно подчитать количество страниц
     * @return количество страниц в файле
     * @throws IOException
     */
    int calcNumbersOfPages(File docFile) throws IOException;

}
