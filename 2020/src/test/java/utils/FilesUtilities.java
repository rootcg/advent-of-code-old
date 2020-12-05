package utils;

import java.nio.file.Path;

public class FilesUtilities {

    public static Path getResource(String path) {
        return Path.of(ClassLoader.getSystemClassLoader().getResource(path).getPath());
    }

}
