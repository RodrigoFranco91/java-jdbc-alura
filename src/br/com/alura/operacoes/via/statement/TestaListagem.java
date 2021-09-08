package br.com.alura.operacoes.via.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.ConnectionFactory;

public class TestaListagem {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection = criaConexao.recuperarConexao();
		
		Statement stm = connection.createStatement();
		//O método execute retorna true quando há retorno de listagem e false quando não tem
		//Por exemplo, update, delete não há retorno!
		boolean resultado = stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
		//Para pegar de fato a listagem retornada, no caso os dados salvos no banco.
		ResultSet rst = stm.getResultSet();
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
