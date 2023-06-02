package kostya.hacker.parsers;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class FileUtils {
    private FileUtils() {
    }

    /**
     * Получить тип файла по имени файла
     *
     * @param file
     * @return "docx" или "pdf"
     */
    public static String parseDocTypeByFile(File file) {
        return FilenameUtils.getExtension(file.getName());
    }
}
