package br.com.aann.financeiro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subcategoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "sub-all", attributeNodes = {@NamedAttributeNode("lancamentos"), @NamedAttributeNode("categoria")})
public class SubcategoriaEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4892082116919313853L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @ToString.Exclude
    private CategoriaEntity categoria;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subcategoria")
    @ToString.Exclude
    private Set<LancamentoEntity> lancamentos;

    public SubcategoriaEntity(String nome, CategoriaEntity categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public SubcategoriaEntity(Long idSubcategoria) {
        this.id = idSubcategoria;
    }

    public CategoriaEntity getCategoria() {
        return super.persistentAttributeInterceptor != null ?
                (CategoriaEntity) super.persistentAttributeInterceptor.readObject(this, SubcategoriaEntity_.CATEGORIA,
                        this.categoria) : this.categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = super.persistentAttributeInterceptor != null ?
                (CategoriaEntity) super.persistentAttributeInterceptor.writeObject(this, SubcategoriaEntity_.CATEGORIA,
                        this.categoria, categoria) : categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubcategoriaEntity that = (SubcategoriaEntity) o;
        return id.equals(that.id) && nome.equals(that.nome) && categoria.equals(that.categoria) && Objects.equals(
                lancamentos, that.lancamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, categoria, lancamentos);
    }
}
