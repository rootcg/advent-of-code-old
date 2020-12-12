package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class Day7Test {

    @Test
    final void exampleFirst() {
        final String data = """
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.""";

        final Day7 day7 = new Day7();
        final long result = day7.first(data.lines().collect(Collectors.toList()));
        Assertions.assertEquals(4, result);
    }

    @Test
    final void exampleSecond() {
        final String data = """
                shiny gold bags contain 2 dark red bags.
                dark red bags contain 2 dark orange bags.
                dark orange bags contain 2 dark yellow bags.
                dark yellow bags contain 2 dark green bags.
                dark green bags contain 2 dark blue bags.
                dark blue bags contain 2 dark violet bags.
                dark violet bags contain no other bags.""";

        final Day7 day7 = new Day7();
        final long result = day7.second(data.lines().collect(Collectors.toList()));
        Assertions.assertEquals(126, result);
    }

    @Test
    final void first() throws IOException {
        final List<String> data = readInput();
        final Day7 day7 = new Day7();
        final long result = day7.first(data);
        System.out.println("Result is: " + result);
    }

    @Test
    final void second() throws IOException {
        final List<String> data = readInput();
        final Day7 day7 = new Day7();
        final long result = day7.second(data);
        System.out.println("Result is: " + result);
    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day7")).collect(Collectors.toList());
    }

}
