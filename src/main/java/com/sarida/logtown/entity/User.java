package com.sarida.logtown.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.LinkedList;
import java.util.Queue;

public class User {


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Queue<Password> passwordQueue = new LinkedList<>();
    private static final int MAX_HISTORY = 3;

    public void addPassword(Password password) {
        passwordQueue.offer(password);
        if(passwordQueue.size() > MAX_HISTORY) {
            passwordQueue.poll();
        }
    }

    public boolean isRecentPassword(Password password) {
        return passwordQueue.contains(password);
    }
}
