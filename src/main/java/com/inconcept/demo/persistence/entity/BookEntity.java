package com.inconcept.demo.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "description")
    private String desc;

    @Column(name = "publish_date")
    private String publishDate;

    @Column
    private Double price;

    @OneToMany(mappedBy = "book", targetEntity = RateEntity.class, fetch = FetchType.LAZY)
    private List<RateEntity> listBookRates;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "authorbook",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
   private Set<AuthorEntity> bookAuthors = new HashSet<>();

}
