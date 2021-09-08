package br.com.alura.operacoes.via.prepared.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.ConnectionFactory;

public class TestaListagemComPreparedStatement {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection = criaConexao.recuperarConexao();
		
		//Preparando o comando SELECT
		PreparedStatement pst = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");

		//Executando o comando
		pst.execute();
		
		//Pegando o resultado
		ResultSet rst = pst.getResultSet();
		
		//Listando os itens que foi retornado na consulta. Sempre verificamos se há o próximo
		while(rst.next()) {
			//Podemos acessar valor da coluna via index ( começa em 1) ou pelo nome da coluna.
			Integer id = rst.getInt("ID");
			String nome = rst.getString("NOME");
			String descricao = rst.getString("DESCRICAO");
			
			System.out.println(id + " " + nome + " " + descricao );
		}
		
		//Fecha a Conexão
		connection.close();
		
	}
	
}
