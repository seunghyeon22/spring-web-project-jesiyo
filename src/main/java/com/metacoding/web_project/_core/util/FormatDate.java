package com.metacoding.web_project._core.util;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class FormatDate {

    public static String formatRemainingTime(Timestamp timestamp) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime futureTime = timestamp.toLocalDateTime();

        Duration duration = Duration.between(now, futureTime);

        if (duration.isNegative()) {
            return "경매가 종료되었습니다.";
        }

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        if (days > 0) {
            return days + "일 뒤 경매 종료";
        } else if (hours > 0) {
            return hours + "시간 뒤 경매 종료";
        } else if (minutes > 0) {
            return minutes + "분 뒤 경매 종료";
        } else {
            return "곧 경매 종료";
        }
    }
}
