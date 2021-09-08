package br.com.alura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {

		// O primeiro parametro do método getConnection é o endereço do banco com o
		// padrão de horário,
		// o segundo é o usuário e o terceiro é a senha.
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "mysql");

		System.out.println("Rodou!");
		
		//Fecha a Conexão
		connection.close();

	}
}
