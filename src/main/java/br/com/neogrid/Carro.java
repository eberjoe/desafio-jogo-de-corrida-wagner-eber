package br.com.neogrid;

import java.math.BigDecimal;

public class Carro {
	private Long idCarro;
	private Long idPiloto;
	private String cor;
	private String marca;
	private Integer ano;
	private Integer potencia;
	BigDecimal preco;
	
	public Long getIdCarro() {
		return idCarro;
	}
	public void setIdCarro(Long idCarro) {
		this.idCarro = idCarro;
	}
	public Long getIdPiloto() {
		return idPiloto;
	}
	public void setIdPiloto(Long idPiloto) {
		this.idPiloto = idPiloto;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getPotencia() {
		return potencia;
	}
	public void setPotencia(Integer potencia) {
		this.potencia = potencia;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
}
