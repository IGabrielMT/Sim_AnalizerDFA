package co.edu.uptc.automata.model.jsonmanager;

public class JSONWriter {

import java.io.IOException;
import java.time.Period;

public class PeriodAdapter extends TypeAdapter<Period> {
    @Override
    public void write(JsonWriter out, Period period) throws IOException {
        out.value(period.toString());
    }
