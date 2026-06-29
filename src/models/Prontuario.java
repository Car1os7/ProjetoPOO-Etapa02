package model;

import java.util.ArrayList;
import java.util.List;

public class Prontuario {
    
    private String observacoes;
    private String diagnostico;
    private List<String> procedimentos;
    private String dataRegistro;
    private List<String> infoEspecialidade; // Informações específicas da especialidade
    
    // Construtor package-private - só pode ser criado dentro do pacote
    // COMPOSIÇÃO: só Atendimento pode criar Prontuario
    Prontuario(String observacoes, String dataRegistro) {
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new ArrayList<>();
        this.dataRegistro = dataRegistro;
        this.infoEspecialidade = new ArrayList<>();
    }
}