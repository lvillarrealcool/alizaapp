package com.alianza.alianzapp.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_human")
@Builder
@Getter
@Setter
public class Human {

    private static final long serialVersionUID= 1L;

    @Id
    @SequenceGenerator(name = "mysequence", sequenceName = "mysequence", allocationSize = 1)
    @GeneratedValue(generator = "mysequence")
    @Column(name = "id")
    private long empId;

    @Column(name = "dna")
    private String dna;

    @Column(name = "mutante")
    private boolean isMutant;

}
