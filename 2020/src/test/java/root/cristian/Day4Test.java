package root.cristian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FilesUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class Day4Test {

    @Test
    final void exampleFirst() {
        final String data = """
                ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
                byr:1937 iyr:2017 cid:147 hgt:183cm
                                
                iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
                hcl:#cfa07d byr:1929
                                
                hcl:#ae17e1 iyr:2013
                eyr:2024
                ecl:brn pid:760753108 byr:1931
                hgt:179cm
                                
                hcl:#cfa07d eyr:2025 pid:166559648
                iyr:2011 ecl:brn hgt:59in""";

        final Day3 day4 = new Day3();
        final long result = day4.first(data.lines().collect(Collectors.toList()));
        Assertions.assertEquals(2, result);
    }

    @Test
    final void exampleSecond() {

    }

    @Test
    final void first() throws IOException {

    }

    @Test
    final void second() throws IOException {

    }

    private List<String> readInput() throws IOException {
        return Files.lines(FilesUtilities.getResource("Day4")).collect(Collectors.toList());
    }

}
