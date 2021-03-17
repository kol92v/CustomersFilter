package model.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

class UpdateFinderFolderDateTest {

    @Mock
    UpdateFinderFolderDate updateFinderFolderDate;

    @Test
    void preVisitDirectory() {
//        LocalDate ld = LocalDate.of(2010,12, 31);
//        System.out.println(ld.plusDays(1L));
//
//        ld = LocalDate.parse("2020-10-01");
//        System.out.println(ld);

//        String path = Paths.get(System.getProperty("user.dir")).toString()
//                .replace("/", "\\");
//        System.out.println(path);
//        String[] array = path.split("\\\\");
//        System.out.println(Arrays.toString(array));










    }
    @Mock
    BasicFileAttributes attrs;

    @Test
    void visitFile() {
        updateFinderFolderDate = Mockito.mock(UpdateFinderFolderDate.class);
        attrs = Mockito.mock(BasicFileAttributes.class);
        try {
            updateFinderFolderDate.visitFile(Paths.get(System.getProperty("user.dir")), attrs);
        } catch (IOException e) {
            System.out.println("error in test");
        }
    }
}