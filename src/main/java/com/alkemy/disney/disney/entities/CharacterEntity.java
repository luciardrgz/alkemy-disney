package com.alkemy.disney.disney.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String story;
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<TitleEntity> titles = new ArrayList<>();
}
