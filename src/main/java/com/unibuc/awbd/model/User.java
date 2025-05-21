package com.unibuc.awbd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    @NotBlank
    private String password;

    private boolean enabled = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id")
    private Authority authority;
}
