package br.com.neogrid;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.neogrid.desafio.annotation.Desafio;
import br.com.neogrid.desafio.app.NeedForSpeedInterface;
import br.com.neogrid.desafio.exceptions.*;


public class NeedForSpeedApplication implements NeedForSpeedInterface {
	private List<Piloto> pilotos = new ArrayList<>();
	private List<Carro> carros = new ArrayList<>();

	private Optional<Piloto> buscarPilotoPorId(Long idPiloto) {
		return this.pilotos.stream()
				.filter(p -> p.getIdPiloto().equals(idPiloto))
				.findFirst();
	}
	private Optional<Carro> buscarCarroPorId(Long idCarro) {
		return this.carros.stream()
				.filter(c -> c.getIdCarro().equals(idCarro))
				.findFirst();
	}

	@Override
	@Desafio("novoPiloto")
	public void novoPiloto(Long id, String nome, LocalDate dataNascimento, LocalDate dataInicioCarreira,
			BigDecimal dinheiro) {
		if (buscarPilotoPorId(id).isPresent()) {
			throw new IdentificadorUtilizadoException();
		}
		Piloto piloto = new Piloto();
		piloto.setIdPiloto(id);
		piloto.setNomePiloto(nome);
		piloto.setDataNascimento(dataNascimento);
		piloto.setDataInicioCarreira(dataInicioCarreira);
		piloto.setDinheiroPiloto(dinheiro);
		this.pilotos.add(piloto);
	}
	
