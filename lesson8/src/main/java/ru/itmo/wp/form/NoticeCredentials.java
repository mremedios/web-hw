package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("unused")
public class NoticeCredentials {
    @NotNull
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 300)
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
