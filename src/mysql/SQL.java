/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Saliya
 */
public class SQL {

    private static SQL INSTENCE = null;
    private static Connection MYSQL_CONNECTION;
    private final String IP = "127.0.0.1";
    private final int TIMEOUT = 3000;
    private final String PORT = "3306";
    private final String DATABASE = "world";
    private final String USER = "root";
    private final String PASSWORD = "";
    private DatabaseMetaData DATABASE_METADATA = null;
    private final boolean CONNECTION_AUTOCOMMIT = false;
    private final boolean AUTORECONNECT = true;
    private static ResultSet RESULTSET;
    private static PreparedStatement PREPAREDSTATEMENT;

    public SQL() {

    }

    public Connection EstablishConnection() throws Exception {
        if (!isReachable(IP, TIMEOUT)) {
            return null;
        } else {
            try {
                if (MYSQL_CONNECTION == null || MYSQL_CONNECTION.isClosed()) {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    MYSQL_CONNECTION = DriverManager.getConnection("jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE + "?autoReconnect=" + AUTORECONNECT, USER, PASSWORD);
                    MYSQL_CONNECTION.setAutoCommit(CONNECTION_AUTOCOMMIT);
                }
                return MYSQL_CONNECTION;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                throw new Exception(e);
            } finally {
                try {
                    if (MYSQL_CONNECTION == null) {
                        MYSQL_CONNECTION.close();
                    }
                } catch (SQLException e) {
                    MYSQL_CONNECTION.close();
                    throw new Exception(e);
                }
            }
        }

    }

    private boolean isReachable(String IP, int Timeout) {
        try {
            InetAddress inet = InetAddress.getByName(IP);
            boolean status = inet.isReachable(Timeout);
            return status;
        } catch (UnknownHostException ex) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public static synchronized SQL getInstence() {
        if (INSTENCE == null) {
            INSTENCE = new SQL();
        }
        return INSTENCE;
    }

    public void setCommand(Object sqlQuery) throws Exception {
        PREPAREDSTATEMENT = SQL.MYSQL_CONNECTION.prepareStatement(sqlQuery.toString());
        PREPAREDSTATEMENT.executeUpdate();
    }

    public ResultSet setCommand(String sqlQuery) throws Exception {
        RESULTSET = SQL.MYSQL_CONNECTION.createStatement().executeQuery(sqlQuery);
        return RESULTSET;
    }

    public DatabaseMetaData getMetadata() throws Exception {
        try {
            if (MYSQL_CONNECTION != null) {
                this.DATABASE_METADATA = MYSQL_CONNECTION.getMetaData();
            }
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return this.DATABASE_METADATA;
    }
}
