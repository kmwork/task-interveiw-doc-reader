package kostya.hacker.parsers;

import java.util.Set;

/**
 * Базовая фабрика для получения класса Reader под конкретный тип документа
 **/
public interface AbstractDocTypeFactory {

    SingleDocTypeReader getParserByDocType(String docType);

    Set<String> getAllDocTypes();
}
