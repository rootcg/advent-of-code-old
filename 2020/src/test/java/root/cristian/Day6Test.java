package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class Day6Test {

    @Test
    final void exampleFirst() {
        final String data = """
                abc
                                
                a
                b
                c
                                
                ab
                ac
                                
                a
                a
                a
                a
                                
                b""";

        final Day6 day6 = new Day6();
        final long result = day6.first(data.lines().collect(Collectors.toList()));
        Assertions.assertEquals(11, result);
    }

    @Test
    final void exampleSecond() {

    }

    @Test
    final void first() throws IOException {
        final List<String> data = readInput();
        final Day6 day6 = new Day6();
        final long result = day6.first(data);
        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {
        final List<String> data = readInput();
        final Day6 day6 = new Day6();
        final long result = day6.second(data);
        System.out.println("Result is: " + result);
    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day6")).collect(Collectors.toList());
    }

}
