package org.github.jrdalpra.organizze.utils;

import java.io.InputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JsonMapper<T> {
    ExtendedHttpClient http;
    HttpResponse.BodySubscriber<InputStream> stream;
    ObjectMapper mapper;

    public JsonMapper(ExtendedHttpClient http) {
        this(
                http,
                HttpResponse.BodySubscribers.ofInputStream(),
                new ObjectMapper()
                        .findAndRegisterModules()
                        .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                        .setInjectableValues(
                                new InjectableValues.Std(Map.of(ExtendedHttpClient.class.getCanonicalName(), http))
                        )
        );
    }

    public final <T> HttpResponse.BodyHandler<T> deserialize(Class<T> type) {
        return new HttpResponse.BodyHandler<T>() {
            @Override
            public HttpResponse.BodySubscriber<T> apply(final HttpResponse.ResponseInfo responseInfo) {
                return HttpResponse.BodySubscribers.mapping(stream, (InputStream is) -> {
                    try (InputStream stream = is) {
                        return mapper.readValue(stream, type);
                    } catch (Exception error) {
                        throw new RuntimeException(error);
                    }
                });
            }
        };
    }


    @SneakyThrows
    final HttpRequest.BodyPublisher serialize(T object) {
        return HttpRequest.BodyPublishers.ofString(
                this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object)
        );
    }
}
