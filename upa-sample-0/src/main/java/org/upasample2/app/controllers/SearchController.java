package org.upasample2.app.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.upasample2.app.domain.Tx;

public class SearchController {
	public List<Tx> findAllTx(Integer paramInteger, String paramString) {
		ArrayList<Tx> arrayList = new ArrayList<>();
		arrayList.add(new Tx(paramString + "a", paramInteger + 1, LocalDate.now()));
		arrayList.add(new Tx(paramString + "b", paramInteger + 2, LocalDate.now().minusDays(3)));
		return arrayList;
	}

}
