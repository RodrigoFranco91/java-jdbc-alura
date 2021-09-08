package br.com.alura.operacoes.com.transacao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.ConnectionFactory;
import br.com.alura.dao.ProdutoDAO;
import br.com.alura.modelo.Produto;

public class TestaProdutoDAO {

	public static void main(String[] args) throws SQLException {
		
		Produto comoda = new Produto("Cômoda", "Cômoda Vertical");

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			ProdutoDAO dao = new ProdutoDAO(connection);
			dao.salvar(comoda);
			List<Produto> produtos = dao.listar();
			produtos.forEach(System.out::println);
			
		}
	}
}
