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
 * - ENCAPSULAMENTO: atributos privados
 * - SOBRECARGA: construtores sobrecarregados
 */
public abstract class Profissional extends Pessoa {
    
    // ATRIBUTOS
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private List<HorarioDisponivel> horarios; // AGREGACAO: horarios existem independentemente
    
    // ========== CONSTRUTORES SOBRECARREGADOS ==========
    // SOBRECARGA: diferentes formas de criar um profissional
    
    // Cadastro minimo
    public Profissional(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0.0;
        this.horarios = new ArrayList<>();
    }
    
    // Com registro e valor
    public Profissional(String nome, String cpf, String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.horarios = new ArrayList<>();
    }
    
    // Completo com horarios
    public Profissional(String nome, String cpf, String especialidade, String registroProfissional, 
                        double valorConsulta, List<HorarioDisponivel> horarios) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.horarios = (horarios != null) ? horarios : new ArrayList<>();
    }
}