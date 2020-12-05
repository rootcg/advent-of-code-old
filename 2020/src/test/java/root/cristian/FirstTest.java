package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;

class FirstTest {

    @Test
    final void exampleFirst() {
        final First first = new First();
        final int[] numbers = new int[]{1721, 979, 366, 299, 675, 1456};
        final int result = first.first(numbers);
        Assertions.assertEquals(514579, result);
    }

    @Test
    final void exampleSecond() {
        final First first = new First();
        final int[] numbers = new int[]{1721, 979, 366, 299, 675, 1456};
        final int result = first.second(numbers);
        Assertions.assertEquals(241861950, result);
    }

    @Test
    final void first() throws IOException {
        final int[] numbers = readInput();

        final First first = new First();
        final int result = first.first(numbers);

        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {
        final int[] numbers = readInput();

        final First first = new First();
        final int result = first.second(numbers);

        System.out.println("Result is: " + result);
    }

    private int[] readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("a"))
                    .map(Integer::valueOf)
                    .mapToInt(Integer::intValue)
                    .toArray();
    }

}
