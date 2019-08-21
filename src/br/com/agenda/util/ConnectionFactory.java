/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	private static Connection conexao;

	/**
	 * Método que retorna conexão com o Banco de Dados
	 * @param sgbd (MSSQL para SQL Server, MYSQL para MySQL)
	 * @return um objeto Connection
	 * @throws Exception
	 */
	public static Connection getConnection(String sgbd) throws Exception {
		
		if((conexao != null) && (!conexao.isClosed())) 
			return conexao;
		
		if(sgbd.equalsIgnoreCase("MSSQL"))
			sqlServer();
		else if(sgbd.equalsIgnoreCase("MYSQL"))
			mySQL();
		else
			throw new Exception("Tipo de SGBD inválido, Informe MSSQL ou MYSQL");
		
		return conexao;
	}
	
	/**
	 * Cria conexão com o MS SQL Server	
	 * @throws SQLException
	 */
	private static void sqlServer() throws SQLException, ClassNotFoundException {
		String usuario = "sa";
		String senha = "asdf1568";
		String banco = "JLOCADORA";
		String servidor = "DESKTOP-T3TP4MV\\SQL2012";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
		
                Class.forName(driver);
		conexao = DriverManager.getConnection("jdbc:sqlserver://" 
			     + servidor 
			     + ";databaseName=" + banco, usuario, senha);  
		
	}
	
	/**
	 * Cria conexão com MySQL
	 * @throws SQLException
	 */
	private static void mySQL() throws SQLException, ClassNotFoundException {
		String usuario = "root";
		String senha = "fiNd321";
		String banco = "trabalhopoo";
		String servidor = "127.0.0.1";
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		conexao = DriverManager.getConnection("jdbc:mysql://" 
				+ servidor 
				+ ":3306/" + banco+"?useTimezone=true&serverTimezone=UTC", usuario, senha);  
	}
	
	public static void Close() throws SQLException {
		if(conexao == null) return;
		conexao.close();
	}
	
	public static void Dispose(Statement stm) throws SQLException {
		stm.close();
		stm = null;
	}
	
	public static void Dispose(PreparedStatement stm) throws SQLException {
		stm.close();
		stm = null;
	}
	
	public static void Dispose(PreparedStatement stm, ResultSet rs) throws SQLException {
		rs.close();
		rs = null;
		Dispose(stm);
	}
	
	public static void Dispose(Statement stm, ResultSet rs) throws SQLException {
		rs.close();
		rs = null;
		Dispose(stm);
	}
}