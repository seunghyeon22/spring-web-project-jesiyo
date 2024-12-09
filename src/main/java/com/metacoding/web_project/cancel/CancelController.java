package com.metacoding.web_project.cancel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class CancelController {
    private final CancelService cancelService;
}
