package com.sivalabs.geeksclub.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
@Setter
@Getter
public class Tag {
    @Id
    @SequenceGenerator(name = "tag_generator", sequenceName = "tag_sequence", initialValue = 10)
    @GeneratedValue(generator = "tag_generator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private Set<Link> links;
}
