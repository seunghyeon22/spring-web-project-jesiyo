package com.metacoding.web_project.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(ReportRepository.class)
@DataJpaTest
public class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

}
