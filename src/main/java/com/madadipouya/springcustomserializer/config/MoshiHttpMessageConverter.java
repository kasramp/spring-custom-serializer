package com.madadipouya.springcustomserializer.config;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJsonHttpMessageConverter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class MoshiHttpMessageConverter extends AbstractJsonHttpMessageConverter {

    private Moshi moshi;

    public MoshiHttpMessageConverter() {
        this.moshi = new Moshi.Builder().build();
        ;
        this.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Type resolvedType, Reader reader) throws Exception {
        try (BufferedSource source = Okio.buffer(Okio.source(asInputStream(reader)))) {
            JsonAdapter<?> adapter = moshi.adapter(resolvedType);
            return adapter.fromJson(source);
        }
    }

    @Override
    protected void writeInternal(Object object, Type type, Writer writer) throws Exception {
        JsonAdapter<Object> adapter = moshi.adapter(type);
        BufferedSink bufferedSink = Okio.buffer(Okio.sink(asOutputStream(writer)));
        adapter.toJson(bufferedSink, object);
        bufferedSink.flush();
    }

    private InputStream asInputStream(Reader reader) {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return reader.read();
            }
        };
    }

    private OutputStream asOutputStream(Writer writer) {
        return new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                writer.write(b);
            }
        };
    }
}
