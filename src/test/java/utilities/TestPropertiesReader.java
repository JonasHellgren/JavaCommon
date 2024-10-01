package utilities;

import org.hellgren.utilities.file_io.PropertiesReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPropertiesReader {
    String path = "src/main/resources/";
    String name = "test.properties";

    @Test
    public void testGetProperties_HappyPath() throws IOException {
        // Arrange
        Properties props= PropertiesReader.getProperties(path, name);
        Assertions.assertEquals("value1", props.getProperty("key1"));
        Assertions.assertEquals(5, Integer.parseInt(props.getProperty("key2")));
    }

    @Test
    public void testGetProperties_FileNotFound() {
        // Arrange
        String path = "src/test/resources/";
        String name = "non-existent.properties";

        // Act and Assert
        assertThrows(IOException.class, () -> PropertiesReader.getProperties(path, name));
    }

    @Test
    public void testGetProperties_NullPath() {
        assertThrows(NullPointerException.class, () -> PropertiesReader.getProperties(null, name));
    }

    @Test
    public void testGetProperties_NullName() {
        assertThrows(NullPointerException.class, () -> PropertiesReader.getProperties(path, null));
    }

    @Test
    public void testGetProperties_EmptyPath() {
        assertThrows(IOException.class, () -> PropertiesReader.getProperties("", name));
    }

    @Test
    public void testGetProperties_EmptyName() {
        assertThrows(IOException.class, () -> PropertiesReader.getProperties(path, ""));
    }

}
