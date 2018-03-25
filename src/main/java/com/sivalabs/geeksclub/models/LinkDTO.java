package com.sivalabs.geeksclub.models;

import com.sivalabs.geeksclub.entities.Link;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class LinkDTO {
    private Long id;
    private String title;
    private String url;
    private LocalDateTime createdOn;
    private Long createdUserId;
    private String createdUserName;
    private String createdUserEmail;
    private Set<String> tags;

    public LinkDTO(Link link) {
        this.id = link.getId();
        this.title = link.getTitle();
        this.url = link.getUrl();
        this.createdOn = link.getCreatedOn();
        this.createdUserId = link.getCreatedBy().getId();
        this.createdUserName = link.getCreatedBy().getName();
        this.createdUserEmail = link.getCreatedBy().getEmail();
        this.tags = link.getTags().stream().map(it -> it.getName()).collect(Collectors.toSet());
    }
}
