package br.com.aann.financeiro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@NamedEntityGraph(name = "subcategorias-lancamentos", attributeNodes = {@NamedAttributeNode(value = "subcategorias", subgraph = "lancamentos")})
public class CategoriaEntity implements Serializable {

    private static final long serialVersionUID = 8334263449759112086L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categoria")
    @ToString.Exclude
    private Set<SubcategoriaEntity> subcategorias;

    public CategoriaEntity(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaEntity that = (CategoriaEntity) o;
        return id.equals(that.id) && nome.equals(that.nome) && Objects.equals(subcategorias, that.subcategorias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, subcategorias);
    }
}