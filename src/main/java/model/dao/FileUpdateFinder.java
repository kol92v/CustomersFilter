package model.dao;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public @Data
class FileUpdateFinder implements FileVisitor<Path> {
    //LocalDate - a directory uses a date for its name
    //String - name of UpdateFile
    //Path - paths to updateFiles
    private Map<LocalDate, Map<String, Path>> mapOfPathsUpdateFiles = new HashMap<>();
    private LocalDate from;
    private LocalDate to;
    private Path pathToUpdateFiles;

    public FileUpdateFinder(LocalDate from, LocalDate to, Path pathToUpdateFiles) {
        this.from = from;
        this.to = to;
        this.pathToUpdateFiles = pathToUpdateFiles;
    }


    @SneakyThrows(IOException.class)
    public void generateMapOfFilesPaths() {
        Files.walkFileTree(pathToUpdateFiles, this);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

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
        String parentDirectoryFile = arrayDirectories[arrayDirectories.length-1].trim();
        LocalDate keyDate = LocalDate.parse(parentDirectoryFile);
        mapOfPathsUpdateFiles.get(keyDate).put(fileName.split("#")[0], file);
        return FileVisitResult.CONTINUE;
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
