package model.dao;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Map;

public interface UpdateFinder {
    Map<LocalDate, Map<String, Path>> generateMapOfFilesPaths(Path pathToUpdateFiles, LocalDate from, LocalDate to);
}
