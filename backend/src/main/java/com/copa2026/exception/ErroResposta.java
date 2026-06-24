package com.copa2026.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Estrutura padronizada de resposta de erro retornada pela API
 * em todos os cenários de falha (404, 400, 500, etc).
 */
public class ErroResposta {

    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String mensagem;
    private Map<String, String> validacoes; // usado apenas em erros de validação (400)

    public ErroResposta(int status, String erro, String mensagem) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
    }

    public ErroResposta(int status, String erro, String mensagem, Map<String, String> validacoes) {
        this(status, erro, mensagem);
        this.validacoes = validacoes;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Map<String, String> getValidacoes() {
        return validacoes;
    }
}
