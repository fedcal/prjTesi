package com.mspazienti.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomErrorAttributes extends DefaultErrorAttributes {
    public static final String DEFAULT_ERROR_MESSAGE = "Errore interno del sistema";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        Throwable error = this.getError(webRequest);
        BindingResult result = customExtractBindingResult(error);
        Optional.ofNullable(result)
                .filter(r -> r.hasErrors())
                .ifPresent(r ->
                        errorAttributes.put("errors", r.getFieldErrors()
                                .stream()
                                .map(fe -> new FieldError(fe.getField(), fe.getDefaultMessage()))
                                .collect(Collectors.toList())
                        ));

        Integer status = (Integer) errorAttributes.get("status");
        if (status >= 500 && status <= 599) {
            errorAttributes.put("message", DEFAULT_ERROR_MESSAGE);
        }

        errorAttributes.remove("exception");

        return errorAttributes;
    }

    private BindingResult customExtractBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult) error;
        } else {
            return error instanceof MethodArgumentNotValidException ? ((MethodArgumentNotValidException) error).getBindingResult() : null;
        }
    }

    public class FieldError {

        private String field;
        private String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }
}
