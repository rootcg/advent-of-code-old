package root.cristian;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * --- Day 7: Handy Haversacks ---
 */
public class Day7 {

    private static final Pattern LUGGAGE_RULE_PATTERN = Pattern.compile("(\\d)?\\s?([a-zA-Z\\s]*)\\sbags?");

    /**
     * You land at the regional airport in time for your next flight. In fact, it looks like you'll even have time to
     * grab some food: all flights are currently delayed due to issues in luggage processing.
     * <p/>
     * Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and their
     * contents; bags must be color-coded and must contain specific quantities of other color-coded bags. Apparently,
     * nobody responsible for these regulations considered how long they would take to enforce!
     * <p/>
     * For example, consider the following rules:
     * <pre>
     * light red bags contain 1 bright white bag, 2 muted yellow bags.
     * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
     * bright white bags contain 1 shiny gold bag.
     * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
     * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
     * dark olive bags contain 3 faded blue bags, 4 dotted black bags.
     * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
     * faded blue bags contain no other bags.
     * dotted black bags contain no other bags.
     * </pre>
     * These rules specify the required contents for 9 bag types. In this example, every faded blue bag is empty, every
     * vibrant plum bag contains 11 bags (5 faded blue and 6 dotted black), and so on.
     * <p/>
     * You have a shiny gold bag. If you wanted to carry it in at least one other bag, how many different bag colors
     * would be valid for the outermost bag? (In other words: how many colors can, eventually, contain at least one
     * shiny gold bag?)
     * <p/>
     * In the above rules, the following options would be available to you:
     * <pre>
     * A bright white bag, which can hold your shiny gold bag directly.
     * A muted yellow bag, which can hold your shiny gold bag directly, plus some other bags.
     * A dark orange bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.
     * A light red bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.
     * </pre>
     * So, in this example, the number of bag colors that can eventually contain at least one shiny gold bag is 4.
     * <p/>
     * How many bag colors can eventually contain at least one shiny gold bag? (The list of rules is quite long; make
     * sure you get all of it.)
     *
     * @param lines containing luggage rules
     * @return number of bags that can hold the shiny gold one
     */
    final long first(final List<String> lines) {
        final BiConsumer<Map<String, Set<String>>, Matcher> luggageAccumulator = (map, matcher) -> {
            String container =
                    Optional.of(matcher.find())
                            .filter(Boolean.TRUE::equals)
                            .map(ignore -> matcher.group(2))
                            .orElseThrow(IllegalStateException::new);

            matcher.results()
                   .map(bags -> bags.group(2))
                   .forEach(bag -> map.compute(bag,
                                               (ignore, containers) -> {
                                                   containers = containers == null ? new HashSet<>() : containers;
                                                   containers.add(container);
                                                   return containers;
                                               }));
        };

        final Map<String, Set<String>> bags =
                lines.stream()
                     .map(LUGGAGE_RULE_PATTERN::matcher)
                     .collect(HashMap::new, luggageAccumulator, Map::putAll);

        return recursiveLookup("shiny gold", bags, Collectors.toSet()).size();
    }

    /**
     * It's getting pretty expensive to fly these days - not because of ticket prices, but because of the ridiculous
     * number of bags you need to buy!
     * <p/>
     * Consider again your shiny gold bag and the rules from the above example:
     * <pre>
     * faded blue bags contain 0 other bags.
     * dotted black bags contain 0 other bags.
     * vibrant plum bags contain 11 other bags: 5 faded blue bags and 6 dotted black bags.
     * dark olive bags contain 7 other bags: 3 faded blue bags and 4 dotted black bags.
     * So, a single shiny gold bag must contain 1 dark olive bag (and the 7 bags within it) plus 2 vibrant plum bags
     * (and the 11 bags within each of those): 1 + 1*7 + 2 + 2*11 = 32 bags!
     * </pre>
     * Of course, the actual rules have a small chance of going several levels deeper than this example; be sure to
     * count all of the bags, even if the nesting becomes topologically impractical!
     * <p/>
     * Here's another example:
     * <pre>
     * shiny gold bags contain 2 dark red bags.
     * dark red bags contain 2 dark orange bags.
     * dark orange bags contain 2 dark yellow bags.
     * dark yellow bags contain 2 dark green bags.
     * dark green bags contain 2 dark blue bags.
     * dark blue bags contain 2 dark violet bags.
     * dark violet bags contain no other bags.
     * </pre>
     * In this example, a single shiny gold bag must contain 126 other bags.
     * <p/>
     * How many individual bags are required inside your single shiny gold bag?
     *
     * @param lines containing luggage rules
     * @return number of bags that the shiny gold bag contains
     */
    final long second(final List<String> lines) {
        final Map<String, List<String>> bags =
                lines.stream()
                     .map(LUGGAGE_RULE_PATTERN::matcher)
                     .collect(Collectors.toMap(
                             matcher -> Optional.of(matcher.find())
                                                .filter(Boolean.TRUE::equals)
                                                .map(ignore -> matcher.group(2))
                                                .orElseThrow(IllegalStateException::new),
                             matcher -> matcher.results()
                                               .flatMap(match -> IntStream.range(0, Integer.parseInt(match.group(1)))
                                                                          .mapToObj(i -> match.group(2)))
                                               .collect(Collectors.toList())));

        return recursiveLookup("shiny gold", bags, Collectors.toList()).size();
    }

    private Collection<String> recursiveLookup(final String target,
                                               final Map<String, ? extends Collection<String>> bags,
                                               final Collector<String, ?, ? extends Collection<String>> collector) {
        final Collection<String> containers = bags.get(target) == null ? Collections.emptyList() : bags.get(target);
        return Stream.concat(containers.stream(),
                             containers.stream()
                                       .map(container -> recursiveLookup(container, bags, collector))
                                       .flatMap(Collection::stream))
                     .collect(collector);
    }

}
