package com.fbank.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account_info")
public class AccountInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullName")
    private String fullName;
    enum Gender{
        MALE(1),FEMALE(2),OTHER(0);
        private int value;

        Gender(int value) {
            this.value = value;
        }
    }
    private Integer gender;
    private String phone;
    private String address;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String introduction;
    @OneToOne(fetch = FetchType.EAGER)
    private Account account;
}
