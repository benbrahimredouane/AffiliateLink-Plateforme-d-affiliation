package com.affiliatelink.offer.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "offer_tag")
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true , length = 50)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Offer> offers = new HashSet<>();

    public Tag(String name){
        this.name = name.trim().toLowerCase(Locale.ROOT);
    }

    @Override
    public boolean equals(Object o){
        return this == o || (o instanceof Tag other && Objects.equals(name , other.name));
    }
    @Override
    public int hashCode(){
        return Objects.hash(
                name
        );
    }
}
