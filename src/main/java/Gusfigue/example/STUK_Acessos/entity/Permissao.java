package Gusfigue.example.STUK_Acessos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "permissao")
@Table(name = "permissao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permissao")
    private Long id;

    private String nome;

    private String descricao;

    private String ativo;
}