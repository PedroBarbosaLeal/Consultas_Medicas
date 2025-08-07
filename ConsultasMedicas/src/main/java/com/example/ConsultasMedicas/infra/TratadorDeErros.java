package com.example.ConsultasMedicas.infra;


import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EsseIdNaoExiste.class)
    public ResponseEntity<String> tratarIdNaoExistente(EsseIdNaoExiste ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
