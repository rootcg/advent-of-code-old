package root.cristian;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * --- Day 4: Passport Processing ---
 */
public class Day4 {

    private static final Pattern PASSPORT_FIELD_SEPARATOR = Pattern.compile(" ");
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("^#[a-fA-F0-9]{6}$");
    private static final Map<String, Predicate<String>> REQUIRED_PASSPORT_FIELDS = Map.of(
            "byr", (value) -> Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002,
            "iyr", (value) -> Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020,
            "eyr", (value) -> Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030,
            "hgt", (value) -> {
                if (value.length() <= 2) return false;

                Function<String, Integer> getNumber = (text) -> Integer.parseInt(value.substring(0, value.length() - 2));
                return switch (value.substring(value.length() - 2)) {
                    case "cm" -> getNumber.apply(value) >= 150 && getNumber.apply(value) <= 193;
                    case "in" -> getNumber.apply(value) >= 59 && getNumber.apply(value) <= 76;
                    default -> false;
                };
            },
            "hcl", (value) -> HEX_COLOR_PATTERN.matcher(value).matches(),
            "ecl", (value) -> List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value),
            "pid", (value) -> value.length() == 9 && value.chars().allMatch(Character::isDigit)
    );

    /**
     * You arrive at the airport only to realize that you grabbed your North Pole Credentials instead of your passport.
     * While these documents are extremely similar, North Pole Credentials aren't issued by a country and therefore
     * aren't actually valid documentation for travel in most of the world.
     * <p/>
     * It seems like you're not the only one having problems, though; a very long line has formed for the automatic
     * passport scanners, and the delay could upset your travel itinerary. Due to some questionable network security,
     * you realize you might be able to solve both of these problems at the same time.
     * <p/>
     * The automatic passport scanners are slow because they're having trouble detecting which passports have all
     * required fields. The expected fields are as follows:
     * <ul>
     *     <li>byr (Birth Year)</li>
     *     <li>iyr (Issue Year)</li>
     *     <li>eyr (Expiration Year)</li>
     *     <li>hgt (Height)</li>
     *     <li>hcl (Hair Color)</li>
     *     <li>ecl (Eye Color)</li>
     *     <li>pid (Passport ID)</li>
     *     <li>cid (Country ID)</li>
     * </ul>
     * Passport data is validated in batch files (your puzzle input). Each passport is represented as a sequence of
     * key:value pairs separated by spaces or newlines. Passports are separated by blank lines.
     * <p/>
     * Here is an example batch file containing four passports:
     * <pre>
     * ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
     * byr:1937 iyr:2017 cid:147 hgt:183cm
     *
     * iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
     * hcl:#cfa07d byr:1929
     *
     * hcl:#ae17e1 iyr:2013
     * eyr:2024
     * ecl:brn pid:760753108 byr:1931
     * hgt:179cm
     *
     * hcl:#cfa07d eyr:2025 pid:166559648
     * iyr:2011 ecl:brn hgt:59in
     * </pre>
     * <p>
     * The first passport is valid - all eight fields are present. The second passport is invalid - it is missing hgt
     * (the Height field).
     * <p/>
     * The third passport is interesting; the only missing field is cid, so it looks like data from North Pole Credentials, not a passport at all! Surely, nobody would mind if you made the system temporarily ignore missing cid fields. Treat this "passport" as valid.
     * <p/>
     * The fourth passport is missing two fields, cid and byr. Missing cid is fine, but missing any other field is not, so this passport is invalid.
     * <p/>
     * According to the above rules, your improved system would report 2 valid passports.
     * <p/>
     * Count the number of valid passports - those that have all required fields. Treat cid as optional. In your batch file, how many passports are valid?
     *
     * @param lines raw passports info
     * @return number of valid passports
     */
    final long first(final List<String> lines) {
        return parsePassportList(lines)
                .stream()
                .map(PASSPORT_FIELD_SEPARATOR::splitAsStream)
                .map(passportFields -> passportFields.map(field -> field.split(":")[0]).collect(Collectors.toList()))
                .filter(passportFields -> passportFields.containsAll(REQUIRED_PASSPORT_FIELDS.keySet()))
                .count();
    }

    /**
     * The line is moving more quickly now, but you overhear airport security talking about how passports with invalid
     * data are getting through. Better add some data validation, quick!
     * <p/>
     * You can continue to ignore the cid field, but each other field has strict rules about what values are valid for
     * automatic validation:
     * <ul>
     *     <li>byr (Birth Year) - four digits; at least 1920 and at most 2002.</li>
     *     <li>iyr (Issue Year) - four digits; at least 2010 and at most 2020.</li>
     *     <li>eyr (Expiration Year) - four digits; at least 2020 and at most 2030.</li>
     *     <li>hgt (Height) - a number followed by either cm or in:</li>
     *     <li>If cm, the number must be at least 150 and at most 193.</li>
     *     <li>If in, the number must be at least 59 and at most 76.</li>
     *     <li>hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.</li>
     *     <li>ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.</li>
     *     <li>pid (Passport ID) - a nine-digit number, including leading zeroes.</li>
     *     <li>cid (Country ID) - ignored, missing or not.</li>
     * </ul>
     * Your job is to count the passports where all required fields are both present and valid according to the above
     * rules. Here are some example values:
     * <pre>
     * byr valid:   2002
     * byr invalid: 2003
     *
     * hgt valid:   60in
     * hgt valid:   190cm
     * hgt invalid: 190in
     * hgt invalid: 190
     *
     * hcl valid:   #123abc
     * hcl invalid: #123abz
     * hcl invalid: 123abc
     *
     * ecl valid:   brn
     * ecl invalid: wat
     *
     * pid valid:   000000001
     * pid invalid: 0123456789
     * </pre>
     * Here are some invalid passports:
     * <pre>
     * eyr:1972 cid:100
     * hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926
     *
     * iyr:2019
     * hcl:#602927 eyr:1967 hgt:170cm
     * ecl:grn pid:012533040 byr:1946
     *
     * hcl:dab227 iyr:2012
     * ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277
     *
     * hgt:59cm ecl:zzz
     * eyr:2038 hcl:74454a iyr:2023
     * pid:3556412378 byr:2007
     * </pre>
     * Here are some valid passports:
     * <pre>
     * pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
     * hcl:#623a2f
     *
     * eyr:2029 ecl:blu cid:129 byr:1989
     * iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm
     *
     * hcl:#888785
     * hgt:164cm byr:2001 iyr:2015 cid:88
     * pid:545766238 ecl:hzl
     * eyr:2022
     *
     * iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
     * </pre>
     * Count the number of valid passports - those that have all required fields and valid values. Continue to treat
     * cid as optional.
     * <p/>
     * In your batch file, how many passports are valid?
     *
     * @param lines raw passports info
     * @return number of valid passports
     */
    final long second(final List<String> lines) {
        return parsePassportList(lines)
                .stream()
                .map(PASSPORT_FIELD_SEPARATOR::splitAsStream)
                .map(passportFields -> passportFields.map(field -> field.split(":")).collect(Collectors.toMap(field -> field[0], field -> field[1])))
                .filter(passportFields -> passportFields.keySet().containsAll(REQUIRED_PASSPORT_FIELDS.keySet()))
                .filter(passportFields -> REQUIRED_PASSPORT_FIELDS.entrySet().stream().allMatch(requiredField -> requiredField.getValue().test(passportFields.get(requiredField.getKey()))))
                .count();
    }

    private List<String> parsePassportList(List<String> rawPassports) {
        final BiFunction<LinkedList<List<String>>, String, LinkedList<List<String>>> accumulator =
                (linkedList, line) -> {
                    if (line.isBlank()) {
                        linkedList.add(new ArrayList<>());
                    } else {
                        linkedList.getLast().add(line);
                    }

                    return linkedList;
                };

        return rawPassports.stream()
                           .reduce(new LinkedList<>(Collections.<List<String>>singletonList(new ArrayList<>())),
                                   accumulator, (a, b) -> b)
                           .stream()
                           .map(passport -> String.join(" ", passport))
                           .collect(Collectors.toList());
    }

}
