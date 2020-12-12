package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class Day10Test {

    @Test
    final void exampleFirst() {
        final String data = """
                16
                10
                15
                5
                1
                11
                7
                19
                6
                12
                4""";

        final Day10 day10 = new Day10();
        final long result = day10.first(data.lines().collect(Collectors.toList()));
        Assertions.assertEquals(35, result);
    }

    @Test
    final void exampleSecond() {

    }

    @Test
    final void first() throws IOException {
        final List<String> data = readInput();
        final Day10 day10 = new Day10();
        final long result = day10.first(data);
        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {

    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day10")).collect(Collectors.toList());
    }

}
