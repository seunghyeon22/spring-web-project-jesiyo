package com.metacoding.web_project.bid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BidService {
    private final BidRepository bidRepository;
}
