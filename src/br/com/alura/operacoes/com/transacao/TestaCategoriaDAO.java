package br.com.alura.operacoes.com.transacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.ConnectionFactory;
import br.com.alura.dao.CategoriaDAO;
import br.com.alura.dao.ProdutoDAO;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;

public class TestaCategoriaDAO {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		try(Connection connection = connectionFactory.recuperarConexao()){
			CategoriaDAO dao = new CategoriaDAO(connection);
			Categoria categoria = new Categoria("Celular");
			dao.salvar(categoria);
			List<Produto> produtosComCategoria = dao.lsitarCategoriaEProduto();
			produtosComCategoria.forEach(System.out::println);
		}
		
	}
}
