package com.sistema.autolaudo.model.gerenteregional;

import com.sistema.autolaudo.model.perito.Perito;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gerente_regional")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GerenteRegional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_perito", referencedColumnName = "id")
    private Perito perito;

    @Column(name = "regional", nullable = false)
    private String regional;

    @Column(name = "uuid", nullable = false)
    private String uuid;
}
