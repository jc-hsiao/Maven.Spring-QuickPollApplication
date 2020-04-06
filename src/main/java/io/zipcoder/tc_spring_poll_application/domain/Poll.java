package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Poll {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "POLL_ID")
    Long id;

    @Column(name = "QUESTION")
    @NotEmpty
    String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLL_ID")
    @OrderBy
    @Size(min=2, max = 6)
    Set<Option> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
