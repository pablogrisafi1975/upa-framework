package org.upa.gsonadapters;

import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

	@Override
	public LocalDateTime read(JsonReader jsonReader) throws IOException {
		return LocalDateTime.parse(jsonReader.nextString());
	}

	@Override
	public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
		jsonWriter.value(localDateTime.toString());
	}

}
