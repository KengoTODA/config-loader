package jp.skypencil.config;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;

public class ConfigLoader {

    public <T> T load(File file, Class<T> configClass) {
        try {
            File strictJson = reconstruct(file);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(strictJson, configClass);
        } catch (JsonParseException | JsonMappingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private File reconstruct(File hocon) throws IOException {
        File strictJson = File.createTempFile("config-loader", ".json");
        Config parsed = ConfigFactory.parseFile(hocon).resolve();
        String reconstructed = parsed.root().render(ConfigRenderOptions.concise());
        Files.write(reconstructed.getBytes(Charsets.UTF_8), strictJson);
        return strictJson;
    }

}
