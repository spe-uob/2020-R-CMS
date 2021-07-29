package com.yourcompany.cms.model;
 
import java.time.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

import lombok.*;
 
@Entity  // This marks Customer class as an entity
@Getter @Setter // This makes all fields below publicly accessible
public class Form {
	
	
    @Id
    @GeneratedValue(generator="system-uuid")
    @Hidden
    @GenericGenerator(name="system-uuid", strategy="uuid")
    @Column(length=32)
    String oid;

    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class) // Current year
    int year;
 
    @Column(length=6)
    int number;
    
    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Current date
    LocalDate date;
 
 
    @Column(length=20)  // The column length is used at the UI level and the DB level
    @Required  // A validation error will be shown if the name property is left empty
    String clientname;
    
    @Stereotype("EMAIL")
    String email;
    
    @Stereotype("WEBURL")
    String URL;
   
    @ManyToOne(fetch=FetchType.LAZY, optional=false) // Customer is required
    Member member;
    
    @Stereotype("MEMO") // This is for a big text, a text area or equivalent will be used
    String remarks;
    
    @Stereotype("FILE")
    String files;
    
}
