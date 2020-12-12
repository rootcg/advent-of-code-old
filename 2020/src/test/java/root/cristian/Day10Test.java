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
                28
                33
                18
                42
                31
                14
                46
                20
                48
                47
                24
                23
                49
                45
                19
                38
                39
                11
                1
                32
                25
                35
                8
                17
                7
                9
                4
                2
                34
                10
                3""";

        final Day10 day10 = new Day10();
        final long result = day10.first(data.lines().collect(Collectors.toList()), 5);
        Assertions.assertEquals(220, result);
    }

    @Test
    final void exampleSecond() {

    }

    @Test
    final void first() throws IOException {
        final List<String> data = readInput();
        final Day10 day10 = new Day10();
        final long result = day10.first(data, 25);
        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {

    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day10")).collect(Collectors.toList());
    }

}
