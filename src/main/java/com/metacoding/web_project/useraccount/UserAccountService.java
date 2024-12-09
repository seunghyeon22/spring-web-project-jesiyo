package com.metacoding.web_project.useraccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
}
