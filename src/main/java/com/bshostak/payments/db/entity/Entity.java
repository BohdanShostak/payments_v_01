package com.bshostak.payments.db.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 *
 * @author B.Shostak
 */

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 4611256478544556423L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity [id=" + id + "]";
    }

}
