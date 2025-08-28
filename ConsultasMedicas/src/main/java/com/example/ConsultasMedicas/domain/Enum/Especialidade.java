package com.example.ConsultasMedicas.domain.Enum;

public enum Especialidade {
    CARDIOLOGISTA(500.00),
    NUTRICIONISTA(400.00),
    CLINICO_GERAL(200.00),
    UROLOGISTA(600.00);

    private final double valor;

    Especialidade(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
