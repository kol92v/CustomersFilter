package mygroup.services.dtoMd;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;

public @Data class UpdateFile implements Serializable {
    private String name;
    private LocalDate date;

    public UpdateFile(String name) {
        this.name = name.toUpperCase(Locale.ROOT).trim();
    }


}
