package com.user.user_app.common.exception.interfaces.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ExceptionResponseDto implements Serializable {
    @Setter(AccessLevel.NONE)
    private Date timestamp = new Date();

    private String resource;

    private String method;

    private Integer statusCode;

    private String reasonPhrase;

    private String title;

    private String summary;

    @Setter(AccessLevel.NONE)
    private List<String> details = new ArrayList<>();
}
