package com.sarida.logtown.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.LinkedList;
import java.util.Queue;

public class User {


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Queue<Password> passwordQueue = new LinkedList<>();

}
