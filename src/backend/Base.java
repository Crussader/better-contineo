package backend;


import java.util.logging.Logger;
import java.util.logging.LogRecord;
import java.util.logging.Level;
public class Base {

    private Level logLevel;

    public Base(Level logLevel) {
        this.logLevel = logLevel;
    }

    public Logger setupConsoleLogging(String name) {
        /*
         * Creates a Logger that uses the standard output.
         */
        Logger logger = Logger.getLogger(name);
        logger.setLevel(this.logLevel);


        return logger;
    }

    public Logger setupFileLogging(String name, boolean file) {
        return Logger.getLogger(name);
    }
}
