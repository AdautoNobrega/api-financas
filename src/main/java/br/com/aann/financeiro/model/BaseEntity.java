package br.com.aann.financeiro.model;

import org.hibernate.engine.spi.PersistentAttributeInterceptable;
import org.hibernate.engine.spi.PersistentAttributeInterceptor;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseEntity implements PersistentAttributeInterceptable {
    @Transient
    protected PersistentAttributeInterceptor persistentAttributeInterceptor;

    @Override
    public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
        return persistentAttributeInterceptor;
    }

    @Override
    public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor persistentAttributeInterceptor) {
        this.persistentAttributeInterceptor = persistentAttributeInterceptor;
    }

}
