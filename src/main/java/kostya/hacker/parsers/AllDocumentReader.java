package kostya.hacker.parsers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Основной класс, реализующий подсчет страниц по всем добавленным в анализ файлов
 */
@Slf4j
public class AllDocumentReader {
    private final AnalyzeData result = new AnalyzeData();
    private final AbstractDocTypeFactory doctypeFactory;


    public AllDocumentReader(AbstractDocTypeFactory doctypeFactory) {
        this.doctypeFactory = doctypeFactory;
    }

    /**
     * Класс по выходным данным анализа документов
     **/
    @Getter
    public static class AnalyzeData {
        private final List<File> errorFileList = new LinkedList<>();
        private int pageCounter;
        private int fileCounter;
        private int skipFileCounter;

    }

    /**
     * Найти Reader по нужному файлу
     *
     * @param file - файл, по которому нужно подсчитать страницы
     * @return
     */
    private SingleDocTypeReader findReaderByFile(File file) {
        String docType = FileUtils.parseDocTypeByFile(file);
        log.debug("docType = {}, for file = {}", docType, file.getAbsolutePath());
        return doctypeFactory.getParserByDocType(docType);
    }

    /**
     * Добавить файл для анализа страниц
     *
     * @param file
     */
    public void addFileForAnalyze(File file) {
        boolean isExists = file.isFile() && file.exists();
        log.debug("read file = {}, exist = {}", file.getAbsolutePath(), isExists);
        if (!isExists) {
            result.skipFileCounter++;
            return;
        }
        try {
            SingleDocTypeReader reader = findReaderByFile(file);
            result.pageCounter += reader.calcNumbersOfPages(file);
            result.fileCounter++;
        } catch (Exception ex) {
            log.error("[Error] in read file = {}", file.getAbsolutePath(), ex);
            result.errorFileList.add(file);
            result.skipFileCounter++;
        }
    }

    /**
     * Получить данных по анализу (нужно вызывать после того, как кончились все файлы в папке)
     *
     * @return
     */
    public AnalyzeData getResultOnFinish() {
        return result;
    }

}
