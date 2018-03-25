package com.sivalabs.geeksclub.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "links")
@Setter
@Getter
public class Link {
    @Id
    @SequenceGenerator(name = "link_generator", sequenceName = "link_sequence", initialValue = 10)
    @GeneratedValue(generator = "link_generator")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String  url;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "link_tag", joinColumns = {
            @JoinColumn(name = "link_id", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "ID")}
            )
    private Set<Tag> tags;

    @JoinColumn(nullable = false, name = "created_by")
    @ManyToOne()
    private User createdBy;

    @Column(nullable = false)
    private LocalDateTime createdOn = LocalDateTime.now();
}
