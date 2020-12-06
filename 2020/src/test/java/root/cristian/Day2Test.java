package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class Day2Test {

    @Test
    final void exampleFirst() {
        final String[] lines = new String[]{"1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"};
        final Day2 day2 = new Day2();
        final long result = day2.first(List.of(lines));
        Assertions.assertEquals(2, result);
    }

    @Test
    final void exampleSecond() {
        final String[] lines = new String[]{"1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"};
        final Day2 day2 = new Day2();
        final long result = day2.second(List.of(lines));
        Assertions.assertEquals(1, result);
    }

    @Test
    final void first() throws IOException {
        final List<String> lines = readInput();
        final Day2 day2 = new Day2();
        final long result = day2.first(lines);
        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {
        final List<String> lines = readInput();
        final Day2 day2 = new Day2();
        final long result = day2.second(lines);
        System.out.println("Result is: " + result);
    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day2")).collect(Collectors.toList());
    }

}
