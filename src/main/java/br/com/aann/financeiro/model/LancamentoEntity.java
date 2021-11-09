package br.com.aann.financeiro.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "lancamento")
@Getter
@Setter
@RequiredArgsConstructor
public class LancamentoEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4031898198186313308L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private BigDecimal valor;

    private LocalDate data = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subcategoria")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @ToString.Exclude
    private SubcategoriaEntity subcategoria;

    private String comentario;

    public SubcategoriaEntity getSubcategoria() {
        return super.persistentAttributeInterceptor != null ?
                (SubcategoriaEntity) super.persistentAttributeInterceptor.readObject(this, "subcategoria",
                        this.subcategoria) : this.subcategoria;
    }

    public void setSubcategoria(SubcategoriaEntity subcategoria) {
        this.subcategoria = super.persistentAttributeInterceptor != null ?
                (SubcategoriaEntity) super.persistentAttributeInterceptor.writeObject(this, "subcategoria",
                        this.subcategoria, subcategoria) : subcategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LancamentoEntity that = (LancamentoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, data, subcategoria, comentario);
    }
}
