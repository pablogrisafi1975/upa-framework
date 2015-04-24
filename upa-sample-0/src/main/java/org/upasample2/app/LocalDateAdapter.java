package org.upasample2.app;

import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

	@Override
	public LocalDate read(JsonReader jsonReader) throws IOException {
		String localDateAsString = jsonReader.nextString();
		return LocalDate.parse(localDateAsString);
	}

	@Override
	public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
		jsonWriter.value(localDate.toString());
	}

}
