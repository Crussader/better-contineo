package backend.database;

import java.sql.SQLException;

class AlreadySQLConnectedException extends SQLException {
    public AlreadySQLConnectedException(String msg) {
        super(msg);
    }
}
