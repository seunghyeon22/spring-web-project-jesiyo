package com.metacoding.web_project.useraccount;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class UserAccountRepository {
    private final EntityManager em;
}
