package com.wGory.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Entity
@Table(name = "WYCIECZKA")
@Data
public class Wycieczka {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nullable
    private String nazwa;
    @Nullable
    private LocalDate planowanaData;
    @Nullable
    private LocalDate dataUtworzenia = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private StatusWycieczki status = StatusWycieczki.Zaplanowana;

    @ManyToOne
    private OdznakaTurysty odznakaTurysty;

    @OneToMany(mappedBy = "wycieczka")
    private List<OdcinekWycieczki> odcinkiWycieczki;

    private Integer punktyWycieczki;

    @PostLoad
    @PreUpdate
    @PrePersist
    private void preOperation() {
        punktyWycieczki = odcinkiWycieczki.stream()
                .filter((OdcinekWycieczki ow) -> ow.getCzyOdbyta().equals(TRUE))
                .mapToInt((OdcinekWycieczki ow) -> ow.getOdcinekTrasy().getPunkty())
                .sum();
    }
}
