package a;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    final void example() {
        final Main main = new Main();
        final int[] numbers = new int[]{1721, 979, 366, 299, 675, 1456};
        final int result = main.first(numbers);
        Assertions.assertEquals(result, 514579);
    }

}
