package org.upasample0.app.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.upasample0.app.domain.Tx;

public class SearchController {
	public List<Tx> findAllTx(String param12, Integer param2) {
		ArrayList<Tx> arrayList = new ArrayList<>();
		arrayList.add(new Tx("first", 1, LocalDate.now()));
		arrayList.add(new Tx("second", 2, LocalDate.now().minusDays(3)));
		return arrayList;
	}

}
