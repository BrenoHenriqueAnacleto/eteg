package com.breno.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String nome;
}
