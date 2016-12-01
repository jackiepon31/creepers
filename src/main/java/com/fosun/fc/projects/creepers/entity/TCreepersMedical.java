package com.fosun.fc.projects.creepers.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the T_CREEPERS_MEDICAL database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_MEDICAL")
@NamedQuery(name = "TCreepersMedical.findAll", query = "SELECT t FROM TCreepersMedical t")
public class TCreepersMedical extends com.fosun.fc.modules.entity.BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_MEDICAL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_MEDICAL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_MEDICAL_ID_GENERATOR")
    private long id;

    @Lob
    private String content;

    @Column(name = "KEY")
    private String key;

    @Column(name = "TYPE")
    private String type;

    public TCreepersMedical() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
