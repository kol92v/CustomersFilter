package view.dtoVw;

import lombok.Data;

import java.util.Locale;

public @Data class BaseVw {
    private String name;

    public BaseVw(String name) {
        this.name = name.toUpperCase(Locale.ROOT).trim();
    }

    public void setName(String name) {
        this.name = name.toUpperCase(Locale.ROOT).trim();
    }
}
