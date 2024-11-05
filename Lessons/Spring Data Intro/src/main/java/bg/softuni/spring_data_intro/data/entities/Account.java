package bg.softuni.spring_data_intro.data.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{

    @Column()
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;  // user_id -->> reference to his id;

    public Account() {
        this.user = new User();
    }

    public Account(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
        this.user = new User();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
