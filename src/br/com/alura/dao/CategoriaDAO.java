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

public class CategoriaDAO {
	
	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		
		this.connection = connection;
	}

	public void salvar(Categoria categoria) throws SQLException {
		
		String sql = "INSERT INTO CATEGORIA (NOME) VALUES (?)";
		
		try(PreparedStatement pst =  connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pst.setString(1, categoria.getNome());
			pst.execute();
			
			try(ResultSet rs = pst.getGeneratedKeys()){
				while(rs.next()) {
					Integer id = rs.getInt(1);
					System.out.println("Produto cadastrado com id: " + id);
				}
			}
			
		}
		
	}
	
	public List<Categoria> lsitar() throws SQLException{
		
		List<Categoria> categorias = new ArrayList<>();
		
		String sql = "SELECT * FROM CATEGORIA";
		
		try(PreparedStatement pst = connection.prepareStatement(sql)){
			pst.execute();
			System.out.println("Listando Categoria");
			
			try(ResultSet rs = pst.getResultSet()){
				while(rs.next()) {
					Categoria categoria = new Categoria(rs.getString("NOME"));
					categoria.setId(rs.getInt("ID"));
					categorias.add(categoria);
				}
			}
		}
		
		return categorias;
	}
	
	public List<Produto> lsitarCategoriaEProduto() throws SQLException{
		
		List<Produto> produtos = new ArrayList<>();
		
		String sql = "SELECT * FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID LIKE P.CATEGORIA_ID";
		
		try(PreparedStatement pst = connection.prepareStatement(sql)){
			pst.execute();
			
			try(ResultSet rs = pst.getResultSet()){
				while(rs.next()) {
					Categoria categoria = new Categoria(rs.getString("C.NOME"));
					categoria.setId(rs.getInt("C.ID"));
					Produto produto = new Produto(rs.getString("P.NOME"), rs.getString("P.NOME"));
					produto.setId(rs.getInt("P.ID"));
					produto.setCategoria(categoria);
					produtos.add(produto);
				}
			}
		}
		
		return produtos;
	}
	
}
