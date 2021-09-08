package br.com.alura.operacoes.via.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.ConnectionFactory;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection = criaConexao.recuperarConexao();
		
		Statement stm = connection.createStatement();
		//Executando um DELETE
		stm.execute("DELETE FROM PRODUTO WHERE id > 1");
		
		//Verifica quantas linhas (itens) foram alterados/deletados após executar Statement.
		Integer contador = stm.getUpdateCount();
		System.out.println("Foram deletados " + contador + " produtos.");
		
		//FEchando conexão
		connection.close();
		
	}
	
}