	@Override
	@Desafio("comprarCarro")
	public void comprarCarro(Long id, Long idPiloto, String cor, String marca, Integer ano, Integer potencia,
			BigDecimal preco) {
		Piloto piloto = buscarPilotoPorId(idPiloto).orElseThrow(PilotoNaoEncontradoException::new);
		BigDecimal dinheiro = piloto.getDinheiroPiloto();
		if (dinheiro.compareTo(preco) < 0) {
			throw new SaldoInsuficienteException();
		}
		if (buscarCarroPorId(id).isPresent()) {
			throw new IdentificadorUtilizadoException();
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
		// deduz o preco do carro do "dinheiro" do piloto
		piloto.setDinheiroPiloto(dinheiro.subtract(preco));
		this.pilotos.set(this.pilotos.indexOf(piloto), piloto);
	}

	@Override
	@Desafio("venderCarro")
	public void venderCarro(Long idCarro) {
		Carro carro = buscarCarroPorId(idCarro).orElseThrow(CarroNaoEncontradoException::new);
		Piloto piloto = buscarPilotoPorId(carro.getIdPiloto()).get();
		BigDecimal dinheiro = piloto.getDinheiroPiloto();
		this.carros.remove(this.carros.indexOf(carro));
		// adiciona o valor do carro ao dinheiro do piloto que o vendeu
		piloto.setDinheiroPiloto(dinheiro.add(carro.preco));
		this.pilotos.set(this.pilotos.indexOf(piloto), piloto);
	}

	@Override
	@Desafio("buscarCarroMaisCaro")
	public Long buscarCarroMaisCaro() {
		BigDecimal maiorpreco = this.carros.stream().max(Comparator.comparing(Carro::getPreco)).get().getPreco();
		return this.carros.stream()
				.filter(c -> c.getPreco().equals(maiorpreco))
				.min(Comparator.comparing(Carro::getIdCarro)).get().getIdCarro();
	}

	@Override
	@Desafio("buscarCarroMaisPotente")
	public Long buscarCarroMaisPotente() {
		Integer maiorpotencia = this.carros.stream().max(Comparator.comparing(Carro::getPotencia)).get().getPotencia();
		return this.carros.stream()
				.filter(c -> c.getPotencia().equals(maiorpotencia))
				.min(Comparator.comparing(Carro::getIdCarro)).get().getIdCarro();
	}

	@Override
	@Desafio("buscarCarros")
	public List<Long> buscarCarros(Long idPiloto) {
		if(!buscarPilotoPorId(idPiloto).isPresent()) {
			throw new PilotoNaoEncontradoException();
		}
		return this.carros.stream()
				.filter(c -> c.getIdPiloto().equals(idPiloto))
				.sorted((a, b) -> a.getIdCarro().compareTo(b.getIdCarro()))
				.map(c -> c.getIdCarro())
				.collect(Collectors.toList());
	}

	@Override
	@Desafio("buscarCarrosPorMarca")
	public List<Long> buscarCarrosPorMarca(String marca) {
		return this.carros.stream()
				.filter(c -> c.getMarca().equals(marca))
				.sorted((a, b) -> a.getIdCarro().compareTo(b.getIdCarro()))
				.map(c -> c.getIdCarro())
				.collect(Collectors.toList());
	}

	@Override
	@Desafio("buscarCor")
	public String buscarCor(Long idCarro) {
		return buscarCarroPorId(idCarro).orElseThrow(CarroNaoEncontradoException::new).getCor();
	}

	@Override
	@Desafio("buscarMarcas")
	public List<String> buscarMarcas() {
		List<String> todasmarcas = new ArrayList<>();
		Set<String> marcas = this.carros.stream()
				.map(c -> c.getMarca())
				.collect(Collectors.toSet());
		todasmarcas.addAll(marcas);
		return todasmarcas;
	}

	@Override
	@Desafio("buscarNomePiloto")
	public String buscarNomePiloto(Long idPiloto) {
		return buscarPilotoPorId(idPiloto).orElseThrow(PilotoNaoEncontradoException::new).getNomePiloto();
	}

	@Override
	@Desafio("buscarPilotoMaisExperiente")
	public Long buscarPilotoMaisExperiente() {
		LocalDate maiorcarreira = this.pilotos.stream()
				.min(Comparator.comparing(Piloto::getDataInicioCarreira)).get().getDataInicioCarreira();
		return this.pilotos.stream()
				.filter(p -> p.getDataInicioCarreira().equals(maiorcarreira))
				.min(Comparator.comparing(Piloto::getIdPiloto)).get().getIdPiloto();
	}

	@Override
	@Desafio("buscarPilotoMenosExperiente")
	public Long buscarPilotoMenosExperiente() {
		LocalDate menorcarreira = this.pilotos.stream()
				.max(Comparator.comparing(Piloto::getDataInicioCarreira)).get().getDataInicioCarreira();
		return this.pilotos.stream()
				.filter(p -> p.getDataInicioCarreira().equals(menorcarreira))
				.min(Comparator.comparing(Piloto::getIdPiloto)).get().getIdPiloto();
	}

	@Override
	@Desafio("buscarPilotos")
	public List<Long> buscarPilotos() {
		
		return this.pilotos.stream()
				.map(p -> p.getIdPiloto())
				.collect(Collectors.toList());
	}

	@Override
	@Desafio("buscarSaldo")
	public BigDecimal buscarSaldo(Long idPiloto) {
		
		return buscarPilotoPorId(idPiloto).orElseThrow(PilotoNaoEncontradoException::new).getDinheiroPiloto();
	}

	@Override
	@Desafio("buscarValorPatrimonio")
	public BigDecimal buscarValorPatrimonio(Long idPiloto) {
		if (!buscarPilotoPorId(idPiloto).isPresent()) {
			throw new PilotoNaoEncontradoException();
		}
		BigDecimal valorcarros = this.carros.stream()
				.filter(c -> c.getIdPiloto().equals(idPiloto))
				.map(c -> c.getPreco())
				.reduce(new BigDecimal(0), BigDecimal::add);
		return valorcarros;
	}

	@Override
	@Desafio("trocarCor")
	public void trocarCor(Long idCarro, String cor) {
		if(!buscarCarroPorId(idCarro).isPresent()) {
			throw new br.com.neogrid.desafio.exceptions.CarroNaoEncontradoException();
		}
		buscarCarroPorId(idCarro).get().setCor(cor);
	}
}
