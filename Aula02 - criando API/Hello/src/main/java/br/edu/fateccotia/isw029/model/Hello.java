package br.edu.fateccotia.isw029.model;

import java.time.LocalDateTime;

public class Hello {
	
	private String name;
	private LocalDateTime date;
	
	//get e set
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return String.format("message: %s, date: %s"
				,name
				,date);
		
	}
	
}
