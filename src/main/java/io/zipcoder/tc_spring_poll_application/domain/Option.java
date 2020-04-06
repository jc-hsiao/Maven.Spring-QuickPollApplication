package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;

@Entity
public class Option {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "OPTION_ID")
    Long id;
    @Column(name = "OPTION_VALUE")
    String value;

    public Option(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
