package br.com.neogrid;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
	public static void main (String[] args) {
		NeedForSpeedApplication nfs = new NeedForSpeedApplication();
		nfs.novoPiloto(1l, "Gil Gomes", LocalDate.of(1999, 5, 5), LocalDate.of(2016, 2, 10), BigDecimal.valueOf(100000));
		nfs.novoPiloto(2l, "Pedro De Lara", LocalDate.of(1944, 5, 5), LocalDate.of(1960, 2, 10), BigDecimal.valueOf(400000));
		nfs.novoPiloto(3l, "Sônia Lima", LocalDate.of(1960, 5, 10), LocalDate.of(1966, 2, 10), BigDecimal.valueOf(200000));
		nfs.novoPiloto(4l, "Décio Piccinini", LocalDate.of(1955, 5, 5), LocalDate.of(1990, 2, 10), BigDecimal.valueOf(600000));
		nfs.novoPiloto(5l, "Sérgio Malandro", LocalDate.of(1961, 5, 5), LocalDate.of(1980, 2, 10), BigDecimal.valueOf(1000000));
		nfs.novoPiloto(6l, "Leão Lobo", LocalDate.of(1973, 5, 5), LocalDate.of(1999, 2, 10), BigDecimal.valueOf(50000));
		nfs.novoPiloto(72l, "Wagner Montes", LocalDate.of(1954, 5, 5), LocalDate.of(1974, 2, 10), BigDecimal.valueOf(100000));
		
		nfs.comprarCarro(1l, 2l, "branco", "Audi", 2016, 200, BigDecimal.valueOf(350000));
		nfs.comprarCarro(2l, 1l, "azul", "Mercedes", 2016, 200, BigDecimal.valueOf(50000));
		nfs.comprarCarro(35l, 5l, "preto", "BMW", 2016, 200, BigDecimal.valueOf(550000));
		nfs.comprarCarro(30l, 5l, "prata", "BMW", 2018, 200, BigDecimal.valueOf(300000));

		
		System.out.print(nfs.buscarSaldo(6l));
	}

}
