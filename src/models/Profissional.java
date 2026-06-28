// ARQUIVO: src/models/Profissional.java
package models;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE ABSTRATA PROFISSIONAL - Especializacao de Pessoa
 * 
 * Conceitos aplicados:
 * - HERANCA: extends Pessoa (Profissional e uma Pessoa)
 * - CLASSE ABSTRATA: nao pode ser instanciada
 * - METODO ABSTRATO: registrarEspecifico() - obriga subclasses a implementarem
 * - AGREGACAO: Profissional possui HorariosDisponiveis (horarios existem independentemente)
 * - SOBRESCRITA: @Override do metodo exibirResumo()
 * - ENCAPSULAMENTO: atributos privados com validacao
 */
public abstract class Profissional extends Pessoa {
    
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private List<HorarioDisponivel> horarios; // AGREGACAO: horarios existem independentemente
    
    // CONSTRUTORES SOBRECARREGADOS 
    
    public Profissional(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        this.registroProfissional = "";
        this.valorConsulta = 0.0;
        this.horarios = new ArrayList<>();
    }
    
    public Profissional(String nome, String cpf, String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        this.registroProfissional = registroProfissional;
        setValorConsulta(valorConsulta);
        this.horarios = new ArrayList<>();
    }
    
    public Profissional(String nome, String cpf, String especialidade, String registroProfissional, 
                        double valorConsulta, List<HorarioDisponivel> horarios) {
        super(nome, cpf);
        setEspecialidade(especialidade);
        this.registroProfissional = registroProfissional;
        setValorConsulta(valorConsulta);
        this.horarios = (horarios != null) ? horarios : new ArrayList<>();
    }
    
    //  GETTERS E SETTERS 
    
    public String getEspecialidade() {
        return especialidade;
    }
    
    public void setEspecialidade(String especialidade) {
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
    
    // ========== METODOS DE NEGOCIO (AGREGACAO) ==========
    
    public void adicionarHorario(HorarioDisponivel horario) {
        if (horario != null && !horarios.contains(horario)) {
            horarios.add(horario);
        }
    }
    
    public void removerHorario(HorarioDisponivel horario) {
        horarios.remove(horario);
    }
    
    public boolean atendeNoDia(String diaSemana) {
        for (HorarioDisponivel h : horarios) {
            if (h.getDiaSemana().equalsIgnoreCase(diaSemana)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean horarioDisponivel(String diaSemana, String horario) {
        for (HorarioDisponivel h : horarios) {
            if (h.getDiaSemana().equalsIgnoreCase(diaSemana) && 
                h.getHorario().equals(horario)) {
                return h.isDisponivel();
            }
        }
        return false;
    }
    
    // METODO ABSTRATO 
    // METODO ABSTRATO: obriga subclasses a implementarem
    public abstract String registrarEspecifico(Atendimento atendimento);
    
    //  SOBRESCRITA 
    // SOBRESCRITA: redefinindo comportamento da superclasse
    // LIGACAO DINAMICA: sera resolvido em tempo de execucao
    
    @Override
    public String exibirResumo() {
        return "PROFISSIONAL: " + getNome() + " | CPF: " + getCpf() + 
               " | Especialidade: " + especialidade + 
               " | Registro: " + (registroProfissional.isEmpty() ? "Nao informado" : registroProfissional) +
               " | Valor: R$" + String.format("%.2f", valorConsulta) +
               " | Horarios: " + horarios.size();
    }
    
    //  METODO ESTATICO 
    public static boolean especialidadeValida(String especialidade) {
        if (especialidade == null) return false;
        String esp = especialidade.toLowerCase().trim();
        return esp.equals("clinica geral") || esp.equals("clinico geral") ||
               esp.equals("fisioterapia") || esp.equals("fisioterapeuta") ||
               esp.equals("psicologia") || esp.equals("psicologo") ||
               esp.equals("nutricao") || esp.equals("nutricionista");
    }
}