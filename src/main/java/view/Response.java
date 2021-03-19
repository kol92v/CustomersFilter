package view;

import lombok.*;
import view.dtoVw.ClientVw;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private List<ClientVw> clientVwList = new ArrayList<>();

    private TypeMessage typeMessage;


}
