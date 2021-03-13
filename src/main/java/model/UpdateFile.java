package model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Locale;

public @Data class UpdateFile {
    private String name;
    private LocalDate date;

    public UpdateFile(String name) {
        this.name = name.toUpperCase(Locale.ROOT).trim();
    }


}
