package com.metacoding.web_project._core.error;

import com.metacoding.web_project._core.error.ex.Exception404;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception404.class)
    public String handleException404(Exception404 e) {
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }
}
