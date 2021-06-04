package models;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="custmer")
@NamedQueries({
@NamedQuery(
        name = "getAllCustmer",
        query = "SELECT c FROM Custmer AS c ORDER BY c.id DESC"
    ),
@NamedQuery(
        name = "getCustmersCount",
        query = "SELECT COUNT(c) FROM Custmer AS c"
        ),
@NamedQuery(
        name = "checkLoginNameAndTell_num",
        query = "SELECT c FROM Custmer AS c WHERE  c.name = :name AND c.tell_num = :tell_num"
    )
})
@Entity
public class Custmer {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="tell_num", nullable=false)
    private String tell_num;

    @Column(name="peoples",nullable=false)
    private String peoples;

    @Column(name="reserve_day", nullable=false)
    private Timestamp reserve_day;

    @Column(name="visit_time",nullable=false)
    private Timestamp visit_time;

    @Column(name="exit_time",nullable=false)
    private Timestamp exit_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTell_num() {
        return tell_num;
    }

    public void setTell_num(String tell_num) {
        this.tell_num = tell_num;
    }

    public String getPeoples() {
        return peoples;
    }

    public void setPeoples(String peoples) {
        this.peoples = peoples;
    }

    public Timestamp getReserve_day() {
        return reserve_day;
    }

    public void setReserve_day(Timestamp reserve_day) {
        this.reserve_day = reserve_day;
    }

    public Timestamp getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(Timestamp visit_time) {
        this.visit_time = visit_time;
    }

    public Timestamp getExit_time() {
        return exit_time;
    }

    public void setExit_time(Timestamp exit_time) {
        this.exit_time = exit_time;
    }

}