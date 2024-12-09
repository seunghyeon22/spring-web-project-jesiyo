package com.metacoding.web_project.cancel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CancelService {
    private final CancelRepository cancelRepository;
}
