package view;

import lombok.Builder;
import view.dtoVw.ClientVw;

import java.util.List;

public @Builder class Response {
    private List<ClientVw> clientVwList;

    private TypeResponse typeResponse;

    public enum TypeResponse {
        UpdateBaseList,
        UpdateClientList
    }
}
