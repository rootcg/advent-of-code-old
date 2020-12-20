package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class Day11Test {

    @Test
    final void exampleFirst() {
        final String data = """
                L.LL.LL.LL
                LLLLLLL.LL
                L.L.L..L..
                LLLL.LL.LL
                L.LL.LL.LL
                L.LLLLL.LL
                ..L.L.....
                LLLLLLLLLL
                L.LLLLLL.L
                L.LLLLL.LL""";

        final Day11 day11 = new Day11();
        final long result = day11.first(data.lines().collect(Collectors.toList()));
        Assertions.assertEquals(37, result);
    }

    @Test
    final void exampleSecond() {

    }

    @Test
    final void first() throws IOException {
        final List<String> data = readInput();
        final Day11 day11 = new Day11();
        final long result = day11.first(data);
        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {
        final List<String> data = readInput();
        final Day11 day11 = new Day11();
        final long result = day11.second(data);
        System.out.println("Result is: " + result);
    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day11")).collect(Collectors.toList());
    }

}
