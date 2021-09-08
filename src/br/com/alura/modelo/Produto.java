package br.com.alura.modelo;

public class Produto {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Categoria categoria;
	
	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return String.format("O produto criado foi: %d - %s - %s - Categoria: %s", this.id, this.nome, this.descricao, this.categoria.getNome());
	}

}
