package com.saucedemo.utilities;

import java.sql.*;
import java.util.*;

public class DatabaseExecution {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * Create a jdbc connection using the url, username, password
     * This method establishes a JDBC connection using the environment variables
     * for the database URL, username, and password.
     * It uses the DriverManager class to create a connection object.
     * If any SQLException occurs during the process, it is caught and printed.
     */

    public static void createConnection() {
        String url = ConfigurationReader.get("dbUrl");
        String username = ConfigurationReader.get("dbUsername");
        String password = ConfigurationReader.get("dbPassword");
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Close existing jdbc connection, destroy the Statement, ResultSet objects
     */
    public static void destroyConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param query The SQL query to execute
     * @param columnNumber The index of the column to retrieve the value from
     * @return The value of the specified cell
     */
    public static Object getCellValue(String query, int columnNumber) {
        return getQueryResultList(query).get(0).get(columnNumber);
    }

    /**
     *
     * @param query The SQL query to execute
     * @return returns a list of Strings which represent a row of data. If the query
     *         results in multiple rows and/or columns of data, only first row will
     *         be returned. The rest of the data will be ignored
     */
    public static List<Object> getRowList(String query) {
        return getQueryResultList(query).get(0);
    }

    /**
     *
     * @param query The SQL query to execute
     * @return returns a map which represent a row of data where key is the column
     *         name. If the query results in multiple rows and/or columns of data,
     *         only first row will be returned. The rest of the data will be ignored
     */
    public static Map<String, Object> getRowMap(String query) {
        return getQueryResultMap(query).get(0);
    }

    public static Map<String, List<String>> getMapList(String query) {
        return getQueryResultMapList(query);
    }

    /**
     *
     * @param query The SQL query to execute
     * @return returns query result in a list of lists where outer list represents
     *         collection of rows and inner lists represent a single row
     */
    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }

                rowList.add(row);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowList;
    }

    public static List<List<String>> getResultListOfListString(String query) {
        executeQuery(query);
        List<List<String>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                rowList.add(row);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    public static List<String> getAllData(String query) {
        executeQuery(query);
        List<String> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    rowList.add(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowList;

    }

    public static List<List<String>> getQueryResultListString(String query) {
        executeQuery(query);
        List<List<String>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }

                rowList.add(row);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowList;

    }

    /**
     *
     * @param query
     * @param column
     * @return list of values of a single column from the result set
     */
    public static List<Object> getColumnData(String query, String column) {
        executeQuery(query);
        List<Object> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                rowList.add(resultSet.getObject(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowList;

    }

    /**
     *
     * @param query
     * @return returns query result in a list of maps where the list represents
     *         collection of rows and a map represents a single row with
     *         key being the column name
     */
    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            rsmd = resultSet.getMetaData();

            while (resultSet.next()) {

                Map<String, Object> colNameValueMap = new HashMap<>();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {

                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }

                rowList.add(colNameValueMap);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowList;

    }

    public static Map<String, List<String>> getQueryResultMapList(String query) {
        executeQuery(query);
        // Create map to hold the fetched data
        Map<String, List<String>> dataMap = new HashMap<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            // Iterate through the result set and populate the map
            while (resultSet.next()) {
                String category = resultSet.getString(1);
                String subcategory = resultSet.getString(2);
                // Add subcategory to the list for the corresponding category
                List<String> subcategories = dataMap.getOrDefault(category, new ArrayList<>());
                subcategories.add(subcategory);
                Collections.sort(subcategories);
                dataMap.put(category, subcategories);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    /**
     *
     * @param query
     * @return List of columns returned in result set
     */
    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columns;

    }

    public static List<String> getColumnValues(String query) {
        executeQuery(query);
        List<String> values = new ArrayList<>();
        ResultSetMetaData rsmd;

        try {
            while (resultSet.next()) {
                // Assuming there is only one column in the result set
                values.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
        return values;
    }

    public static String getColumnValue(String query, int columnIndex) {
        executeQuery(query);
        String value = "";
        ResultSetMetaData rsmd;

        try {
            while (resultSet.next()) {
                // Assuming there is only one column in the result set
                value = resultSet.getString(columnIndex);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
        return value;
    }

    private static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeQueryUpdate(String query) {

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeQueryDelete(String query) {

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getRowCount() throws Exception {
        resultSet.last();
        int rowCount = resultSet.getRow();
        return rowCount;
    }
}
