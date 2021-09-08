package br.com.alura.operacoes.com.transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.ConnectionFactory;

public class TestaInsercaoComTransacao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory criaConexao = new ConnectionFactory();
		try (Connection connection = criaConexao.recuperarConexao()) {

			// False para podermos manipular a transação.
			connection.setAutoCommit(false);

			// Criamos um PrepareStatment com o comando INSERT. Veja que os valores são
			// representados por INTERROGAÇÃO.
			// O segundo argumento do metodo execute faz retornar o ID do item criado/salvo.
			try (PreparedStatement pst = connection.prepareStatement(
					"INSERT INTO PRODUTO(NOME, DESCRICAO) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);) {

				// Por padrão, cada inserção está sendo feito por uma transação diferente!
				// Quando da erro no item teclado o item mouse já foi salvo!
				// Quando queremos controlar isso, devemos alterar na conexao o setAutoCommit()
				// para falso.
				// Dai poderemos forçar que ou salva os dois ou nenhum (rollback)!
				adicionarVariavel("mouse", "mouse sem fio", pst);
				adicionarVariavel("teclado", "teclado sem fio", pst);

				// Se depois de adicionar os dois itens sem erro eu posso commitar!
				connection.commit();

				// Fechando a connection
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Vamos dar o rollback");
				// Caso houver Exception, vamos retronar tudo com rollback
				connection.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement pst) throws SQLException {
		// Terminar de preparar o PreparedStatement. Vamos trocar as INTERROGAÇÕES por
		// VALORES
		// O primeiro argumento representa o index (posição) da interrogação. O segundo
		// argumento é o valor.
		pst.setString(1, nome);
		pst.setString(2, descricao);

		if (nome.equals("teclado")) {
			throw new RuntimeException("Forçando esse erro!");
		}

		// Executando o PreparedStatement
		pst.execute();

		// Pegando o ID do item criado
		try (ResultSet rs = pst.getGeneratedKeys()) {

			while (rs.next()) {
				// Pegando o valor via index.
				Integer id = rs.getInt(1);
				System.out.println("Foi criado o produto com id: " + id);
			}

		}
	}

}
