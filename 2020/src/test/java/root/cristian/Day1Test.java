package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;

class Day1Test {

    @Test
    final void exampleFirst() {
        final Day1 day1 = new Day1();
        final int[] numbers = new int[]{1721, 979, 366, 299, 675, 1456};
        final int result = day1.first(numbers);
        Assertions.assertEquals(514579, result);
    }

    @Test
    final void exampleSecond() {
        final Day1 day1 = new Day1();
        final int[] numbers = new int[]{1721, 979, 366, 299, 675, 1456};
        final int result = day1.second(numbers);
        Assertions.assertEquals(241861950, result);
    }

    @Test
    final void first() throws IOException {
        final int[] numbers = readInput();

        final Day1 day1 = new Day1();
        final int result = day1.first(numbers);

        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {
        final int[] numbers = readInput();

        final Day1 day1 = new Day1();
        final int result = day1.second(numbers);

        System.out.println("Result is: " + result);
    }

    private int[] readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day1"))
                    .map(Integer::valueOf)
                    .mapToInt(Integer::intValue)
                    .toArray();
    }

}
