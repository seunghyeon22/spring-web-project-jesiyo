package com.metacoding.web_project.useraccount;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ConcreteProxy;

@RequiredArgsConstructor
@ConcreteProxy
public class UserAccountController {

    private final UserAccountService userAccountService;


}
