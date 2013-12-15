package jp.skypencil.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Test;

public class JsonLoadingTest {
    private File testResourceDir = new File("src/test/resources");

    @Test
    public void loadSimpleJson() {
        ConfigLoader loader = new ConfigLoader();
        Config config = loader.load(new File(testResourceDir, "simple.json"), Config.class);
        assertThat(config.getFoo(), is("foo"));
    }

    @Test
    public void loadHOCON() {
        ConfigLoader loader = new ConfigLoader();
        Config config = loader.load(new File(testResourceDir, "hocon.conf"), Config.class);
        assertThat(config.getFoo(), is("foo"));
    }

    public static class Config {
        private String foo;
        private String bar;

        public String getFoo() {
            return foo;
        }
        public void setFoo(String foo) {
            this.foo = foo;
        }
        public String getBar() {
            return bar;
        }
        public void setBar(String bar) {
            this.bar = bar;
        }
    }
}
