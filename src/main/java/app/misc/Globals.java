package app.misc;

import java.util.logging.Level;

public interface Globals {

    boolean DEBUG = true;
    boolean SQL_DEBUG = false;
    boolean SQL_CONN = false;
    boolean VERBOSE_FACTORY = false;

    Level LOG_LEVEL = Level.WARNING;

    String DB_PREFIX = "tdc_";

    String ROOT_PATH = "src/main/java/app/";

    boolean SAFE_UPDATE = true;//enforce objects to have a backup to fallback to in case update statement fails
}
