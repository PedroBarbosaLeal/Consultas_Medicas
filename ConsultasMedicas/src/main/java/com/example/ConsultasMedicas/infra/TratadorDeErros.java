package com.example.ConsultasMedicas.infra;

import com.example.ConsultasMedicas.infra.exceptions.DataDeConsultaDuplicada;
import com.example.ConsultasMedicas.infra.exceptions.EsseIdNaoExiste;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EsseIdNaoExiste.class)
    public ResponseEntity<String> tratarIdNaoExistente(EsseIdNaoExiste ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> TratarBadRequest(MethodArgumentNotValidException ex) {

        List<String> erros = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity TratarDuplicidade(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity TratarDataDeConsultaDuplicada(DataDeConsultaDuplicada ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
