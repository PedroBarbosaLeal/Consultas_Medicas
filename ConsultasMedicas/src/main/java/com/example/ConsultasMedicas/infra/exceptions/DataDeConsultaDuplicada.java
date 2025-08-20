package com.example.ConsultasMedicas.infra.exceptions;

public class DataDeConsultaDuplicada extends RuntimeException {
    public DataDeConsultaDuplicada(String message) {
        super(message);
    }
}
