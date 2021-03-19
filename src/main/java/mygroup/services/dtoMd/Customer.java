package mygroup.services.dtoMd;


import lombok.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@Builder
public class Customer implements Serializable, Cloneable {
    private String name;
    private List<UpdateFile> updateFileList = new ArrayList<>();
    private LocalDate from;
    private LocalDate to;

    public Customer(String name) {
        this.name = name.toLowerCase(Locale.ROOT).trim();
    }

    @SneakyThrows({IOException.class, ClassNotFoundException.class})
    @Override
    public Customer clone() throws CloneNotSupportedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        return (Customer) ois.readObject();
    }
}
