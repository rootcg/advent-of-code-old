package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class Day8Test {

    @Test
    final void exampleFirst() {
        final String data = """
                nop +0
                acc +1
                jmp +4
                acc +3
                jmp -3
                acc -99
                acc +1
                jmp -4
                acc +6""";

        final Day8 day8 = new Day8();
        final long result = day8.first(data.lines().collect(Collectors.toList()));
        Assertions.assertEquals(5, result);
    }

    @Test
    final void exampleSecond() {

    }

    @Test
    final void first() throws IOException {
        final List<String> data = readInput();
        final Day8 day8 = new Day8();
        final long result = day8.first(data);
        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {

    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day8")).collect(Collectors.toList());
    }

}
