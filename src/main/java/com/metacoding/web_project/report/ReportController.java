package com.metacoding.web_project.report;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ConcreteProxy;

@RequiredArgsConstructor
@ConcreteProxy
public class ReportController {
    private final ReportService reportService;
}
