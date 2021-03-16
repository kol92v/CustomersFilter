package view.dtoVw;

import lombok.Data;

public @Data class BaseVw {
    private String name;

    public BaseVw(String name) {
        this.name = name;
    }
}
