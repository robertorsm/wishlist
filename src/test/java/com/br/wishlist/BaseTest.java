package com.br.wishlist;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ActiveProfiles("test")
public class BaseTest {

    public ObjectMapper mapper = new ObjectMapper();

    public <T> T createIntegrationResponse(String path, Class<T> element) {
        try (InputStream resourceAsStream = this.getClass().getResourceAsStream(String.format("/%s", path))){
            final Object request = JSONValue.parse(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
            return mapper.convertValue(request, element);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public <T> List<T> createIntegrationResponseList(String path, Class<T> element) {
        try (InputStream resourceAsStream = getClass().getResourceAsStream(String.format("/%s", path))) {
            String jsonContent = new String(resourceAsStream.readAllBytes(), "UTF-8");
            return mapper.readValue(jsonContent, mapper.getTypeFactory().constructCollectionType(List.class, element));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
