package org.upa.gsonadapters;

import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

	@Override
	public LocalDate read(JsonReader jsonReader) throws IOException {
		return LocalDate.parse(jsonReader.nextString());
	}

	@Override
	public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
		jsonWriter.value(localDate.toString());
	}

}
