/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dishelt
 */
@Entity
@Table(name="activities")
public class Activity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "description")
    private String description;
    
    @Column(name="deadline")
    private Date deadline;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDeadline() {
        return deadline;
    }        

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }       

    @Override
    public String toString() {
        return "Activity{" + "id=" + id + ", description=" + description + ", deadline=" + deadline + '}';
    }

           
    
}
