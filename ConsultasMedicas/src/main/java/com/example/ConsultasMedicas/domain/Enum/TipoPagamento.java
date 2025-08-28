package com.example.ConsultasMedicas.domain.Enum;

public enum TipoPagamento {
    PIX(0.10),
    PLANO(0.30),
    DEBITO(0.5),
    CREDITO(0.0);

    private final double desconto;


    TipoPagamento(double desconto) {
        this.desconto = desconto;
    }
    
    public double aplicarDesconto(double valor) {
        if (valor < 0) {
            return 0;
        }
        double descontoAplicado = valor * this.desconto;
        return valor - descontoAplicado;
    }

    public double getDesconto() {
        return desconto;
    }

}
