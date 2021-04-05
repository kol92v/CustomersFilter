package mygroup.dao;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateFinderFolderDate implements UpdateFinder, FileVisitor<Path> {
    //LocalDate - a directory uses a date for its name
    //String - name of UpdateFile
    //Path - paths to updateFiles
    private Map<LocalDate, Map<String, List<Path>>> mapOfPathsUpdateFiles;
    private LocalDate from;
    private LocalDate to;
    private Path pathToUpdateFiles;


    @SneakyThrows(IOException.class)
    public Map<LocalDate, Map<String, List<Path>>> generateMapOfFilesPaths(Path pathToUpdateFiles, LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
        this.pathToUpdateFiles = pathToUpdateFiles;
        mapOfPathsUpdateFiles = new HashMap<>();
        Files.walkFileTree(pathToUpdateFiles, this);
        return mapOfPathsUpdateFiles;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (dir.equals(pathToUpdateFiles)) return FileVisitResult.CONTINUE;
        try {
            LocalDate keyDate = LocalDate.parse(dir.getFileName().toString().trim());
            if (!(keyDate.isAfter(from.minusDays(1L)) && keyDate.isBefore(to.plusDays(1L)))) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            mapOfPathsUpdateFiles.put(keyDate, new HashMap<>());
            return FileVisitResult.CONTINUE;
        } catch (Exception e) {
            return FileVisitResult.SKIP_SUBTREE;
        }

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString().toUpperCase();
        if (!fileName.endsWith(".ANS")) return FileVisitResult.CONTINUE;
        String[] arrayDirectories = file.getParent().toString().replaceAll("/", "\\")
                .split("\\\\");
        String parentDirectoryFile = arrayDirectories[arrayDirectories.length - 1].trim();
        LocalDate keyDate = LocalDate.parse(parentDirectoryFile);
        if (!mapOfPathsUpdateFiles.containsKey(keyDate)) initInnerMap(keyDate);
        Map<String, List<Path>> mapFileNameAndPaths = mapOfPathsUpdateFiles.get(keyDate);
        String baseName = fileName.split("#")[0].trim();
        if (!mapFileNameAndPaths.containsKey(baseName)) initListOfPaths(keyDate, baseName);
        mapFileNameAndPaths.get(baseName).add(file);
        return FileVisitResult.CONTINUE;
    }

    private void initInnerMap(LocalDate keyDate) {
        mapOfPathsUpdateFiles.put(keyDate, new HashMap<>());
    }

    private void initListOfPaths(LocalDate keyDate, String baseName) {
        mapOfPathsUpdateFiles.get(keyDate).put(baseName, new ArrayList<>());
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.err.format("Что-то пошло не так при добавлении файла %s в список", file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
