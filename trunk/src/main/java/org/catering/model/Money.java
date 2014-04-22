package org.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "MONEY")
@XmlRootElement
public class Money implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private Double total;

    @Basic(optional = false)
    @NotNull
    @Column(name = "USED")
    private Double used;

    @Basic(optional = false)
    @NotNull
    @Column(name = "MISSING")
    private Double missing;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User user;

    public Money() {
    }

    public Money(Double total, Double used, Double missing) {
        this.total = total;
        this.used = used;
        this.missing = missing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getUsed() {
        return used;
    }

    public void setUsed(Double used) {
        this.used = used;
    }

    public Double getMissing() {
        return missing;
    }

    public void setMissing(Double missing) {
        this.missing = missing;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!id.equals(money.id)) return false;
        if (!missing.equals(money.missing)) return false;
        if (!total.equals(money.total)) return false;
        if (!used.equals(money.used)) return false;
        if (!user.equals(money.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + total.hashCode();
        result = 31 * result + used.hashCode();
        result = 31 * result + missing.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Money{" +
                "id=" + id +
                ", total=" + total +
                ", used=" + used +
                ", missing=" + missing +
                ", user=" + user +
                '}';
    }
}
