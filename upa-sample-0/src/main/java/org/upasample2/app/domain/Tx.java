package org.upasample2.app.domain;

import java.time.LocalDate;

public class Tx {
	private String name;
	private Integer id;
	private LocalDate localDate;

	public Tx(String name, Integer id, LocalDate localDate) {
		this.name = name;
		this.id = id;
		this.localDate = localDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

}
