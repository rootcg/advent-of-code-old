package a;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;

class MainTest {

    @Test
    final void exampleFirst() {
        final Main main = new Main();
        final int[] numbers = new int[]{1721, 979, 366, 299, 675, 1456};
        final int result = main.first(numbers);
        Assertions.assertEquals(514579, result);
    }

    @Test
    final void exampleSecond() {
        final Main main = new Main();
        final int[] numbers = new int[]{1721, 979, 366, 299, 675, 1456};
        final int result = main.second(numbers);
        Assertions.assertEquals(241861950, result);
    }

    @Test
    final void first() throws IOException {
        final int[] numbers = readInput();

        final Main main = new Main();
        final int result = main.first(numbers);

        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {
        final int[] numbers = readInput();

        final Main main = new Main();
        final int result = main.second(numbers);

        System.out.println("Result is: " + result);
    }

    private int[] readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("a"))
                    .map(Integer::valueOf)
                    .mapToInt(Integer::intValue)
                    .toArray();
    }

}
