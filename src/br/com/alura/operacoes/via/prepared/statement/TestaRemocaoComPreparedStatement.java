package br.com.alura.operacoes.via.prepared.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.ConnectionFactory;

public class TestaRemocaoComPreparedStatement {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection = criaConexao.recuperarConexao();
		
		//Preparando comando DELETE
		PreparedStatement pst = connection.prepareStatement("DELETE FROM PRODUTO WHERE id > 1");

		//Executando o comando
		pst.execute();
		
		//Verifica quantas linhas (itens) foram alterados/deletados após executar Statement.
		Integer contador = pst.getUpdateCount();
		System.out.println("Foram deletados " + contador + " produtos.");
		
		//FEchando conexão
		connection.close();
		
	}
	
}
