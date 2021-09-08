package br.com.alura.operacoes.com.transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.ConnectionFactory;
import br.com.alura.modelo.Produto;

public class TestaInsercaoComProduto {
	
	public static void main(String[] args) throws SQLException {
		
		//Produto a ser salvo:
		Produto comoda = new Produto("Cômoda", "Cômoda Vertical");
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
		
			String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?,?)";
			
			try(PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
				pst.setString(1, comoda.getNome());
				pst.setString(2, comoda.getDescricao());
				pst.execute();
				
				try(ResultSet rs = pst.getGeneratedKeys()){
					while(rs.next()) {
						comoda.setId(rs.getInt(1));
					}
				}
				
			}
			
		}
		
		System.out.println(comoda);
		
	}

}
