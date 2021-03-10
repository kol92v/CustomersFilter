package model;

import lombok.Data;

public @Data class UpdateFile {
    private String name;

    public UpdateFile(String name) {
        this.name = name;
    }
}
