package com.ecommerce.promotion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "promotion_db")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private int product_id;

    private Date start_date;
    private Date end_date;
    private double taxe;

    public Promotion() {
    }

    public Promotion(Date start_date, Date end_date, double taxe) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.taxe = taxe;
    }

    public Promotion(int product_id, Date start_date, Date end_date, double taxe) {
        this.product_id = product_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.taxe = taxe;
    }

    public long getId() {
        return id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public double getTaxe() {
        return taxe;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setTaxe(double taxe) {
        this.taxe = taxe;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", taxe=" + taxe +
                '}';
    }
}
