package com.example.demo.validations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseErrorValidation {
    // при спринг валидации классов(аннотации-ограничения указанныу в классах Entity) возникают ошибки, этот сервис служит для их обработки

    public ResponseEntity<Object> mapValidationService (BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            // result.getAllErrors() работает не только с полями, но и с объектами
            if (!CollectionUtils.isEmpty(result.getAllErrors())) {
                // работаем с объектами (в нашем случае - несовпадение паролей при регистрации)
                for (ObjectError error : result.getAllErrors()) {
                    errorMap.put(error.getCode(), error.getDefaultMessage());
                }
            }

            // result.getFieldErrors() возвращает ошибки связанные с полями entity (username, password)
            // работаем с полями (проверка ограничений для каждого отдельного поля)
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
