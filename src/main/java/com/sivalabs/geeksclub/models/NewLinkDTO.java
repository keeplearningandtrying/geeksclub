package com.sivalabs.geeksclub.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewLinkDTO {
    @NotBlank(message = "Title must not be blank")
    private String title;
    @NotBlank(message = "URL must not be blank")
    private String url;
    private String tags;
}
