package github.hacimertgokhan.m1.jdbc;

import github.hacimertgokhan.m1.core.Table;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class M1ResultSet implements ResultSet {
    private final List<String> columnNames;
    private final List<List<Object>> rows;
    private int cursor = -1; 

    public M1ResultSet(Table table) {
        this.columnNames = new ArrayList<>(table.getColumnNames());
        this.rows = new ArrayList<>(table.getRows());
    }

    @Override
    public boolean next() throws SQLException {
        if (cursor < rows.size() - 1) {
            cursor++;
            return true;
        }
        return false;
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        
        if (columnIndex < 1 || columnIndex > columnNames.size()) {
            throw new SQLException("Geçersiz kolon indeksi: " + columnIndex);
        }
        Object value = rows.get(cursor).get(columnIndex - 1);
        return value == null ? null : value.toString();
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code boolean} in the Java programming language.
     *
     * <P>If the designated column has a datatype of CHAR or VARCHAR
     * and contains a "0" or has a datatype of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * and contains  a 0, a value of {@code false} is returned.  If the designated column has a datatype
     * of CHAR or VARCHAR
     * and contains a "1" or has a datatype of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * and contains  a 1, a value of {@code true} is returned.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code false}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        return false;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code byte} in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public byte getByte(int columnIndex) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code short} in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public short getShort(int columnIndex) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * an {@code int} in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public int getInt(int columnIndex) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code long} in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public long getLong(int columnIndex) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code float} in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public float getFloat(int columnIndex) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code double} in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public double getDouble(int columnIndex) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code java.sql.BigDecimal} in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param scale       the number of digits to the right of the decimal point
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs or this method is
     *                                         called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @deprecated Use {@code getBigDecimal(int columnIndex)}
     * or {@code getBigDecimal(String columnLabel)}
     */
    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code byte} array in the Java programming language.
     * The bytes represent the raw values returned by the driver.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        return new byte[0];
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code java.sql.Date} object in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public Date getDate(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code java.sql.Time} object in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public Time getTime(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code java.sql.Timestamp} object in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a stream of ASCII characters. The value can then be read in chunks from the
     * stream. This method is particularly
     * suitable for retrieving large {@code LONGVARCHAR} values.
     * The JDBC driver will
     * do any necessary conversion from the database format into ASCII.
     *
     * <P><B>Note:</B> All the data in the returned stream must be
     * read prior to getting the value of any other column. The next
     * call to a getter method implicitly closes the stream.  Also, a
     * stream may return {@code 0} when the method
     * {@code InputStream.available}
     * is called whether there is data available or not.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a Java input stream that delivers the database column value
     * as a stream of one-byte ASCII characters;
     * if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * as a stream of two-byte 3 characters. The first byte is
     * the high byte; the second byte is the low byte.
     * <p>
     * The value can then be read in chunks from the
     * stream. This method is particularly
     * suitable for retrieving large {@code LONGVARCHAR}values.  The
     * JDBC driver will do any necessary conversion from the database
     * format into Unicode.
     *
     * <P><B>Note:</B> All the data in the returned stream must be
     * read prior to getting the value of any other column. The next
     * call to a getter method implicitly closes the stream.
     * Also, a stream may return {@code 0} when the method
     * {@code InputStream.available}
     * is called, whether there is data available or not.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a Java input stream that delivers the database column value
     * as a stream of two-byte Unicode characters;
     * if the value is SQL {@code NULL}, the value returned is
     * {@code null}
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs or this method is
     *                                         called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @deprecated use {@code getCharacterStream} in place of
     * {@code getUnicodeStream}
     */
    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a  stream of
     * uninterpreted bytes. The value can then be read in chunks from the
     * stream. This method is particularly
     * suitable for retrieving large {@code LONGVARBINARY} values.
     *
     * <P><B>Note:</B> All the data in the returned stream must be
     * read prior to getting the value of any other column. The next
     * call to a getter method implicitly closes the stream.  Also, a
     * stream may return {@code 0} when the method
     * {@code InputStream.available}
     * is called whether there is data available or not.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a Java input stream that delivers the database column value
     * as a stream of uninterpreted bytes;
     * if the value is SQL {@code NULL}, the value returned is
     * {@code null}
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        int columnIndex = columnNames.indexOf(columnLabel.toUpperCase()) + 1;
        if (columnIndex == 0) {
            throw new SQLException("Geçersiz kolon adı: " + columnLabel);
        }
        return getString(columnIndex);
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code boolean} in the Java programming language.
     *
     * <P>If the designated column has a datatype of CHAR or VARCHAR
     * and contains a "0" or has a datatype of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * and contains  a 0, a value of {@code false} is returned.  If the designated column has a datatype
     * of CHAR or VARCHAR
     * and contains a "1" or has a datatype of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * and contains  a 1, a value of {@code true} is returned.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code false}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        return false;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code byte} in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public byte getByte(String columnLabel) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code short} in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public short getShort(String columnLabel) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * an {@code int} in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public int getInt(String columnLabel) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code long} in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public long getLong(String columnLabel) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code float} in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public float getFloat(String columnLabel) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code double} in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code 0}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public double getDouble(String columnLabel) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code java.math.BigDecimal} in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param scale       the number of digits to the right of the decimal point
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs or this method is
     *                                         called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @deprecated Use {@code getBigDecimal(int columnIndex)}
     * or {@code getBigDecimal(String columnLabel)}
     */
    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code byte} array in the Java programming language.
     * The bytes represent the raw values returned by the driver.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        return new byte[0];
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code java.sql.Date} object in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public Date getDate(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code java.sql.Time} object in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value;
     * if the value is SQL {@code NULL},
     * the value returned is {@code null}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public Time getTime(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code java.sql.Timestamp} object in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a stream of
     * ASCII characters. The value can then be read in chunks from the
     * stream. This method is particularly
     * suitable for retrieving large {@code LONGVARCHAR} values.
     * The JDBC driver will
     * do any necessary conversion from the database format into ASCII.
     *
     * <P><B>Note:</B> All the data in the returned stream must be
     * read prior to getting the value of any other column. The next
     * call to a getter method implicitly closes the stream. Also, a
     * stream may return {@code 0} when the method {@code available}
     * is called whether there is data available or not.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a Java input stream that delivers the database column value
     * as a stream of one-byte ASCII characters.
     * If the value is SQL {@code NULL},
     * the value returned is {@code null}.
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a stream of two-byte
     * Unicode characters. The first byte is the high byte; the second
     * byte is the low byte.
     * <p>
     * The value can then be read in chunks from the
     * stream. This method is particularly
     * suitable for retrieving large {@code LONGVARCHAR} values.
     * The JDBC technology-enabled driver will
     * do any necessary conversion from the database format into Unicode.
     *
     * <P><B>Note:</B> All the data in the returned stream must be
     * read prior to getting the value of any other column. The next
     * call to a getter method implicitly closes the stream.
     * Also, a stream may return {@code 0} when the method
     * {@code InputStream.available} is called, whether there
     * is data available or not.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a Java input stream that delivers the database column value
     * as a stream of two-byte Unicode characters.
     * If the value is SQL {@code NULL}, the value returned
     * is {@code null}.
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs or this method is
     *                                         called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @deprecated use {@code getCharacterStream} instead
     */
    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a stream of uninterpreted
     * {@code byte}s.
     * The value can then be read in chunks from the
     * stream. This method is particularly
     * suitable for retrieving large {@code LONGVARBINARY}
     * values.
     *
     * <P><B>Note:</B> All the data in the returned stream must be
     * read prior to getting the value of any other column. The next
     * call to a getter method implicitly closes the stream. Also, a
     * stream may return {@code 0} when the method {@code available}
     * is called whether there is data available or not.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a Java input stream that delivers the database column value
     * as a stream of uninterpreted bytes;
     * if the value is SQL {@code NULL}, the result is {@code null}
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the first warning reported by calls on this
     * {@code ResultSet} object.
     * Subsequent warnings on this {@code ResultSet} object
     * will be chained to the {@code SQLWarning} object that
     * this method returns.
     *
     * <P>The warning chain is automatically cleared each time a new
     * row is read.  This method may not be called on a {@code ResultSet}
     * object that has been closed; doing so will cause an
     * {@code SQLException} to be thrown.
     * <p>
     * <B>Note:</B> This warning chain only covers warnings caused
     * by {@code ResultSet} methods.  Any warning caused by
     * {@code Statement} methods
     * (such as reading OUT parameters) will be chained on the
     * {@code Statement} object.
     *
     * @return the first {@code SQLWarning} object reported or
     * {@code null} if there are none
     * @throws SQLException if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    /**
     * Clears all warnings reported on this {@code ResultSet} object.
     * After this method is called, the method {@code getWarnings}
     * returns {@code null} until a new warning is
     * reported for this {@code ResultSet} object.
     *
     * @throws SQLException if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public void clearWarnings() throws SQLException {

    }

    /**
     * Retrieves the name of the SQL cursor used by this {@code ResultSet}
     * object.
     *
     * <P>In SQL, a result table is retrieved through a cursor that is
     * named. The current row of a result set can be updated or deleted
     * using a positioned update/delete statement that references the
     * cursor name. To insure that the cursor has the proper isolation
     * level to support update, the cursor's {@code SELECT} statement
     * should be of the form {@code SELECT FOR UPDATE}. If
     * {@code FOR UPDATE} is omitted, the positioned updates may fail.
     *
     * <P>The JDBC API supports this SQL feature by providing the name of the
     * SQL cursor used by a {@code ResultSet} object.
     * The current row of a {@code ResultSet} object
     * is also the current row of this SQL cursor.
     *
     * @return the SQL name for this {@code ResultSet} object's cursor
     * @throws SQLException                    if a database access error occurs or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     */
    @Override
    public String getCursorName() throws SQLException {
        return "";
    }


    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new M1ResultSetMetaData();
    }

    /**
     * <p>Gets the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * an {@code Object} in the Java programming language.
     *
     * <p>This method will return the value of the given column as a
     * Java object.  The type of the Java object will be the default
     * Java object type corresponding to the column's SQL type,
     * following the mapping for built-in types specified in the JDBC
     * specification. If the value is an SQL {@code NULL},
     * the driver returns a Java {@code null}.
     *
     * <p>This method may also be used to read database-specific
     * abstract data types.
     * <p>
     * In the JDBC 2.0 API, the behavior of method
     * {@code getObject} is extended to materialize
     * data of SQL user-defined types.
     * <p>
     * If {@code Connection.getTypeMap} does not throw a
     * {@code SQLFeatureNotSupportedException},
     * then when a column contains a structured or distinct value,
     * the behavior of this method is as
     * if it were a call to: {@code getObject(columnIndex,
     * this.getStatement().getConnection().getTypeMap())}.
     * <p>
     * If {@code Connection.getTypeMap} does throw a
     * {@code SQLFeatureNotSupportedException},
     * then structured values are not supported, and distinct values
     * are mapped to the default Java class as determined by the
     * underlying SQL type of the DISTINCT type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a {@code java.lang.Object} holding the column value
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public Object getObject(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * <p>Gets the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * an {@code Object} in the Java programming language.
     *
     * <p>This method will return the value of the given column as a
     * Java object.  The type of the Java object will be the default
     * Java object type corresponding to the column's SQL type,
     * following the mapping for built-in types specified in the JDBC
     * specification. If the value is an SQL {@code NULL},
     * the driver returns a Java {@code null}.
     * <p>
     * This method may also be used to read database-specific
     * abstract data types.
     * <p>
     * In the JDBC 2.0 API, the behavior of the method
     * {@code getObject} is extended to materialize
     * data of SQL user-defined types.  When a column contains
     * a structured or distinct value, the behavior of this method is as
     * if it were a call to: {@code getObject(columnIndex,
     * this.getStatement().getConnection().getTypeMap())}.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a {@code java.lang.Object} holding the column value
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public Object getObject(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Maps the given {@code ResultSet} column label to its
     * {@code ResultSet} column index.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column index of the given column name
     * @throws SQLException if the {@code ResultSet} object
     *                      does not contain a column labeled {@code columnLabel}, a database access error occurs
     *                      or this method is called on a closed result set
     */
    @Override
    public int findColumn(String columnLabel) throws SQLException {
        return 0;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a
     * {@code java.io.Reader} object.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a {@code java.io.Reader} object that contains the column
     * value; if the value is SQL {@code NULL}, the value returned is
     * {@code null} in the Java programming language.
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     * @since 1.2
     */
    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a
     * {@code java.io.Reader} object.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a {@code java.io.Reader} object that contains the column
     * value; if the value is SQL {@code NULL}, the value returned is
     * {@code null} in the Java programming language
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     * @since 1.2
     */
    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a
     * {@code java.math.BigDecimal} with full precision.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value (full precision);
     * if the value is SQL {@code NULL}, the value returned is
     * {@code null} in the Java programming language.
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     * @since 1.2
     */
    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a
     * {@code java.math.BigDecimal} with full precision.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value (full precision);
     * if the value is SQL {@code NULL}, the value returned is
     * {@code null} in the Java programming language.
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs or this method is
     *                      called on a closed result set
     * @since 1.2
     */
    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves whether the cursor is before the first row in
     * this {@code ResultSet} object.
     * <p>
     * <strong>Note:</strong>Support for the {@code isBeforeFirst} method
     * is optional for {@code ResultSet}s with a result
     * set type of {@code TYPE_FORWARD_ONLY}
     *
     * @return {@code true} if the cursor is before the first row;
     * {@code false} if the cursor is at any other position or the
     * result set contains no rows
     * @throws SQLException                    if a database access error occurs or this method is
     *                                         called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public boolean isBeforeFirst() throws SQLException {
        return false;
    }

    /**
     * Retrieves whether the cursor is after the last row in
     * this {@code ResultSet} object.
     * <p>
     * <strong>Note:</strong>Support for the {@code isAfterLast} method
     * is optional for {@code ResultSet}s with a result
     * set type of {@code TYPE_FORWARD_ONLY}
     *
     * @return {@code true} if the cursor is after the last row;
     * {@code false} if the cursor is at any other position or the
     * result set contains no rows
     * @throws SQLException                    if a database access error occurs or this method is
     *                                         called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public boolean isAfterLast() throws SQLException {
        return false;
    }

    /**
     * Retrieves whether the cursor is on the first row of
     * this {@code ResultSet} object.
     * <p>
     * <strong>Note:</strong>Support for the {@code isFirst} method
     * is optional for {@code ResultSet}s with a result
     * set type of {@code TYPE_FORWARD_ONLY}
     *
     * @return {@code true} if the cursor is on the first row;
     * {@code false} otherwise
     * @throws SQLException                    if a database access error occurs or this method is
     *                                         called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public boolean isFirst() throws SQLException {
        return false;
    }

    /**
     * Retrieves whether the cursor is on the last row of
     * this {@code ResultSet} object.
     * <strong>Note:</strong> Calling the method {@code isLast} may be expensive
     * because the JDBC driver
     * might need to fetch ahead one row in order to determine
     * whether the current row is the last row in the result set.
     * <p>
     * <strong>Note:</strong> Support for the {@code isLast} method
     * is optional for {@code ResultSet}s with a result
     * set type of {@code TYPE_FORWARD_ONLY}
     *
     * @return {@code true} if the cursor is on the last row;
     * {@code false} otherwise
     * @throws SQLException                    if a database access error occurs or this method is
     *                                         called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public boolean isLast() throws SQLException {
        return false;
    }

    /**
     * Moves the cursor to the front of
     * this {@code ResultSet} object, just before the
     * first row. This method has no effect if the result set contains no rows.
     *
     * @throws SQLException                    if a database access error
     *                                         occurs; this method is called on a closed result set or the
     *                                         result set type is {@code TYPE_FORWARD_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void beforeFirst() throws SQLException {

    }

    /**
     * Moves the cursor to the end of
     * this {@code ResultSet} object, just after the
     * last row. This method has no effect if the result set contains no rows.
     *
     * @throws SQLException                    if a database access error
     *                                         occurs; this method is called on a closed result set
     *                                         or the result set type is {@code TYPE_FORWARD_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void afterLast() throws SQLException {

    }

    /**
     * Moves the cursor to the first row in
     * this {@code ResultSet} object.
     *
     * @return {@code true} if the cursor is on a valid row;
     * {@code false} if there are no rows in the result set
     * @throws SQLException                    if a database access error
     *                                         occurs; this method is called on a closed result set
     *                                         or the result set type is {@code TYPE_FORWARD_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public boolean first() throws SQLException {
        return false;
    }

    /**
     * Moves the cursor to the last row in
     * this {@code ResultSet} object.
     *
     * @return {@code true} if the cursor is on a valid row;
     * {@code false} if there are no rows in the result set
     * @throws SQLException                    if a database access error
     *                                         occurs; this method is called on a closed result set
     *                                         or the result set type is {@code TYPE_FORWARD_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public boolean last() throws SQLException {
        return false;
    }

    /**
     * Retrieves the current row number.  The first row is number 1, the
     * second number 2, and so on.
     * <p>
     * <strong>Note:</strong>Support for the {@code getRow} method
     * is optional for {@code ResultSet}s with a result
     * set type of {@code TYPE_FORWARD_ONLY}
     *
     * @return the current row number; {@code 0} if there is no current row
     * @throws SQLException                    if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public int getRow() throws SQLException {
        return 0;
    }

    /**
     * Moves the cursor to the given row number in
     * this {@code ResultSet} object.
     *
     * <p>If the row number is positive, the cursor moves to
     * the given row number with respect to the
     * beginning of the result set.  The first row is row 1, the second
     * is row 2, and so on.
     *
     * <p>If the given row number is negative, the cursor moves to
     * an absolute row position with respect to
     * the end of the result set.  For example, calling the method
     * {@code absolute(-1)} positions the
     * cursor on the last row; calling the method {@code absolute(-2)}
     * moves the cursor to the next-to-last row, and so on.
     *
     * <p>If the row number specified is zero, the cursor is moved to
     * before the first row.
     *
     * <p>An attempt to position the cursor beyond the first/last row in
     * the result set leaves the cursor before the first row or after
     * the last row.
     *
     * <p><B>Note:</B> Calling {@code absolute(1)} is the same
     * as calling {@code first()}. Calling {@code absolute(-1)}
     * is the same as calling {@code last()}.
     *
     * @param row the number of the row to which the cursor should move.
     *            A value of zero indicates that the cursor will be positioned
     *            before the first row; a positive number indicates the row number
     *            counting from the beginning of the result set; a negative number
     *            indicates the row number counting from the end of the result set
     * @return {@code true} if the cursor is moved to a position in this
     * {@code ResultSet} object;
     * {@code false} if the cursor is before the first row or after the
     * last row
     * @throws SQLException                    if a database access error
     *                                         occurs; this method is called on a closed result set
     *                                         or the result set type is {@code TYPE_FORWARD_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public boolean absolute(int row) throws SQLException {
        return false;
    }

    /**
     * Moves the cursor a relative number of rows, either positive or negative.
     * Attempting to move beyond the first/last row in the
     * result set positions the cursor before/after the
     * the first/last row. Calling {@code relative(0)} is valid, but does
     * not change the cursor position.
     *
     * <p>Note: Calling the method {@code relative(1)}
     * is identical to calling the method {@code next()} and
     * calling the method {@code relative(-1)} is identical
     * to calling the method {@code previous()}.
     *
     * @param rows an {@code int} specifying the number of rows to
     *             move from the current row; a positive number moves the cursor
     *             forward; a negative number moves the cursor backward
     * @return {@code true} if the cursor is on a row;
     * {@code false} otherwise
     * @throws SQLException                    if a database access error occurs;  this method
     *                                         is called on a closed result set or the result set type is
     *                                         {@code TYPE_FORWARD_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public boolean relative(int rows) throws SQLException {
        return false;
    }

    /**
     * Moves the cursor to the previous row in this
     * {@code ResultSet} object.
     * <p>
     * When a call to the {@code previous} method returns {@code false},
     * the cursor is positioned before the first row.  Any invocation of a
     * {@code ResultSet} method which requires a current row will result in a
     * {@code SQLException} being thrown.
     * <p>
     * If an input stream is open for the current row, a call to the method
     * {@code previous} will implicitly close it.  A {@code ResultSet}
     * object's warning change is cleared when a new row is read.
     *
     * @return {@code true} if the cursor is now positioned on a valid row;
     * {@code false} if the cursor is positioned before the first row
     * @throws SQLException                    if a database access error
     *                                         occurs; this method is called on a closed result set
     *                                         or the result set type is {@code TYPE_FORWARD_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public boolean previous() throws SQLException {
        return false;
    }

    /**
     * Gives a hint as to the direction in which the rows in this
     * {@code ResultSet} object will be processed.
     * The initial value is determined by the
     * {@code Statement} object
     * that produced this {@code ResultSet} object.
     * The fetch direction may be changed at any time.
     *
     * @param direction an {@code int} specifying the suggested
     *                  fetch direction; one of {@code ResultSet.FETCH_FORWARD},
     *                  {@code ResultSet.FETCH_REVERSE}, or
     *                  {@code ResultSet.FETCH_UNKNOWN}
     * @throws SQLException if a database access error occurs; this
     *                      method is called on a closed result set or
     *                      the result set type is {@code TYPE_FORWARD_ONLY} and the fetch
     *                      direction is not {@code FETCH_FORWARD}
     * @see Statement#setFetchDirection
     * @see #getFetchDirection
     * @since 1.2
     */
    @Override
    public void setFetchDirection(int direction) throws SQLException {

    }

    /**
     * Retrieves the fetch direction for this
     * {@code ResultSet} object.
     *
     * @return the current fetch direction for this {@code ResultSet} object
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed result set
     * @see #setFetchDirection
     * @since 1.2
     */
    @Override
    public int getFetchDirection() throws SQLException {
        return 0;
    }

    /**
     * Gives the JDBC driver a hint as to the number of rows that should
     * be fetched from the database when more rows are needed for this
     * {@code ResultSet} object.
     * If the fetch size specified is zero, the JDBC driver
     * ignores the value and is free to make its own best guess as to what
     * the fetch size should be.  The default value is set by the
     * {@code Statement} object
     * that created the result set.  The fetch size may be changed at any time.
     *
     * @param rows the number of rows to fetch
     * @throws SQLException if a database access error occurs; this method
     *                      is called on a closed result set or the
     *                      condition {@code rows >= 0} is not satisfied
     * @see #getFetchSize
     * @since 1.2
     */
    @Override
    public void setFetchSize(int rows) throws SQLException {

    }

    /**
     * Retrieves the fetch size for this
     * {@code ResultSet} object.
     *
     * @return the current fetch size for this {@code ResultSet} object
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed result set
     * @see #setFetchSize
     * @since 1.2
     */
    @Override
    public int getFetchSize() throws SQLException {
        return 0;
    }

    /**
     * Retrieves the type of this {@code ResultSet} object.
     * The type is determined by the {@code Statement} object
     * that created the result set.
     *
     * @return {@code ResultSet.TYPE_FORWARD_ONLY},
     * {@code ResultSet.TYPE_SCROLL_INSENSITIVE},
     * or {@code ResultSet.TYPE_SCROLL_SENSITIVE}
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.2
     */
    @Override
    public int getType() throws SQLException {
        return 0;
    }

    /**
     * Retrieves the concurrency mode of this {@code ResultSet} object.
     * The concurrency used is determined by the
     * {@code Statement} object that created the result set.
     *
     * @return the concurrency type, either
     * {@code ResultSet.CONCUR_READ_ONLY}
     * or {@code ResultSet.CONCUR_UPDATABLE}
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.2
     */
    @Override
    public int getConcurrency() throws SQLException {
        return 0;
    }

    /**
     * Retrieves whether the current row has been updated.  The value returned
     * depends on whether or not the result set can detect updates.
     * <p>
     * <strong>Note:</strong> Support for the {@code rowUpdated} method is optional with a result set
     * concurrency of {@code CONCUR_READ_ONLY}
     *
     * @return {@code true} if the current row is detected to
     * have been visibly updated by the owner or another; {@code false} otherwise
     * @throws SQLException                    if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @see DatabaseMetaData#updatesAreDetected
     * @since 1.2
     */
    @Override
    public boolean rowUpdated() throws SQLException {
        return false;
    }

    /**
     * Retrieves whether the current row has had an insertion.
     * The value returned depends on whether or not this
     * {@code ResultSet} object can detect visible inserts.
     * <p>
     * <strong>Note:</strong> Support for the {@code rowInserted} method is optional with a result set
     * concurrency of {@code CONCUR_READ_ONLY}
     *
     * @return {@code true} if the current row is detected to
     * have been inserted; {@code false} otherwise
     * @throws SQLException                    if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @see DatabaseMetaData#insertsAreDetected
     * @since 1.2
     */
    @Override
    public boolean rowInserted() throws SQLException {
        return false;
    }

    /**
     * Retrieves whether a row has been deleted.  A deleted row may leave
     * a visible "hole" in a result set.  This method can be used to
     * detect holes in a result set.  The value returned depends on whether
     * or not this {@code ResultSet} object can detect deletions.
     * <p>
     * <strong>Note:</strong> Support for the {@code rowDeleted} method is optional with a result set
     * concurrency of {@code CONCUR_READ_ONLY}
     *
     * @return {@code true} if the current row is detected to
     * have been deleted by the owner or another; {@code false} otherwise
     * @throws SQLException                    if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @see DatabaseMetaData#deletesAreDetected
     * @since 1.2
     */
    @Override
    public boolean rowDeleted() throws SQLException {
        return false;
    }

    /**
     * Updates the designated column with a {@code null} value.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow}
     * or {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateNull(int columnIndex) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code boolean} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code byte} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code short} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {

    }

    /**
     * Updates the designated column with an {@code int} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code long} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code float} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code double} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.math.BigDecimal}
     * value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code String} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateString(int columnIndex, String x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code byte} array value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Date} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Time} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Timestamp}
     * value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {

    }

    /**
     * Updates the designated column with an ascii stream value, which will have
     * the specified number of bytes.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {

    }

    /**
     * Updates the designated column with a binary stream value, which will have
     * the specified number of bytes.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {

    }

    /**
     * Updates the designated column with a character stream value, which will have
     * the specified number of bytes.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {

    }

    /**
     * Updates the designated column with an {@code Object} value.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     * <p>
     * If the second argument is an {@code InputStream} then the stream must contain
     * the number of bytes specified by scaleOrLength.  If the second argument is a
     * {@code Reader} then the reader must contain the number of characters specified
     * by scaleOrLength. If these conditions are not true the driver will generate a
     * {@code SQLException} when the statement is executed.
     *
     * @param columnIndex   the first column is 1, the second is 2, ...
     * @param x             the new column value
     * @param scaleOrLength for an object of {@code java.math.BigDecimal} ,
     *                      this is the number of digits after the decimal point. For
     *                      Java Object types {@code InputStream} and {@code Reader},
     *                      this is the length
     *                      of the data in the stream or reader.  For all other types,
     *                      this value will be ignored.
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {

    }

    /**
     * Updates the designated column with an {@code Object} value.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code null} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateNull(String columnLabel) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code boolean} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code byte} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code short} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {

    }

    /**
     * Updates the designated column with an {@code int} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code long} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code float} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code double} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.BigDecimal}
     * value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code String} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateString(String columnLabel, String x) throws SQLException {

    }

    /**
     * Updates the designated column with a byte array value.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow}
     * or {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Date} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Time} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Timestamp}
     * value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {

    }

    /**
     * Updates the designated column with an ascii stream value, which will have
     * the specified number of bytes.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {

    }

    /**
     * Updates the designated column with a binary stream value, which will have
     * the specified number of bytes.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {

    }

    /**
     * Updates the designated column with a character stream value, which will have
     * the specified number of bytes.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param reader      the {@code java.io.Reader} object containing
     *                    the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {

    }

    /**
     * Updates the designated column with an {@code Object} value.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     * <p>
     * If the second argument is an {@code InputStream} then the stream must contain
     * the number of bytes specified by scaleOrLength.  If the second argument is a
     * {@code Reader} then the reader must contain the number of characters specified
     * by scaleOrLength. If these conditions are not true the driver will generate a
     * {@code SQLException} when the statement is executed.
     *
     * @param columnLabel   the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x             the new column value
     * @param scaleOrLength for an object of {@code java.math.BigDecimal} ,
     *                      this is the number of digits after the decimal point. For
     *                      Java Object types {@code InputStream} and {@code Reader},
     *                      this is the length
     *                      of the data in the stream or reader.  For all other types,
     *                      this value will be ignored.
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {

    }

    /**
     * Updates the designated column with an {@code Object} value.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {

    }

    /**
     * Inserts the contents of the insert row into this
     * {@code ResultSet} object and into the database.
     * The cursor must be on the insert row when this method is called.
     *
     * @throws SQLException                    if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY},
     *                                         this method is called on a closed result set,
     *                                         if this method is called when the cursor is not on the insert row,
     *                                         or if not all of non-nullable columns in
     *                                         the insert row have been given a non-null value
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void insertRow() throws SQLException {

    }

    /**
     * Updates the underlying database with the new contents of the
     * current row of this {@code ResultSet} object.
     * This method cannot be called when the cursor is on the insert row.
     *
     * @throws SQLException                    if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY};
     *                                         this method is called on a closed result set or
     *                                         if this method is called when the cursor is on the insert row
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void updateRow() throws SQLException {

    }

    /**
     * Deletes the current row from this {@code ResultSet} object
     * and from the underlying database.  This method cannot be called when
     * the cursor is on the insert row.
     *
     * @throws SQLException                    if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY};
     *                                         this method is called on a closed result set
     *                                         or if this method is called when the cursor is on the insert row
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void deleteRow() throws SQLException {

    }

    /**
     * Refreshes the current row with its most recent value in
     * the database.  This method cannot be called when
     * the cursor is on the insert row.
     *
     * <P>The {@code refreshRow} method provides a way for an
     * application to
     * explicitly tell the JDBC driver to refetch a row(s) from the
     * database.  An application may want to call {@code refreshRow} when
     * caching or prefetching is being done by the JDBC driver to
     * fetch the latest value of a row from the database.  The JDBC driver
     * may actually refresh multiple rows at once if the fetch size is
     * greater than one.
     *
     * <P> All values are refetched subject to the transaction isolation
     * level and cursor sensitivity.  If {@code refreshRow} is called after
     * calling an updater method, but before calling
     * the method {@code updateRow}, then the
     * updates made to the row are lost.  Calling the method
     * {@code refreshRow} frequently will likely slow performance.
     *
     * @throws SQLException                    if a database access error
     *                                         occurs; this method is called on a closed result set;
     *                                         the result set type is {@code TYPE_FORWARD_ONLY} or if this
     *                                         method is called when the cursor is on the insert row
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method or this method is not supported for the specified result
     *                                         set type and result set concurrency.
     * @since 1.2
     */
    @Override
    public void refreshRow() throws SQLException {

    }

    /**
     * Cancels the updates made to the current row in this
     * {@code ResultSet} object.
     * This method may be called after calling an
     * updater method(s) and before calling
     * the method {@code updateRow} to roll back
     * the updates made to a row.  If no updates have been made or
     * {@code updateRow} has already been called, this method has no
     * effect.
     *
     * @throws SQLException                    if a database access error
     *                                         occurs; this method is called on a closed result set;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or if this method is called when the cursor is
     *                                         on the insert row
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void cancelRowUpdates() throws SQLException {

    }

    /**
     * Moves the cursor to the insert row.  The current cursor position is
     * remembered while the cursor is positioned on the insert row.
     * <p>
     * The insert row is a special row associated with an updatable
     * result set.  It is essentially a buffer where a new row may
     * be constructed by calling the updater methods prior to
     * inserting the row into the result set.
     * <p>
     * Only the updater, getter,
     * and {@code insertRow} methods may be
     * called when the cursor is on the insert row.  All of the columns in
     * a result set must be given a value each time this method is
     * called before calling {@code insertRow}.
     * An updater method must be called before a
     * getter method can be called on a column value.
     *
     * @throws SQLException                    if a database access error occurs; this
     *                                         method is called on a closed result set
     *                                         or the result set concurrency is {@code CONCUR_READ_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void moveToInsertRow() throws SQLException {

    }

    /**
     * Moves the cursor to the remembered cursor position, usually the
     * current row.  This method has no effect if the cursor is not on
     * the insert row.
     *
     * @throws SQLException                    if a database access error occurs; this
     *                                         method is called on a closed result set
     *                                         or the result set concurrency is {@code CONCUR_READ_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public void moveToCurrentRow() throws SQLException {

    }

    /**
     * Retrieves the {@code Statement} object that produced this
     * {@code ResultSet} object.
     * If the result set was generated some other way, such as by a
     * {@code DatabaseMetaData} method, this method  may return
     * {@code null}.
     *
     * @return the {@code Statement} object that produced
     * this {@code ResultSet} object or {@code null}
     * if the result set was produced some other way
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.2
     */
    @Override
    public Statement getStatement() throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as an {@code Object}
     * in the Java programming language.
     * If the value is an SQL {@code NULL},
     * the driver returns a Java {@code null}.
     * This method uses the given {@code Map} object
     * for the custom mapping of the
     * SQL structured or distinct type that is being retrieved.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param map         a {@code java.util.Map} object that contains the mapping
     *                    from SQL type names to classes in the Java programming language
     * @return an {@code Object} in the Java programming language
     * representing the SQL value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code Ref} object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a {@code Ref} object representing an SQL {@code REF}
     * value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code Blob} object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a {@code Blob} object representing the SQL
     * {@code BLOB} value in the specified column
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code Clob} object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a {@code Clob} object representing the SQL
     * {@code CLOB} value in the specified column
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as an {@code Array} object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return an {@code Array} object representing the SQL
     * {@code ARRAY} value in the specified column
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Array getArray(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as an {@code Object}
     * in the Java programming language.
     * If the value is an SQL {@code NULL},
     * the driver returns a Java {@code null}.
     * This method uses the specified {@code Map} object for
     * custom mapping if appropriate.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param map         a {@code java.util.Map} object that contains the mapping
     *                    from SQL type names to classes in the Java programming language
     * @return an {@code Object} representing the SQL value in the
     * specified column
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code Ref} object
     * in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a {@code Ref} object representing the SQL {@code REF}
     * value in the specified column
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code Blob} object
     * in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a {@code Blob} object representing the SQL {@code BLOB}
     * value in the specified column
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code Clob} object
     * in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a {@code Clob} object representing the SQL {@code CLOB}
     * value in the specified column
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as an {@code Array} object
     * in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return an {@code Array} object representing the SQL {@code ARRAY} value in
     * the specified column
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.2
     */
    @Override
    public Array getArray(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code java.sql.Date} object
     * in the Java programming language.
     * This method uses the given calendar to construct an appropriate millisecond
     * value for the date if the underlying database does not store
     * timezone information.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param cal         the {@code java.util.Calendar} object
     *                    to use in constructing the date
     * @return the column value as a {@code java.sql.Date} object;
     * if the value is SQL {@code NULL},
     * the value returned is {@code null} in the Java programming language
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.2
     */
    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code java.sql.Date} object
     * in the Java programming language.
     * This method uses the given calendar to construct an appropriate millisecond
     * value for the date if the underlying database does not store
     * timezone information.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param cal         the {@code java.util.Calendar} object
     *                    to use in constructing the date
     * @return the column value as a {@code java.sql.Date} object;
     * if the value is SQL {@code NULL},
     * the value returned is {@code null} in the Java programming language
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.2
     */
    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code java.sql.Time} object
     * in the Java programming language.
     * This method uses the given calendar to construct an appropriate millisecond
     * value for the time if the underlying database does not store
     * timezone information.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param cal         the {@code java.util.Calendar} object
     *                    to use in constructing the time
     * @return the column value as a {@code java.sql.Time} object;
     * if the value is SQL {@code NULL},
     * the value returned is {@code null} in the Java programming language
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.2
     */
    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code java.sql.Time} object
     * in the Java programming language.
     * This method uses the given calendar to construct an appropriate millisecond
     * value for the time if the underlying database does not store
     * timezone information.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param cal         the {@code java.util.Calendar} object
     *                    to use in constructing the time
     * @return the column value as a {@code java.sql.Time} object;
     * if the value is SQL {@code NULL},
     * the value returned is {@code null} in the Java programming language
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.2
     */
    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code java.sql.Timestamp} object
     * in the Java programming language.
     * This method uses the given calendar to construct an appropriate millisecond
     * value for the timestamp if the underlying database does not store
     * timezone information.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param cal         the {@code java.util.Calendar} object
     *                    to use in constructing the timestamp
     * @return the column value as a {@code java.sql.Timestamp} object;
     * if the value is SQL {@code NULL},
     * the value returned is {@code null} in the Java programming language
     * @throws SQLException if the columnIndex is not valid;
     *                      if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.2
     */
    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code java.sql.Timestamp} object
     * in the Java programming language.
     * This method uses the given calendar to construct an appropriate millisecond
     * value for the timestamp if the underlying database does not store
     * timezone information.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param cal         the {@code java.util.Calendar} object
     *                    to use in constructing the date
     * @return the column value as a {@code java.sql.Timestamp} object;
     * if the value is SQL {@code NULL},
     * the value returned is {@code null} in the Java programming language
     * @throws SQLException if the columnLabel is not valid or
     *                      if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.2
     */
    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code java.net.URL}
     * object in the Java programming language.
     *
     * @param columnIndex the index of the column 1 is the first, 2 is the second,...
     * @return the column value as a {@code java.net.URL} object;
     * if the value is SQL {@code NULL},
     * the value returned is {@code null} in the Java programming language
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs; this method
     *                                         is called on a closed result set or if a URL is malformed
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public URL getURL(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code java.net.URL}
     * object in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value as a {@code java.net.URL} object;
     * if the value is SQL {@code NULL},
     * the value returned is {@code null} in the Java programming language
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs; this method
     *                                         is called on a closed result set or if a URL is malformed
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public URL getURL(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Updates the designated column with a {@code java.sql.Ref} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Ref} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Blob} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Blob} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Clob} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Clob} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Array} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.Array} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.4
     */
    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {

    }

    /**
     * Retrieves the value of the designated column in the current row of this
     * {@code ResultSet} object as a {@code java.sql.RowId} object in the Java
     * programming language.
     *
     * @param columnIndex the first column is 1, the second 2, ...
     * @return the column value; if the value is a SQL {@code NULL} the
     * value returned is {@code null}
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row of this
     * {@code ResultSet} object as a {@code java.sql.RowId} object in the Java
     * programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value ; if the value is a SQL {@code NULL} the
     * value returned is {@code null}
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Updates the designated column with a {@code RowId} value. The updater
     * methods are used to update column values in the current row or the insert
     * row. The updater methods do not update the underlying database; instead
     * the {@code updateRow} or {@code insertRow} methods are called
     * to update the database.
     *
     * @param columnIndex the first column is 1, the second 2, ...
     * @param x           the column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code RowId} value. The updater
     * methods are used to update column values in the current row or the insert
     * row. The updater methods do not update the underlying database; instead
     * the {@code updateRow} or {@code insertRow} methods are called
     * to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {

    }

    /**
     * Retrieves the holdability of this {@code ResultSet} object
     *
     * @return either {@code ResultSet.HOLD_CURSORS_OVER_COMMIT} or {@code ResultSet.CLOSE_CURSORS_AT_COMMIT}
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed result set
     * @since 1.6
     */
    @Override
    public int getHoldability() throws SQLException {
        return 0;
    }

    /**
     * Retrieves whether this {@code ResultSet} object has been closed. A {@code ResultSet} is closed if the
     * method close has been called on it, or if it is automatically closed.
     *
     * @return true if this {@code ResultSet} object is closed; false if it is still open
     * @throws SQLException if a database access error occurs
     * @since 1.6
     */
    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    /**
     * Updates the designated column with a {@code String} value.
     * It is intended for use when updating {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second 2, ...
     * @param nString     the value for the column to be updated
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or if a database access error occurs
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code String} value.
     * It is intended for use when updating {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param nString     the value for the column to be updated
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or if a database access error occurs
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.NClob} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second 2, ...
     * @param nClob       the value for the column to be updated
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set;
     *                                         if a database access error occurs or
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.NClob} value.
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param nClob       the value for the column to be updated
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set;
     *                                         if a database access error occurs or
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {

    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code NClob} object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a {@code NClob} object representing the SQL
     * {@code NCLOB} value in the specified column
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set
     *                                         or if a database access error occurs
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code NClob} object
     * in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a {@code NClob} object representing the SQL {@code NCLOB}
     * value in the specified column
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set
     *                                         or if a database access error occurs
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in  the current row of
     * this {@code ResultSet} as a
     * {@code java.sql.SQLXML} object in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a {@code SQLXML} object that maps an {@code SQL XML} value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in  the current row of
     * this {@code ResultSet} as a
     * {@code java.sql.SQLXML} object in the Java programming language.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a {@code SQLXML} object that maps an {@code SQL XML} value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Updates the designated column with a {@code java.sql.SQLXML} value.
     * The updater
     * methods are used to update column values in the current row or the insert
     * row. The updater methods do not update the underlying database; instead
     * the {@code updateRow} or {@code insertRow} methods are called
     * to update the database.
     *
     * @param columnIndex the first column is 1, the second 2, ...
     * @param xmlObject   the value for the column to be updated
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs; this method
     *                                         is called on a closed result set;
     *                                         the {@code java.xml.transform.Result},
     *                                         {@code Writer} or {@code OutputStream} has not been closed
     *                                         for the {@code SQLXML} object;
     *                                         if there is an error processing the XML value or
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}.  The {@code getCause} method
     *                                         of the exception may provide a more detailed exception, for example, if the
     *                                         stream does not contain valid XML.
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {

    }

    /**
     * Updates the designated column with a {@code java.sql.SQLXML} value.
     * The updater
     * methods are used to update column values in the current row or the insert
     * row. The updater methods do not update the underlying database; instead
     * the {@code updateRow} or {@code insertRow} methods are called
     * to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param xmlObject   the column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs; this method
     *                                         is called on a closed result set;
     *                                         the {@code java.xml.transform.Result},
     *                                         {@code Writer} or {@code OutputStream} has not been closed
     *                                         for the {@code SQLXML} object;
     *                                         if there is an error processing the XML value or
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}.  The {@code getCause} method
     *                                         of the exception may provide a more detailed exception, for example, if the
     *                                         stream does not contain valid XML.
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {

    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code String} in the Java programming language.
     * It is intended for use when
     * accessing  {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public String getNString(int columnIndex) throws SQLException {
        return "";
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as
     * a {@code String} in the Java programming language.
     * It is intended for use when
     * accessing  {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return the column value; if the value is SQL {@code NULL}, the
     * value returned is {@code null}
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public String getNString(String columnLabel) throws SQLException {
        return "";
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a
     * {@code java.io.Reader} object.
     * It is intended for use when
     * accessing  {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a {@code java.io.Reader} object that contains the column
     * value; if the value is SQL {@code NULL}, the value returned is
     * {@code null} in the Java programming language.
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        return null;
    }

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a
     * {@code java.io.Reader} object.
     * It is intended for use when
     * accessing  {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @return a {@code java.io.Reader} object that contains the column
     * value; if the value is SQL {@code NULL}, the value returned is
     * {@code null} in the Java programming language
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        return null;
    }

    /**
     * Updates the designated column with a character stream value, which will have
     * the specified number of bytes.   The
     * driver does the necessary conversion from Java character format to
     * the national character set in the database.
     * It is intended for use when
     * updating  {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY} or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {

    }

    /**
     * Updates the designated column with a character stream value, which will have
     * the specified number of bytes.  The
     * driver does the necessary conversion from Java character format to
     * the national character set in the database.
     * It is intended for use when
     * updating  {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param reader      the {@code java.io.Reader} object containing
     *                    the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY} or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {

    }

    /**
     * Updates the designated column with an ascii stream value, which will have
     * the specified number of bytes.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {

    }

    /**
     * Updates the designated column with a binary stream value, which will have
     * the specified number of bytes.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {

    }

    /**
     * Updates the designated column with a character stream value, which will have
     * the specified number of bytes.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {

    }

    /**
     * Updates the designated column with an ascii stream value, which will have
     * the specified number of bytes.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {

    }

    /**
     * Updates the designated column with a binary stream value, which will have
     * the specified number of bytes.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {

    }

    /**
     * Updates the designated column with a character stream value, which will have
     * the specified number of bytes.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param reader      the {@code java.io.Reader} object containing
     *                    the new column value
     * @param length      the length of the stream
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {

    }

    /**
     * Updates the designated column using the given input stream, which
     * will have the specified number of bytes.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param inputStream An object that contains the data to set the parameter
     *                    value to.
     * @param length      the number of bytes in the parameter data.
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {

    }

    /**
     * Updates the designated column using the given input stream, which
     * will have the specified number of bytes.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param inputStream An object that contains the data to set the parameter
     *                    value to.
     * @param length      the number of bytes in the parameter data.
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {

    }

    /**
     * Updates the designated column using the given {@code Reader}
     * object, which is the given number of characters long.
     * When a very large UNICODE value is input to a {@code LONGVARCHAR}
     * parameter, it may be more practical to send it via a
     * {@code java.io.Reader} object. The JDBC driver will
     * do any necessary conversion from UNICODE to the database char format.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param reader      An object that contains the data to set the parameter value to.
     * @param length      the number of characters in the parameter data.
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {

    }

    /**
     * Updates the designated column using the given {@code Reader}
     * object, which is the given number of characters long.
     * When a very large UNICODE value is input to a {@code LONGVARCHAR}
     * parameter, it may be more practical to send it via a
     * {@code java.io.Reader} object.  The JDBC driver will
     * do any necessary conversion from UNICODE to the database char format.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param reader      An object that contains the data to set the parameter value to.
     * @param length      the number of characters in the parameter data.
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {

    }

    /**
     * Updates the designated column using the given {@code Reader}
     * object, which is the given number of characters long.
     * When a very large UNICODE value is input to a {@code LONGVARCHAR}
     * parameter, it may be more practical to send it via a
     * {@code java.io.Reader} object. The JDBC driver will
     * do any necessary conversion from UNICODE to the database char format.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnIndex the first column is 1, the second 2, ...
     * @param reader      An object that contains the data to set the parameter value to.
     * @param length      the number of characters in the parameter data.
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set,
     *                                         if a database access error occurs or
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {

    }

    /**
     * Updates the designated column using the given {@code Reader}
     * object, which is the given number of characters long.
     * When a very large UNICODE value is input to a {@code LONGVARCHAR}
     * parameter, it may be more practical to send it via a
     * {@code java.io.Reader} object. The JDBC driver will
     * do any necessary conversion from UNICODE to the database char format.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param reader      An object that contains the data to set the parameter value to.
     * @param length      the number of characters in the parameter data.
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set;
     *                                         if a database access error occurs or
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {

    }

    /**
     * Updates the designated column with a character stream value.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.  The
     * driver does the necessary conversion from Java character format to
     * the national character set in the database.
     * It is intended for use when
     * updating  {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateNCharacterStream} which takes a length parameter.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY} or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {

    }

    /**
     * Updates the designated column with a character stream value.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.  The
     * driver does the necessary conversion from Java character format to
     * the national character set in the database.
     * It is intended for use when
     * updating  {@code NCHAR},{@code NVARCHAR}
     * and {@code LONGNVARCHAR} columns.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateNCharacterStream} which takes a length parameter.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param reader      the {@code java.io.Reader} object containing
     *                    the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY} or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {

    }

    /**
     * Updates the designated column with an ascii stream value.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateAsciiStream} which takes a length parameter.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {

    }

    /**
     * Updates the designated column with a binary stream value.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateBinaryStream} which takes a length parameter.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {

    }

    /**
     * Updates the designated column with a character stream value.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateCharacterStream} which takes a length parameter.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param x           the new column value
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {

    }

    /**
     * Updates the designated column with an ascii stream value.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateAsciiStream} which takes a length parameter.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {

    }

    /**
     * Updates the designated column with a binary stream value.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateBinaryStream} which takes a length parameter.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param x           the new column value
     * @throws SQLException                    if the columnLabel is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {

    }

    /**
     * Updates the designated column with a character stream value.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateCharacterStream} which takes a length parameter.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param reader      the {@code java.io.Reader} object containing
     *                    the new column value
     * @throws SQLException                    if the columnLabel is not valid; if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {

    }

    /**
     * Updates the designated column using the given input stream. The data will be read from the stream
     * as needed until end-of-stream is reached.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateBlob} which takes a length parameter.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param inputStream An object that contains the data to set the parameter
     *                    value to.
     * @throws SQLException                    if the columnIndex is not valid; if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {

    }

    /**
     * Updates the designated column using the given input stream. The data will be read from the stream
     * as needed until end-of-stream is reached.
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateBlob} which takes a length parameter.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param inputStream An object that contains the data to set the parameter
     *                    value to.
     * @throws SQLException                    if the columnLabel is not valid; if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {

    }

    /**
     * Updates the designated column using the given {@code Reader}
     * object.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.  The JDBC driver will
     * do any necessary conversion from UNICODE to the database char format.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateClob} which takes a length parameter.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param reader      An object that contains the data to set the parameter value to.
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {

    }

    /**
     * Updates the designated column using the given {@code Reader}
     * object.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.  The JDBC driver will
     * do any necessary conversion from UNICODE to the database char format.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateClob} which takes a length parameter.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param reader      An object that contains the data to set the parameter value to.
     * @throws SQLException                    if the columnLabel is not valid; if a database access error occurs;
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     *                                         or this method is called on a closed result set
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {

    }

    /**
     * Updates the designated column using the given {@code Reader}
     * <p>
     * The data will be read from the stream
     * as needed until end-of-stream is reached.  The JDBC driver will
     * do any necessary conversion from UNICODE to the database char format.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateNClob} which takes a length parameter.
     *
     * @param columnIndex the first column is 1, the second 2, ...
     * @param reader      An object that contains the data to set the parameter value to.
     * @throws SQLException                    if the columnIndex is not valid;
     *                                         if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set,
     *                                         if a database access error occurs or
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {

    }

    /**
     * Updates the designated column using the given {@code Reader}
     * object.
     * The data will be read from the stream
     * as needed until end-of-stream is reached.  The JDBC driver will
     * do any necessary conversion from UNICODE to the database char format.
     *
     * <p>
     * The updater methods are used to update column values in the
     * current row or the insert row.  The updater methods do not
     * update the underlying database; instead the {@code updateRow} or
     * {@code insertRow} methods are called to update the database.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentation to determine if
     * it might be more efficient to use a version of
     * {@code updateNClob} which takes a length parameter.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS clause was not specified, then the label is the name of the column
     * @param reader      An object that contains the data to set the parameter value to.
     * @throws SQLException                    if the columnLabel is not valid; if the driver does not support national
     *                                         character sets;  if the driver can detect that a data conversion
     *                                         error could occur; this method is called on a closed result set;
     *                                         if a database access error occurs or
     *                                         the result set concurrency is {@code CONCUR_READ_ONLY}
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.6
     */
    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {

    }

    /**
     * <p>Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object and will convert from the
     * SQL type of the column to the requested Java data type, if the
     * conversion is supported. If the conversion is not
     * supported  or null is specified for the type, a
     * {@code SQLException} is thrown.
     * <p>
     * At a minimum, an implementation must support the conversions defined in
     * Appendix B, Table B-3 and conversion of appropriate user defined SQL
     * types to a Java type which implements {@code SQLData}, or {@code Struct}.
     * Additional conversions may be supported and are vendor defined.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @param type        Class representing the Java data type to convert the designated
     *                    column to.
     * @return an instance of {@code type} holding the column value
     * @throws SQLException                    if conversion is not supported, type is null or
     *                                         another error occurs. The getCause() method of the
     *                                         exception may provide a more detailed exception, for example, if
     *                                         a conversion error occurs
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.7
     */
    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        return null;
    }

    /**
     * <p>Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object and will convert from the
     * SQL type of the column to the requested Java data type, if the
     * conversion is supported. If the conversion is not
     * supported  or null is specified for the type, a
     * {@code SQLException} is thrown.
     * <p>
     * At a minimum, an implementation must support the conversions defined in
     * Appendix B, Table B-3 and conversion of appropriate user defined SQL
     * types to a Java type which implements {@code SQLData}, or {@code Struct}.
     * Additional conversions may be supported and are vendor defined.
     *
     * @param columnLabel the label for the column specified with the SQL AS clause.
     *                    If the SQL AS clause was not specified, then the label is the name
     *                    of the column
     * @param type        Class representing the Java data type to convert the designated
     *                    column to.
     * @return an instance of {@code type} holding the column value
     * @throws SQLException                    if conversion is not supported, type is null or
     *                                         another error occurs. The getCause() method of the
     *                                         exception may provide a more detailed exception, for example, if
     *                                         a conversion error occurs
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not support
     *                                         this method
     * @since 1.7
     */
    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        return null;
    }

    /**
     * Returns an object that implements the given interface to allow access to
     * non-standard methods, or standard methods not exposed by the proxy.
     * <p>
     * If the receiver implements the interface then the result is the receiver
     * or a proxy for the receiver. If the receiver is a wrapper
     * and the wrapped object implements the interface then the result is the
     * wrapped object or a proxy for the wrapped object. Otherwise return the
     * the result of calling {@code unwrap} recursively on the wrapped object
     * or a proxy for that result. If the receiver is not a
     * wrapper and does not implement the interface, then an {@code SQLException} is thrown.
     *
     * @param iface A Class defining an interface that the result must implement.
     * @return an object that implements the interface. May be a proxy for the actual implementing object.
     * @throws SQLException If no object found that implements the interface
     * @since 1.6
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    /**
     * Returns true if this either implements the interface argument or is directly or indirectly a wrapper
     * for an object that does. Returns false otherwise. If this implements the interface then return true,
     * else if this is a wrapper then return the result of recursively calling {@code isWrapperFor} on the wrapped
     * object. If this does not implement the interface and is not a wrapper, return false.
     * This method should be implemented as a low-cost operation compared to {@code unwrap} so that
     * callers can use this method to avoid expensive {@code unwrap} calls that may fail. If this method
     * returns true then calling {@code unwrap} with the same argument should succeed.
     *
     * @param iface a Class defining an interface.
     * @return true if this implements the interface or directly or indirectly wraps an object that does.
     * @throws SQLException if an error occurs while determining whether this is a wrapper
     *                      for an object with the given interface.
     * @since 1.6
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    private class M1ResultSetMetaData implements ResultSetMetaData {
        @Override
        public int getColumnCount() throws SQLException {
            return columnNames.size();
        }

        /**
         * Indicates whether the designated column is automatically numbered.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return {@code true} if so; {@code false} otherwise
         * @throws SQLException if a database access error occurs
         */
        @Override
        public boolean isAutoIncrement(int column) throws SQLException {
            return false;
        }

        /**
         * Indicates whether a column's case matters.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return {@code true} if so; {@code false} otherwise
         * @throws SQLException if a database access error occurs
         */
        @Override
        public boolean isCaseSensitive(int column) throws SQLException {
            return false;
        }

        /**
         * Indicates whether the designated column can be used in a where clause.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return {@code true} if so; {@code false} otherwise
         * @throws SQLException if a database access error occurs
         */
        @Override
        public boolean isSearchable(int column) throws SQLException {
            return false;
        }

        /**
         * Indicates whether the designated column is a cash value.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return {@code true} if so; {@code false} otherwise
         * @throws SQLException if a database access error occurs
         */
        @Override
        public boolean isCurrency(int column) throws SQLException {
            return false;
        }

        /**
         * Indicates the nullability of values in the designated column.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return the nullability status of the given column; one of {@code columnNoNulls},
         * {@code columnNullable} or {@code columnNullableUnknown}
         * @throws SQLException if a database access error occurs
         */
        @Override
        public int isNullable(int column) throws SQLException {
            return 0;
        }

        /**
         * Indicates whether values in the designated column are signed numbers.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return {@code true} if so; {@code false} otherwise
         * @throws SQLException if a database access error occurs
         */
        @Override
        public boolean isSigned(int column) throws SQLException {
            return false;
        }

        /**
         * Indicates the designated column's normal maximum width in characters.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return the normal maximum number of characters allowed as the width
         * of the designated column
         * @throws SQLException if a database access error occurs
         */
        @Override
        public int getColumnDisplaySize(int column) throws SQLException {
            return 0;
        }

        /**
         * Gets the designated column's suggested title for use in printouts and
         * displays. The suggested title is usually specified by the SQL {@code AS}
         * clause.  If a SQL {@code AS} is not specified, the value returned from
         * {@code getColumnLabel} will be the same as the value returned by the
         * {@code getColumnName} method.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return the suggested column title
         * @throws SQLException if a database access error occurs
         */
        @Override
        public String getColumnLabel(int column) throws SQLException {
            return "";
        }

        @Override
        public String getColumnName(int column) throws SQLException {
            return columnNames.get(column - 1);
        }

        /**
         * Get the designated column's table's schema.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return schema name or "" if not applicable
         * @throws SQLException if a database access error occurs
         */
        @Override
        public String getSchemaName(int column) throws SQLException {
            return "";
        }

        /**
         * Get the designated column's specified column size.
         * For numeric data, this is the maximum precision.  For character data, this is the length in characters.
         * For datetime datatypes, this is the length in characters of the String representation (assuming the
         * maximum allowed precision of the fractional seconds component). For binary data, this is the length in bytes.  For the ROWID datatype,
         * this is the length in bytes. 0 is returned for data types where the
         * column size is not applicable.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return precision
         * @throws SQLException if a database access error occurs
         */
        @Override
        public int getPrecision(int column) throws SQLException {
            return 0;
        }

        /**
         * Gets the designated column's number of digits to right of the decimal point.
         * 0 is returned for data types where the scale is not applicable.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return scale
         * @throws SQLException if a database access error occurs
         */
        @Override
        public int getScale(int column) throws SQLException {
            return 0;
        }

        /**
         * Gets the designated column's table name.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return table name or "" if not applicable
         * @throws SQLException if a database access error occurs
         */
        @Override
        public String getTableName(int column) throws SQLException {
            return "";
        }

        /**
         * Gets the designated column's table's catalog name.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return the name of the catalog for the table in which the given column
         * appears or "" if not applicable
         * @throws SQLException if a database access error occurs
         */
        @Override
        public String getCatalogName(int column) throws SQLException {
            return "";
        }

        /**
         * Retrieves the designated column's SQL type.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return SQL type from java.sql.Types
         * @throws SQLException if a database access error occurs
         * @see Types
         */
        @Override
        public int getColumnType(int column) throws SQLException {
            return 0;
        }

        /**
         * Retrieves the designated column's database-specific type name.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return type name used by the database. If the column type is
         * a user-defined type, then a fully-qualified type name is returned.
         * @throws SQLException if a database access error occurs
         */
        @Override
        public String getColumnTypeName(int column) throws SQLException {
            return "";
        }

        /**
         * Indicates whether the designated column is definitely not writable.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return {@code true} if so; {@code false} otherwise
         * @throws SQLException if a database access error occurs
         */
        @Override
        public boolean isReadOnly(int column) throws SQLException {
            return false;
        }

        /**
         * Indicates whether it is possible for a write on the designated column to succeed.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return {@code true} if so; {@code false} otherwise
         * @throws SQLException if a database access error occurs
         */
        @Override
        public boolean isWritable(int column) throws SQLException {
            return false;
        }

        /**
         * Indicates whether a write on the designated column will definitely succeed.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return {@code true} if so; {@code false} otherwise
         * @throws SQLException if a database access error occurs
         */
        @Override
        public boolean isDefinitelyWritable(int column) throws SQLException {
            return false;
        }

        /**
         * <p>Returns the fully-qualified name of the Java class whose instances
         * are manufactured if the method {@code ResultSet.getObject}
         * is called to retrieve a value
         * from the column.  {@code ResultSet.getObject} may return a subclass of the
         * class returned by this method.
         *
         * @param column the first column is 1, the second is 2, ...
         * @return the fully-qualified name of the class in the Java programming
         * language that would be used by the method
         * {@code ResultSet.getObject} to retrieve the value in the specified
         * column. This is the class name used for custom mapping.
         * @throws SQLException if a database access error occurs
         * @since 1.2
         */
        @Override
        public String getColumnClassName(int column) throws SQLException {
            return "";
        }

        /**
         * Returns an object that implements the given interface to allow access to
         * non-standard methods, or standard methods not exposed by the proxy.
         * <p>
         * If the receiver implements the interface then the result is the receiver
         * or a proxy for the receiver. If the receiver is a wrapper
         * and the wrapped object implements the interface then the result is the
         * wrapped object or a proxy for the wrapped object. Otherwise return the
         * the result of calling {@code unwrap} recursively on the wrapped object
         * or a proxy for that result. If the receiver is not a
         * wrapper and does not implement the interface, then an {@code SQLException} is thrown.
         *
         * @param iface A Class defining an interface that the result must implement.
         * @return an object that implements the interface. May be a proxy for the actual implementing object.
         * @throws SQLException If no object found that implements the interface
         * @since 1.6
         */
        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        /**
         * Returns true if this either implements the interface argument or is directly or indirectly a wrapper
         * for an object that does. Returns false otherwise. If this implements the interface then return true,
         * else if this is a wrapper then return the result of recursively calling {@code isWrapperFor} on the wrapped
         * object. If this does not implement the interface and is not a wrapper, return false.
         * This method should be implemented as a low-cost operation compared to {@code unwrap} so that
         * callers can use this method to avoid expensive {@code unwrap} calls that may fail. If this method
         * returns true then calling {@code unwrap} with the same argument should succeed.
         *
         * @param iface a Class defining an interface.
         * @return true if this implements the interface or directly or indirectly wraps an object that does.
         * @throws SQLException if an error occurs while determining whether this is a wrapper
         *                      for an object with the given interface.
         * @since 1.6
         */
        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
        
    }
    
    @Override public void close() throws SQLException {}

    /**
     * Reports whether
     * the last column read had a value of SQL {@code NULL}.
     * Note that you must first call one of the getter methods
     * on a column to try to read its value and then call
     * the method {@code wasNull} to see if the value read was
     * SQL {@code NULL}.
     *
     * @return {@code true} if the last column value read was SQL
     * {@code NULL} and {@code false} otherwise
     * @throws SQLException if a database access error occurs or this method is
     *                      called on a closed result set
     */
    @Override
    public boolean wasNull() throws SQLException {
        return false;
    }
    
}