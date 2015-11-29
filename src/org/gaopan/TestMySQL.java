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
 * ���Զ�db������ֲ���
 * ��ѯ statement.executeQuery
 * ɾ�� statement.executeUpdate
 * ���� statement.executeUpdate
 * �޸� statement.executeUpdate
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
	 * ���Ա��ѯ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void testQuery() throws ClassNotFoundException, SQLException {
		// ����mysql����
		Class.forName("com.mysql.jdbc.Driver");
		// ������mysql��db����
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// ����Statement
		Statement st = dbConnection.createStatement();
		// ��Statement��ִ��sql��ѯ���
		ResultSet rs = st.executeQuery("select * from `guild`");
		// ȡ�����Ԫ���ݣ�������˱�ͷ
		ResultSetMetaData rsmd = rs.getMetaData();

		// �ѱ�ͷ��ӡ����
		StringBuilder sb = new StringBuilder();
		for (int col = 1; col <= rsmd.getColumnCount(); ++col) {
			sb.append(rsmd.getColumnName(col)).append("\t");
		}
		System.out.println(sb.toString());

		// ��ӡȫ�����
		while (rs.next()) { // �л���ǰ�е���һ��
			StringBuilder sb1 = new StringBuilder();
			for (int col = 1; col <= rsmd.getColumnCount(); ++col) {
				// ȡ��ǰ�е�ָ���е�ֵ��ת��string����
				sb1.append(rs.getString(col)).append("\t");
			}
			System.out.println(sb1.toString());
		}

		// ��������Դ
		rs.close();
		st.close();
		dbConnection.close();
	}
	
	/**
	 * ���Ա��ѯ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void testPreparedQuery() throws ClassNotFoundException, SQLException {
		// ����mysql����
		Class.forName("com.mysql.jdbc.Driver");
		// ������mysql��db����
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// ��Statement��ִ��sql��ѯ���
		String sql = "select * from `guild` where id=? or name=?";
		// ����Statement
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		ps.setInt(1, 2);
		ps.setString(2, "gaopan");
		
		ResultSet rs = ps.executeQuery();
		// ȡ�����Ԫ���ݣ�������˱�ͷ
		ResultSetMetaData rsmd = rs.getMetaData();

		// �ѱ�ͷ��ӡ����
		StringBuilder sb = new StringBuilder();
		for (int col = 1; col <= rsmd.getColumnCount(); ++col) {
			sb.append(rsmd.getColumnName(col)).append("\t");
		}
		System.out.println(sb.toString());

		// ��ӡȫ�����
		while (rs.next()) { // �л���ǰ�е���һ��
			StringBuilder sb1 = new StringBuilder();
			for (int col = 1; col <= rsmd.getColumnCount(); ++col) {
				// ȡ��ǰ�е�ָ���е�ֵ��ת��string����
				sb1.append(rs.getString(col)).append("\t");
			}
			System.out.println(sb1.toString());
		}

		// ��������Դ
		rs.close();
		ps.close();
		dbConnection.close();
	}

	/**
	 * ����һ�м�¼������
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void testInsert() throws ClassNotFoundException, SQLException {
		// ����mysql����
		Class.forName("com.mysql.jdbc.Driver");
		// ������mysql��db����
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// ����Statement
		Statement st = dbConnection.createStatement();
		int rows = st.executeUpdate("insert into guild values(5,'guild6',1005,'name5',null,null)");
		System.out.println("effect rows:" + rows);
		st.close();
		dbConnection.close();
	}
	
	/**
	 * �ӱ���ɾ��һ����¼
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void testDelete() throws ClassNotFoundException, SQLException {
		// ����mysql����
		Class.forName("com.mysql.jdbc.Driver");
		// ������mysql��db����
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// ����Statement
		Statement st = dbConnection.createStatement();
		int rows = st.executeUpdate("delete from guild where id=5");
		System.out.println("effect rows:" + rows);
		st.close();
		dbConnection.close();
	}
	
	private static void testUpdate() throws ClassNotFoundException, SQLException {
		// ����mysql����
		Class.forName("com.mysql.jdbc.Driver");
		// ������mysql��db����
		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "123456");
		// ����Statement
		Statement st = dbConnection.createStatement();
		int rows = st.executeUpdate("update guild set `name`='gaopan' where id=4");
		System.out.println("effect rows:" + rows);
		st.close();
		dbConnection.close();
	}
}