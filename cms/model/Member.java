package com.yourcompany.cms.model;

import javax.persistence.*;

import lombok.*;

@Entity  // This marks Customer class as an entity
@Getter @Setter // This makes all fields below publicly accessible
public class Member {
    @Id  // The number property is the key property. Keys are required by default
    @Column(length=6)  // The column length is used at the UI level and the DB level
    int number;
	    
    @Column(length=10)
    String firstname;
    
    @Column(length=10)
    String surename;
}
