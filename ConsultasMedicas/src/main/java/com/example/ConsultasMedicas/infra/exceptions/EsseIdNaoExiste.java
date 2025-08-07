package com.example.ConsultasMedicas.infra.exceptions;

public class EsseIdNaoExiste extends RuntimeException {
    public EsseIdNaoExiste(String message) {
        super(message);
    }
}
