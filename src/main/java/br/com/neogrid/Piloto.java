package br.com.neogrid;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Piloto {
	private Long idPiloto;
	private String nomePiloto;
	private LocalDate dataNascimento;
	private LocalDate dataInicioCarreira;
	private BigDecimal dinheiroPiloto;
	
	public Long getIdPiloto() {
		return idPiloto;
	}
	public void setIdPiloto(Long idPiloto) {
		this.idPiloto = idPiloto;
	}
	public String getNomePiloto() {
		return nomePiloto;
	}
	public void setNomePiloto(String nomePiloto) {
		this.nomePiloto = nomePiloto;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public LocalDate getDataInicioCarreira() {
		return dataInicioCarreira;
	}
	public void setDataInicioCarreira(LocalDate dataInicioCarreira) {
		this.dataInicioCarreira = dataInicioCarreira;
	}
	public BigDecimal getDinheiroPiloto() {
		return dinheiroPiloto;
	}
	public void setDinheiroPiloto(BigDecimal dinheiroPiloto) {
		this.dinheiroPiloto = dinheiroPiloto;
	}
}
