package com.tickets.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "TICKET")
public class Ticket implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TICKET")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "NAME_TICKET")
    private String nameTicket;

    @NotBlank(message = "Name Owner is mandatory")
    @Column(name = "NAME_OWNER")
    private String nameOwner;

    @Column(name = "TEAM_SOLUTION")
    private String teamSolution;

    @NotBlank(message = "Type Incident is mandatory")
    @Column(name = "TYPE_INCIDENT")
    private String typeIncident;

    @NotBlank(message = "Severity Incident is mandatory")
    @Column(name = "SEVERITY_INCIDENT")
    private String severityIncident;

    @Column(name = "VERSION_SOFTWARE_INCIDENT")
    private String versionSoftwareIncident;

    @NotBlank(message = "Description Problem is mandatory")
    @Column(name = "DESCRIPTION_PROBLEM")
    private String descriptionProblem;

    @Column(name = "ATTACHMENT")
    private String attachment;

    @Column(name = "ARCHIVED")
    private String archived;

    @Column(name = "STATUS")
    private String status;

    @JsonIgnore
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", columnDefinition = "timestamp", updatable = false)
    private Date createDate;

    @JsonIgnore
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE", columnDefinition = "timestamp default current_timestamp")
    private Date updateDate;

    @Override
    public Ticket clone() {
        try {
            return (Ticket) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Error al clonar Ticket");
            throw new AssertionError();
        }
    }
}