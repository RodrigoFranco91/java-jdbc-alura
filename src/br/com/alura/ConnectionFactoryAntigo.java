package br.com.alura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * Esse ConnectionFactory não usa Pool de Conexão. 
 * Não devemos usá-lo!
 */


public class ConnectionFactoryAntigo {
	
	public Connection recuperarConexao() throws SQLException {

		//Primeiro argumento é o endereço do banco
		//O segundo argumento é o usuario e o terceiro é a senha do banco
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "mysql");
		
		return connection;
	}

}
