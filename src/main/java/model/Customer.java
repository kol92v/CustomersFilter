package model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class Customer {
    private String name;
    private List<UpdateFile> updateFileList = new ArrayList<>();

}
