package backend.logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormat extends Formatter {

    @Override
    public String format(LogRecord record) {
        return record.getLongThreadID() + "::" + record.getSourceClassName() + "::"
                + record.getSourceMethodName() + "::"
                + new Date(record.getMillis()) + "::"
                + record.getMessage() + "\n";
    }
}
