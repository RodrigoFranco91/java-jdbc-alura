package br.com.alura;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class ConnectionFactory {
	
	//Crio o DataSource
	public DataSource dataSource;
	
	
	public ConnectionFactory(){
		//Definindo que toda instancia de ConnectionFactory vai criar um pool de conexao
		//COnfiguramos o Pool de Conexao com os dados do banco de dados.
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("mysql");
		
		//Configuração extra do Pool de Conexao:
		
		//Quantidade máxima de conexão que fica disponível!
		comboPooledDataSource.setMaxPoolSize(15);
		
		//Queremos que nossa aplicação se comunique com o DataSource e não com o  Pool diretamente!
		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexao() throws SQLException {
		return this.dataSource.getConnection();
	}

}
