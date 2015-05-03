package org.upa.gsonadapters;

import java.io.IOException;
import java.time.LocalTime;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalTimeAdapter extends TypeAdapter<LocalTime> {

	@Override
	public LocalTime read(JsonReader jsonReader) throws IOException {
		return LocalTime.parse(jsonReader.nextString());
	}

	@Override
	public void write(JsonWriter jsonWriter, LocalTime localTime) throws IOException {
		jsonWriter.value(localTime.toString());
	}

}
