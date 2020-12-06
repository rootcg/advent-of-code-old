package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class Day3Test {

    @Test
    final void exampleFirst() {
        final String lines = """
                ..##.......
                #...#...#..
                .#....#..#.
                ..#.#...#.#
                .#...##..#.
                ..#.##.....
                .#.#.#....#
                .#........#
                #.##...#...
                #...##....#
                .#..#...#.#""";

        final Day3 day3 = new Day3();
        final long result = day3.first(lines.lines().collect(Collectors.toList()));
        Assertions.assertEquals(7, result);
    }

    @Test
    final void exampleSecond() {

    }

    @Test
    final void first() throws IOException {
        final List<String> lines = readInput();
        final Day3 day3 = new Day3();
        final long result = day3.first(lines);
        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {

    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day3")).collect(Collectors.toList());
    }

}
