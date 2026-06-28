// ARQUIVO: src/models/Profissional.java
package models;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE ABSTRATA PROFISSIONAL - Especializacao de Pessoa
 * 
 * Conceitos aplicados:
 * - HERANCA: extends Pessoa
 * - CLASSE ABSTRATA: nao pode ser instanciada
 * - ENCAPSULAMENTO: atributos privados com validacao
 * - AGREGACAO: Profissional possui HorariosDisponiveis
 */
public abstract class Profissional extends Pessoa {
    
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private List<HorarioDisponivel> horarios; // AGREGACAO
    
    //  CONSTRUTORES SOBRECARREGADOS 
    
    public Profissional(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        setEspecialidade(especialidade); // USANDO SETTER COM VALIDACAO
        this.registroProfissional = "";
        this.valorConsulta = 0.0;
        this.horarios = new ArrayList<>();
    }
    
    public Profissional(String nome, String cpf, String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, cpf);
        setEspecialidade(especialidade); // USANDO SETTER COM VALIDACAO
        this.registroProfissional = registroProfissional;
        setValorConsulta(valorConsulta); // USANDO SETTER COM VALIDACAO
        this.horarios = new ArrayList<>();
    }
    
    public Profissional(String nome, String cpf, String especialidade, String registroProfissional, 
                        double valorConsulta, List<HorarioDisponivel> horarios) {
        super(nome, cpf);
        setEspecialidade(especialidade); // USANDO SETTER COM VALIDACAO
        this.registroProfissional = registroProfissional;
        setValorConsulta(valorConsulta); // USANDO SETTER COM VALIDACAO
        this.horarios = (horarios != null) ? horarios : new ArrayList<>();
    }
    
    // GETTERS E SETTERS 
    // ENCAPSULAMENTO: acesso controlado aos atributos
    
    public String getEspecialidade() {
        return especialidade;
    }
    
    public void setEspecialidade(String especialidade) {
        // VALIDACAO: especialidade deve ser valida
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialidade nao pode ser vazia!");
        }
        String espNormalizada = especialidade.trim().toLowerCase();
        if (!especialidadeValida(espNormalizada)) {
            throw new IllegalArgumentException("Especialidade invalida! Opcoes: clinica geral, fisioterapia, psicologia, nutricao");
        }
        this.especialidade = espNormalizada;
    }
    
    public String getRegistroProfissional() {
        return registroProfissional;
    }
    
    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }
    
    public double getValorConsulta() {
        return valorConsulta;
    }
    
    public void setValorConsulta(double valorConsulta) {
        // VALIDACAO: valor nao pode ser negativo
        if (valorConsulta < 0) {
            throw new IllegalArgumentException("Valor da consulta nao pode ser negativo!");
        }
        this.valorConsulta = valorConsulta;
    }
    
    public List<HorarioDisponivel> getHorarios() {
        return horarios;
    }
    
    public void setHorarios(List<HorarioDisponivel> horarios) {
        this.horarios = (horarios != null) ? horarios : new ArrayList<>();
    }
}