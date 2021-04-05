package mygroup.dao;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface UpdateFinder {
    Map<LocalDate, Map<String, List<Path>>> generateMapOfFilesPaths(Path pathToUpdateFiles, LocalDate from, LocalDate to);
}
