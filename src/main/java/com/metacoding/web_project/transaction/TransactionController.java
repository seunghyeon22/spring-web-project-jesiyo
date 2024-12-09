package com.metacoding.web_project.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class TransactionController {
    private final TransactionService transactionService;
}
