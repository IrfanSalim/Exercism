import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GrepTool {

    String grep(String regex, Collection<String> flags, Collection<String> files) {
        boolean flagLineNumber       = flags.contains("-n");
        boolean flagFilesWithMatches = flags.contains("-l");
        boolean flagIgnoreCase       = flags.contains("-i");
        boolean flagInvertMatch      = flags.contains("-v");
        boolean flagLineRegexp       = flags.contains("-x");
        boolean flagWithFilename     = files.size() > 1;

        Pattern pattern = Pattern.compile(Pattern.quote(regex), flagIgnoreCase ? Pattern.CASE_INSENSITIVE : 0);
        Collection<CharSequence> matches = new ArrayDeque<>();

        for (String filename : files) {
            try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {
                String line;
                for (int lineNumber = 1; (line = reader.readLine()) != null; lineNumber++) {
                    Matcher matcher = pattern.matcher(line);
                    if (flagInvertMatch ^ (flagLineRegexp ? matcher.matches() : matcher.find())) {
                        if (flagFilesWithMatches) {
                            matches.add(filename);
                            break;
                        } else {
                            StringBuilder format = new StringBuilder();
                            if (flagWithFilename) format.append(filename).append(':');
                            if (flagLineNumber) format.append(lineNumber).append(':');
                            matches.add(format.append(line));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return String.join("\n", matches);
    }

}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

public class GrepTool {

    private String pattern;
    private List<String> flags;
    private SortedSet<File> files;
    private Predicate<String> filter;

    public String grep(String pattern, List<String> flags, List<String> fileNames) {
        initFields(pattern, flags, fileNames);
        handleMultipleFiles();
        handleLineNumberFlag();

        return needOnlyFileNames() ? getMatchingFiles() : getMatchingLines();
    }

    private void initFields(String pattern, List<String> flags, List<String> fileNames) {
        this.pattern = pattern;
        this.flags = flags;
        files = fileNames.stream().map(File::new).collect(toCollection(TreeSet::new));
        initFilter();
    }

    private void initFilter() {
        filter = line -> line.contains(pattern);
        if(flags.contains("-i")) {
            filter = line -> line.toLowerCase().contains(pattern.toLowerCase());
        }
        if(flags.contains("-x")) {
            filter = line -> line.equals(pattern);
        }
        if(flags.contains("-x") && flags.contains("-i")) {
            filter = line -> line.equalsIgnoreCase(pattern);
        }
        if(flags.contains("-v")) {
            filter = filter.negate();
        }
    }

    private void handleMultipleFiles() {
        if(files.size() > 1) {
            files.forEach(File::appendFileName);
        }
    }

    private void handleLineNumberFlag() {
        if(flags.contains("-n")) {
            files.forEach(File::appendNumbers);
        }
    }

    private boolean needOnlyFileNames() {
        return flags.contains("-l");
    }

    private String getMatchingFiles() {
        return files.stream()
                .filter(File::isMatching)
                .map(file -> file.name)
                .collect(joining("\n"));
    }

    private String getMatchingLines() {
        return files.stream()
                .map(File::getMatchingLines)
                .flatMap(List::stream)
                .collect(joining("\n"));
    }

     class File implements Comparable<File> {

        private final String name;
        private final List<String> lines;

        private Function<String, String> appendNumbers = identity();
        private Function<String, String> appendName = identity();

        File(String name) {
            this.name = name;

            Path path = Paths.get(name);
            try(Stream<String> lines = Files.lines(path)) {
                this.lines = lines.toList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        List<String> getMatchingLines() {
            return lines.stream()
                    .filter(filter)
                    .map(appendNumbers)
                    .map(appendName)
                    .toList();
        }

        boolean isMatching() {
            return lines.stream().anyMatch(filter);
        }

        void appendNumbers() {
            appendNumbers = line -> 1 + lines.indexOf(line) + ":" + line;
        }

        void appendFileName() {
            appendName = line -> name + ":" + line;
        }


        @Override
        public int compareTo(File other) {
            return name.compareTo(other.name);
        }
    }

}