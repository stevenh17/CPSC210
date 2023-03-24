package persistence;

import model.Category;
import model.Record;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testRead() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Record r = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecord.json");
        try {
            Record r = reader.read();
            assertEquals(0, r.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecord.json");
        try {
            Record r = reader.read();
            assertEquals(1, r.getLength());
            assertEquals("a", r.getLog(0).get(0).getName());
            assertEquals("b", r.getLog(0).get(1).getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
