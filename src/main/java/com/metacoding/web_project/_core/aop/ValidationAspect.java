package com.metacoding.web_project._core.aop;
import com.metacoding.web_project._core.error.ex.Exception400;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Aspect
public class ValidationAspect {

    // 행위
    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)") // 포인트컷 자리 (post매핑한 메서드)
    public Object validationCheck(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();

        for (Object arg : args) {
            if(arg instanceof Errors){
                Errors errors = (Errors) arg;

                if(errors.hasErrors()) {
                    String errMsg = errors.getFieldErrors().get(0).getField() +" 은(는) "+ errors.getFieldErrors().get(0).getDefaultMessage();
                    throw new Exception400(errMsg);
                }
            }
        }
        Object ob = jp.proceed(); // 메서드 진행
        return ob;
    }
}
