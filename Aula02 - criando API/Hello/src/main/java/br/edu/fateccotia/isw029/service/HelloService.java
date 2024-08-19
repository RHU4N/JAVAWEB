package br.edu.fateccotia.isw029.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateccotia.isw029.model.Hello;

public class HelloService {
	private static final List<Hello> lista = new ArrayList<Hello>();
	
	public void create(String line) {
		Hello hello = new Hello();
		hello.setName(line);
		hello.setDate(LocalDateTime.now());
		lista.add(hello);
	}
	
	public static  List<Hello> findAll(){
		return lista;
	}
}
