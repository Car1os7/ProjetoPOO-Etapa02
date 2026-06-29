package model;

import java.util.ArrayList;
import java.util.List;

public class Convenio {
    
    private String nome;
    private double percentualCobertura; // 0.0 a 1.0
    private List<String> especialidadesCobertas;
    
    // ========== CONSTRUTORES ==========
    
    public Convenio(String nome, double percentualCobertura) {
        this.nome = nome;
        setPercentualCobertura(percentualCobertura);
        this.especialidadesCobertas = new ArrayList<>();
    }
    
    public Convenio(String nome, double percentualCobertura, List<String> especialidadesCobertas) {
        this(nome, percentualCobertura);
        if (especialidadesCobertas != null) {
            this.especialidadesCobertas = new ArrayList<>(especialidadesCobertas);
        }
    }
    
    // ========== GETTERS E SETTERS ==========
    
    public String getNome() { return nome; }
    
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do convênio não pode ser vazio!");
        }
        this.nome = nome;
    }
    
    public double getPercentualCobertura() { return percentualCobertura; }
    
    public void setPercentualCobertura(double percentualCobertura) {
        // VALIDAÇÃO: percentual entre 0 e 1
        if (percentualCobertura < 0 || percentualCobertura > 1) {
            throw new IllegalArgumentException("Percentual de cobertura deve estar entre 0 e 1!");
        }
        this.percentualCobertura = percentualCobertura;
    }
    
    public List<String> getEspecialidadesCobertas() { 
        return new ArrayList<>(especialidadesCobertas); 
    }
}