package kostya.hacker;

import kostya.hacker.parsers.AllDocumentReader;
import kostya.hacker.parsers.DocxAndPdfFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Collection;
import java.util.Set;

/**
 * Консольное приложение, это точка входа в приложение
 */
@Slf4j
@SpringBootApplication
public class MainApp implements CommandLineRunner {

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(MainApp.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        try {
            if (args.length != 1 || Strings.isEmpty(args[0])) {
                System.err.println("Invalid argument of application");
                System.exit(100);
            }
            String rootPath = args[0];
            log.info("[App] Reading folder: {}", rootPath);
            File rootFile = new File(rootPath);
            if (!rootFile.isDirectory() || !rootFile.exists()) {
                System.err.println("Invalid folder path = " + rootPath);
                System.exit(200);
            }

            log.info("[App] rootFile = {}", rootFile);

            DocxAndPdfFactory docFactory = new DocxAndPdfFactory();
            AllDocumentReader allReader = new AllDocumentReader(docFactory);

            Set<String> docTypes = docFactory.getAllDocTypes();
            String[] extensions = new String[docTypes.size()];
            docTypes.toArray(extensions);

            Collection<File> images = FileUtils.listFiles(rootFile, extensions, true);

            for (File file : images) {
                allReader.addFileForAnalyze(file);
            }
            AllDocumentReader.AnalyzeData result = allReader.getResultOnFinish();

            System.out.println("Documents: " + result.getFileCounter());
            System.out.println("Pages: " + result.getPageCounter());
            System.out.println("Errors: count = " + result.getErrorFileList().size() + " into files = " + result.getErrorFileList());
            System.out.println("Skip files: " + result.getSkipFileCounter());
        } catch (Exception ex) {
            log.error("[App] Error into Application", ex);
            System.exit(999);
        }
    }
}