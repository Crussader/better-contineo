package backend.logging;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetupLog {

    private final Object classObject;
    private final Level logLevel;
    private final String directory;

    public SetupLog(Object classObject, Level level, String directory) {
        this.classObject = classObject;
        this.logLevel = level;
        this.directory = directory;
    }

    public SetupLog(Object classObject, Level level) {
        this(classObject, level, "./logs/");
    }

    public SetupLog(Object classObject) {
        this(classObject, Level.INFO, "./logs/");
    }

    public final Logger setupLogging() {
        /*
        Creates the logging files if it doesn't exist.
         */

        Logger logger = Logger.getLogger(this.classObject.getClass().getName());

        logger.setLevel(this.logLevel);

        String pattern = this.directory + this.classObject.getClass().getName();

        try {
            // Create our file handler
            Handler handler = new FileHandler(pattern, 2000, 1);

            handler.setLevel(logLevel);
            handler.setFormatter(new LogFormat());

            logger.addHandler(handler);

            logger.info("Set up logger.");
        } catch (NoSuchFileException e) {
            /*
            No Such file exists so we much create the directory
             */
            File directory = new File("./logs/");
            if (!directory.exists()) {
                try {
                    boolean result = directory.mkdirs();

                    if (result) {
                        System.out.println("Created Directories for logging.");
                        return this.setupLogging();
                    }
                } catch (SecurityException exec) {
                    e.printStackTrace();
                }

            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        return logger;
    }
}
