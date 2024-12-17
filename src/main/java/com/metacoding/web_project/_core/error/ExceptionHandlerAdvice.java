package com.metacoding.web_project._core.error;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project._core.error.ex.*;
import com.metacoding.web_project.report.ReportRequest;
import com.metacoding.web_project.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception400.class)
    public String err400(Exception400 e) {
        String message = e.getMessage().replace("'", "\\'");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", message);

        return body;
    }

    @ResponseBody
    @ExceptionHandler(Exception401.class)
    public String err401(Exception401 e) {
        String message = e.getMessage().replace("'", "\\'");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", message);

        return body;
    }

    @ResponseBody
    @ExceptionHandler(Exception403.class)
    public String err403(Exception403 e) {
        String message = e.getMessage().replace("'", "\\'");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", message);

        return body;
    }


    @ResponseBody
    @ExceptionHandler(Exception404.class)
    public String err404(Exception404 e) {
        String message = e.getMessage().replace("'", "\\'");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", message);

        return body;
    }

    @ResponseBody
    @ExceptionHandler(Exception500.class)
    public String err500(Exception500 e) {
        String message = e.getMessage().replace("'", "\\'");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", message);

        return body;
    }


    // Json 응답말고 단순 평문으로도 해결되어 아래와 같이 작성
    @ExceptionHandler(Exception400NotHTML.class)
    public ResponseEntity<?> Exception400NotHTML(Exception400NotHTML ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
