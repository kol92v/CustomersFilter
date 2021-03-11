package model;


import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public @Data class Customer {
    private String name;
    private List<UpdateFile> updateFileList = new ArrayList<>();
    private LocalDate from;
    private LocalDate to;

    public Customer(String name) {
        this.name = name.toLowerCase().trim();
    }
}
