package org.gaopan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author gzoro
 *
 * 测试对db表的四种操作
 * 查询 statement.executeQuery
 * 删除 statement.executeUpdate
 * 增加 statement.executeUpdate
 * 修改 statement.executeUpdate
 */
public class TestMySQL {

	public static void main(String[] args) throws Exception {
//		testQuery();
//		testInsert();
//		testDelete();
//		testUpdate();
		testPreparedQuery();
	}

	/**
	 * 测试表查询
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void testQuery() throws ClassNotFoundException, SQLException {
		// 载入mysql驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 建立到mysql的db连接
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// 创建Statement
		Statement st = dbConnection.createStatement();
		// 在Statement上执行sql查询语句
		ResultSet rs = st.executeQuery("select * from `guild`");
		// 取出结果元数据，里面存了表头
		ResultSetMetaData rsmd = rs.getMetaData();

		// 把表头打印出来
		StringBuilder sb = new StringBuilder();
		for (int col = 1; col <= rsmd.getColumnCount(); ++col) {
			sb.append(rsmd.getColumnName(col)).append("\t");
		}
		System.out.println(sb.toString());

		// 打印全部结果
		while (rs.next()) { // 切换当前行到下一行
			StringBuilder sb1 = new StringBuilder();
			for (int col = 1; col <= rsmd.getColumnCount(); ++col) {
				// 取当前行的指定列的值，转成string类型
				sb1.append(rs.getString(col)).append("\t");
			}
			System.out.println(sb1.toString());
		}

		// 清除相关资源
		rs.close();
		st.close();
		dbConnection.close();
	}
	
	/**
	 * 测试表查询
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void testPreparedQuery() throws ClassNotFoundException, SQLException {
		// 载入mysql驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 建立到mysql的db连接
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// 在Statement上执行sql查询语句
		String sql = "select * from `guild` where id=? or name=?";
		// 创建Statement
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		ps.setInt(1, 2);
		ps.setString(2, "gaopan");
		
		ResultSet rs = ps.executeQuery();
		// 取出结果元数据，里面存了表头
		ResultSetMetaData rsmd = rs.getMetaData();

		// 把表头打印出来
		StringBuilder sb = new StringBuilder();
		for (int col = 1; col <= rsmd.getColumnCount(); ++col) {
			sb.append(rsmd.getColumnName(col)).append("\t");
		}
		System.out.println(sb.toString());

		// 打印全部结果
		while (rs.next()) { // 切换当前行到下一行
			StringBuilder sb1 = new StringBuilder();
			for (int col = 1; col <= rsmd.getColumnCount(); ++col) {
				// 取当前行的指定列的值，转成string类型
				sb1.append(rs.getString(col)).append("\t");
			}
			System.out.println(sb1.toString());
		}

		// 清除相关资源
		rs.close();
		ps.close();
		dbConnection.close();
	}

	/**
	 * 插入一行记录到表中
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void testInsert() throws ClassNotFoundException, SQLException {
		// 载入mysql驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 建立到mysql的db连接
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// 创建Statement
		Statement st = dbConnection.createStatement();
		int rows = st.executeUpdate("insert into guild values(5,'guild6',1005,'name5',null,null)");
		System.out.println("effect rows:" + rows);
		st.close();
		dbConnection.close();
	}
	
	/**
	 * 从表中删除一条记录
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void testDelete() throws ClassNotFoundException, SQLException {
		// 载入mysql驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 建立到mysql的db连接
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// 创建Statement
		Statement st = dbConnection.createStatement();
		int rows = st.executeUpdate("delete from guild where id=5");
		System.out.println("effect rows:" + rows);
		st.close();
		dbConnection.close();
	}
	
	private static void testUpdate() throws ClassNotFoundException, SQLException {
		// 载入mysql驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 建立到mysql的db连接
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// 创建Statement
		Statement st = dbConnection.createStatement();
		int rows = st.executeUpdate("update guild set `name`='gaopan' where id=4");
		System.out.println("effect rows:" + rows);
		st.close();
		dbConnection.close();
	}
}