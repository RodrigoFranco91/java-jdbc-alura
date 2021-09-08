package br.com.alura.operacoes.via.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.ConnectionFactory;

public class TestaInsercao {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection = criaConexao.recuperarConexao();
		
		Statement stm = connection.createStatement();
		//Executando um INSERT no banco. O segundo argumento do metodo execute faz retornar o ID do item criado/salvo.
		stm.execute("INSERT INTO PRODUTO(NOME, DESCRICAO) VALUES('MOUSE', 'MOUSE SEM FIO')", Statement.RETURN_GENERATED_KEYS);
		
		//Pegando o ID do item criado
		ResultSet rs = stm.getGeneratedKeys();
		while(rs.next()) {
			//Pegando o valor via index.
			Integer id = rs.getInt(1);
			System.out.println("Foi criado o produto com id: " + id);
		}
		

	}

}
