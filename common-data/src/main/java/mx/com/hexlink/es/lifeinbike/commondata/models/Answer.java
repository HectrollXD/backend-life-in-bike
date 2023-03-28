package mx.com.hexlink.es.lifeinbike.commondata.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@Entity
@Table(name = "data_answers")
@EqualsAndHashCode(callSuper = true)
public class Answer extends ControlOfData{
    @Column(name = "answer", nullable = false, updatable = false, length = 256)
    private String answer;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private SecurityQuestion question;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;



    // Setters;
    public void setAnswer(String answer){
        this.answer = answer.trim().toUpperCase();
    }
}
