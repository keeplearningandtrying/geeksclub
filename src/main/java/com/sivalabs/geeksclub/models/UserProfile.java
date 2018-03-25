package com.sivalabs.geeksclub.models;

import com.sivalabs.geeksclub.entities.Link;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String name;
    private String email;
    private String website;
    private String bio;
    private List<Link> postedLinks;
}
