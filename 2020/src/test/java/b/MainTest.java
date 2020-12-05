package b;

import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class MainTest {

    @Test
    final void exampleFirst() {

    }

    @Test
    final void exampleSecond() {

    }

    @Test
    final void first() throws IOException {

    }

    @Test
    final void second() throws IOException {

    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("b")).collect(Collectors.toList());
    }

}
