package com.fbank.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true,length = 30)
    private String username;
    @Column(nullable = false, length = 30)
    private String password;
    @Column(name = "times_login_failed")
    private Integer timesLoginFailed;
    @Column(name = "time_blocked")
    private Date timeBlocked;
    public enum Status{
        ACTIVED(1),DEACTIVED(-1);
        private  int value;

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        Status(int value) {
            this.value = value;
        }
    }
    @Column
    private Integer status ;
    @OneToOne(mappedBy = "account",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private AccountInformation accountInformation;
}
