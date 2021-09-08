package br.com.alura.operacoes.via.prepared.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.ConnectionFactory;

public class TestaInsercaoComPreparedStatement {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection = criaConexao.recuperarConexao();
		
		//Criamos um PrepareStatment com o comando INSERT. Veja que os valores são representados por INTERROGAÇÃO.
		//O segundo argumento do metodo execute faz retornar o ID do item criado/salvo.
		PreparedStatement pst = connection.prepareStatement("INSERT INTO PRODUTO(NOME, DESCRICAO) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);

		//Terminar de preparar o PreparedStatement. Vamos trocar as INTERROGAÇÕES por VALORES
		//O primeiro argumento representa o index (posição) da interrogação. O segundo argumento é o valor.
		pst.setString(1, "Mouse");
		pst.setString(2, "Mouse sem fio");
		
		//Executando o PreparedStatement
		pst.execute();
		
		//Pegando o ID do item criado
		ResultSet rs = pst.getGeneratedKeys();
		while(rs.next()) {
			//Pegando o valor via index.
			Integer id = rs.getInt(1);
			System.out.println("Foi criado o produto com id: " + id);
		}
		
	}

}
