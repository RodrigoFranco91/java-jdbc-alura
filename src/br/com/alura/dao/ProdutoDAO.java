package br.com.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;

public class ProdutoDAO {
	
	private Connection connection;
	
	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Produto produto) throws SQLException {
		
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?,?)";
		
		try(PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getDescricao());
			pst.execute();
			
			try(ResultSet rs = pst.getGeneratedKeys()){
				while(rs.next()) {
					produto.setId(rs.getInt(1));
				}
			}
			
		}
		
	}
	
	public List<Produto> listar() throws SQLException{
		List<Produto> produtos = new ArrayList<>();
		
		String sql = "SELECT * FROM PRODUTO";
		
		try(PreparedStatement pst = connection.prepareStatement(sql)){
			//Antes estavamosfazendo: pst.execute() e depois faziamos pst.getResultSet()
			//Vamos usar o metodo executeQuery() que já retorna o ResultSet
			try(ResultSet rs = pst.executeQuery()){
				while(rs.next()) {
					Produto produto = new Produto(rs.getString("NOME"), rs.getString("DESCRICAO"));
					produto.setId(rs.getInt("ID"));
					produtos.add(produto);
				}
			}
		}
		
		return produtos;		
	}
	
	public List<Produto> listarPorCategoriaQuerie1MaisN(Categoria categoria) throws SQLException{
		List<Produto> produtos = new ArrayList<>();
		
		String sql = "SELECT * FROM PRODUTO WHERE CATEGORIA_ID = ?";
		
		try(PreparedStatement pst = connection.prepareStatement(sql)){
			
			System.out.println("Listando Produto por Categoria");
			pst.setInt(1, categoria.getId());
			
			//Antes estavamosfazendo: pst.execute() e depois faziamos pst.getResultSet()
			//Vamos usar o metodo executeQuery() que já retorna o ResultSet
			try(ResultSet rs = pst.executeQuery()){
				while(rs.next()) {
					Produto produto = new Produto(rs.getString("NOME"), rs.getString("DESCRICAO"));
					produto.setId(rs.getInt("ID"));
					produtos.add(produto);
				}
			}
		}
		
		return produtos;		
	}
	
}
