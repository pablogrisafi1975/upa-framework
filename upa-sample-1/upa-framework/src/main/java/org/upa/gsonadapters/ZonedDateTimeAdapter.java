package org.upa.gsonadapters;

import java.io.IOException;
import java.time.ZonedDateTime;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ZonedDateTimeAdapter extends TypeAdapter<ZonedDateTime> {

	@Override
	public ZonedDateTime read(JsonReader jsonReader) throws IOException {
		return ZonedDateTime.parse(jsonReader.nextString());
	}

	@Override
	public void write(JsonWriter jsonWriter, ZonedDateTime zonedDateTime) throws IOException {
		jsonWriter.value(zonedDateTime.toString());
	}

}
