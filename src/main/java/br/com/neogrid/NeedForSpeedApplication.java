package br.com.neogrid;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.neogrid.desafio.annotation.Desafio;
import br.com.neogrid.desafio.app.NeedForSpeedInterface;

public class NeedForSpeedApplication implements NeedForSpeedInterface {
	private List<Piloto> pilotos = new ArrayList<>();
	private List<Carro> carros = new ArrayList<>();

	private Optional<Piloto> buscarPilotoPorId(Long idPiloto) {
		return pilotos.stream().filter(p -> p.getIdPiloto().equals(idPiloto)).findFirst();
	}
	private Optional<Carro> buscarCarroPorId(Long idCarro) {
		return carros.stream().filter(c -> c.getIdCarro().equals(idCarro)).findFirst();
	}

	@Override
	@Desafio("novoPiloto")
	public void novoPiloto(Long id, String nome, LocalDate dataNascimento, LocalDate dataInicioCarreira,
			BigDecimal dinheiro) {
		
		Optional<Piloto> piloto = buscarPilotoPorId(id);
		
		if (piloto.isPresent()) {
			throw new br.com.neogrid.desafio.exceptions.IdentificadorUtilizadoException();
		}
//		if (dinheiro.equals(BigDecimal.valueOf(0l))) {
//			throw new br.com.neogrid.desafio.exceptions.SaldoInsuficienteException();
//		}
		
		Piloto piloto2 = new Piloto();
		piloto2.setIdPiloto(id);
		piloto2.setNomePiloto(nome);
		piloto2.setDataNascimento(dataNascimento);
		piloto2.setDataInicioCarreira(dataInicioCarreira);
		piloto2.setDinheiroPiloto(dinheiro);

		this.pilotos.add(piloto2);

		// throw new UnsupportedOperationException();
	}
	
	@Override
	@Desafio("comprarCarro")
	public void comprarCarro(Long id, Long idPiloto, String cor, String marca, Integer ano, Integer potencia,
			BigDecimal preco) {

		Optional<Piloto> piloto = buscarPilotoPorId(idPiloto);
		
		if (!piloto.isPresent()) {
			throw new br.com.neogrid.desafio.exceptions.PilotoNaoEncontradoException();
		}
		if (piloto.get().getDinheiroPiloto().compareTo(preco) < 0) {
			throw new br.com.neogrid.desafio.exceptions.SaldoInsuficienteException();
		}
		
		for (Carro carro : this.carros) {
			if (carro.getIdCarro().equals(id)) {
				throw new br.com.neogrid.desafio.exceptions.IdentificadorUtilizadoException();
			}
		}

		Carro carro = new Carro();
		carro.setIdCarro(id);
		carro.setIdPiloto(idPiloto);
		carro.setCor(cor);
		carro.setMarca(marca);
		carro.setAno(ano);
		carro.setPotencia(potencia);
		carro.setPreco(preco);
		this.carros.add(carro);
			
		// throw new UnsupportedOperationException();
	}

	@Override
	@Desafio("venderCarro")
	public void venderCarro(Long idCarro) {
		if(!buscarCarroPorId(idCarro).isPresent()) {
			throw new br.com.neogrid.desafio.exceptions.CarroNaoEncontradoException();
		}
	}

	@Override
	@Desafio("buscarCarroMaisCaro")
	public Long buscarCarroMaisCaro() {
		throw new UnsupportedOperationException();
	}

	@Override
	@Desafio("buscarCarroMaisPotente")
	public Long buscarCarroMaisPotente() {
		throw new UnsupportedOperationException();
	}

	@Override
	@Desafio("buscarCarros")
	public List<Long> buscarCarros(Long idPiloto) {
		if(!buscarPilotoPorId(idPiloto).isPresent()) {
			throw new br.com.neogrid.desafio.exceptions.PilotoNaoEncontradoException();
		}
		
		List<Long> lista = new ArrayList<Long>();
		for (Carro carro : this.carros) {
			if (carro.getIdPiloto().equals(idPiloto)) {
				lista.add(carro.getIdCarro());
			}
		}
		return (lista);
		//throw new UnsupportedOperationException();
	}

	@Override
	@Desafio("buscarCarrosPorMarca")
	public List<Long> buscarCarrosPorMarca(String marca) {
		List<Long> lista = new ArrayList<Long>();
		for (Carro carro : this.carros) {
			if (carro.getMarca().equals(marca)) {
				lista.add(carro.getIdCarro());
			}
		}
		return (lista);
	}

	@Override
	@Desafio("buscarCor")
	public String buscarCor(Long idCarro) {
		
		if(!buscarCarroPorId(idCarro).isPresent()) {
			throw new br.com.neogrid.desafio.exceptions.CarroNaoEncontradoException();
		}
		for (Carro carro: this.carros) {
			if (carro.getIdCarro().equals(idCarro)) {
				return carro.getCor();
			}
		}
		return "";
		//return buscarCarroPorId(idCarro).get().getCor();
	}

	@Override
	@Desafio("buscarMarcas")
	public List<String> buscarMarcas() {
		throw new UnsupportedOperationException();
	}

	@Override
	@Desafio("buscarNomePiloto")
	public String buscarNomePiloto(Long idPiloto) {
		for (Piloto piloto : this.pilotos) {
			if (piloto.getIdPiloto().equals(idPiloto)) {
				return piloto.getNomePiloto();
			}
		}
		return "Não encontrado";
		// throw new UnsupportedOperationException();
	}

	@Override
	@Desafio("buscarPilotoMaisExperiente")
	public Long buscarPilotoMaisExperiente() {
		for (Piloto piloto : this.pilotos) {
			
			
		}
	}

	@Override
	@Desafio("buscarPilotoMenosExperiente")
	public Long buscarPilotoMenosExperiente() {
		throw new UnsupportedOperationException();
	}

	@Override
	@Desafio("buscarPilotos")
	public List<Long> buscarPilotos() {
		List<Long> lista = new ArrayList<Long>();
		for (Piloto piloto : this.pilotos) {
			lista.add(piloto.getIdPiloto());
		}
		return (lista);
		// throw new UnsupportedOperationException();
	}

	@Override
	@Desafio("buscarSaldo")
	public BigDecimal buscarSaldo(Long idPiloto) {
		if(!buscarPilotoPorId(idPiloto).isPresent()) {
			throw new br.com.neogrid.desafio.exceptions.PilotoNaoEncontradoException();
		}
		return BigDecimal.valueOf(10l);
	}

	@Override
	@Desafio("buscarValorPatrimonio")
	public BigDecimal buscarValorPatrimonio(Long idPiloto) {
		if(!buscarPilotoPorId(idPiloto).isPresent()) {
			throw new br.com.neogrid.desafio.exceptions.PilotoNaoEncontradoException();
		}
		return BigDecimal.valueOf(10l);
	}

	@Override
	@Desafio("trocarCor")
	public void trocarCor(Long idCarro, String cor) {
		if(!buscarCarroPorId(idCarro).isPresent()) {
			throw new br.com.neogrid.desafio.exceptions.CarroNaoEncontradoException();
		}
		
		for (Carro carro : this.carros) {
			if (carro.getIdCarro().equals(idCarro)) {
				carro.setCor(cor);
			}
		}
	}

}
