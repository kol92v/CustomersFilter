package mygroup.services.dtoMd;


import lombok.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
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

    public static Builder builder() {
        return new Customer.Builder();
    }

    public static class Builder {
        private String name;
        private LocalDate from;
        private LocalDate to;
        private List<UpdateFile> updateFileList;

        private Builder() {}

        public Builder name(String name) {
            this.name = name.toLowerCase(Locale.ROOT).trim();
            return this;
        }

        public Builder from(LocalDate from) {
            this.from = from;
            return this;
        }

        public Builder to(LocalDate to) {
            this.to = to;
            return this;
        }

        public Builder updateFileList(List<UpdateFile> updateFiles) {
            this.updateFileList = updateFiles;
            return this;
        }

        public Customer build() {
            Customer customer = new Customer(this.name);
            customer.from = this.from;
            customer.to = this.to;
            return customer;
        }

    }
}
