package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Day5Test {

    @Test
    final void exampleFirst() {
        final Day5 day5 = new Day5();
        Assertions.assertAll(
                () -> Assertions.assertEquals(567, day5.first(Collections.singletonList("BFFFBBFRRR"))),
                () -> Assertions.assertEquals(119, day5.first(Collections.singletonList("FFFBBBFRRR"))),
                () -> Assertions.assertEquals(820, day5.first(Collections.singletonList("BBFFBBFRLL")))
        );
    }

    @Test
    final void exampleSecond() {
        // There is no example
    }

    @Test
    final void first() throws IOException {
        final List<String> data = readInput();
        final Day5 day5 = new Day5();
        final long result = day5.first(data);
        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {
        final List<String> data = readInput();
        final Day5 day5 = new Day5();
        final long result = day5.second(data);
        System.out.println("Result is: " + result);
    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day5")).collect(Collectors.toList());
    }

}
