package model;

import lombok.Data;

import java.time.LocalDate;

public @Data class UpdateFile {
    private String name;
    private LocalDate date;

    public UpdateFile(String name) {
        this.name = name.toUpperCase().trim();
    }


}
