package view.dtoVw;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public @Data class ClientVw {
    private String name;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<BaseVw> baseVwList = new ArrayList<>();

    public ClientVw(String name) {
        this.name = name.toLowerCase(Locale.ROOT).trim();
    }

    public void setName(String name) {
        this.name = name.toLowerCase(Locale.ROOT).trim();
    }
}
