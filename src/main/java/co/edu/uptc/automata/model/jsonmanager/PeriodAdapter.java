package co.edu.uptc.automata.model.jsonmanager;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Period;

public class PeriodAdapter extends TypeAdapter<Period> {
    @Override
    public void write(JsonWriter out, Period period) throws IOException {
        out.value(period.toString());
    }

    @Override
    public Period read(JsonReader in) throws IOException {
        return Period.parse(in.nextString());
    }
}