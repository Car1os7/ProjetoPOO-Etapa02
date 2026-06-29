// ARQUIVO: src/models/Paciente.java


/**
 * CLASSE PACIENTE - Especializacao de Pessoa
 * 
 * Conceitos aplicados:
 * - HERANCA: extends Pessoa (Paciente e uma Pessoa)
 * - SOBRESCRITA: @Override do metodo exibirResumo()
 * - ASSOCIACAO: Paciente conhece Convenio (ambos existem independentemente)
 * - ENCAPSULAMENTO: atributos privados com validacao
 * - SOBRECARGA: construtores e metodos sobrecarregados
 */
public class Paciente extends Pessoa {
    
    // ATRIBUTOS ESPECIFICOS DE PACIENTE
    private int idade;
    private String convenioNome;
    private boolean ativo;
    private Convenio convenio; // ASSOCIACAO: paciente pode ter um convenio
    
    //  CONSTRUTORES SOBRECARREGADOS 
    // SOBRECARGA: diferentes formas de criar um paciente
    
    public Paciente(String nome, String cpf) {
        super(nome, cpf);
        this.idade = 0;
        this.convenioNome = "";
        this.ativo = true;
        this.convenio = null;
    }
    
    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, telefone);
        setIdade(idade);
        this.convenioNome = "";
        this.ativo = true;
        this.convenio = null;
    }
    
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome, cpf, telefone);
        setIdade(idade);
        this.convenioNome = convenioNome;
        this.ativo = true;
        this.convenio = null;
    }
    
    public Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio) {
        super(nome, cpf, telefone);
        setIdade(idade);
        this.convenio = convenio;
        this.convenioNome = (convenio != null) ? convenio.getNome() : "";
        this.ativo = true;
    }
    
    //  GETTERS E SETTERS ==========
    // ENCAPSULAMENTO: acesso controlado aos atributos
    
    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        // VALIDACAO: idade nao pode ser negativa
        if (idade < 0) {
            throw new IllegalArgumentException("Idade nao pode ser negativa!");
        }
        if (idade > 150) {
            throw new IllegalArgumentException("Idade invalida!");
        }
        this.idade = idade;
    }
    
    public String getConvenioNome() {
        return convenioNome;
    }
    
    public void setConvenioNome(String convenioNome) {
        this.convenioNome = convenioNome;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public Convenio getConvenio() {
        return convenio;
    }
    
    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
        this.convenioNome = (convenio != null) ? convenio.getNome() : "";
    }
    
    //  METODOS ESPECIFICOS 
    
    public void desativar() {
        this.ativo = false;
    }
    
    public void ativar() {
        this.ativo = true;
    }
    
    //  METODOS SOBRECARREGADOS 
    // SOBRECARGA: mesmo nome, parametros diferentes
    
    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }
    
    public void complementar(int idade, String telefone, String convenioNome) {
        setIdade(idade);
        setTelefone(telefone);
        this.convenioNome = convenioNome;
    }
    
    //  SOBRESCRITA 
    // SOBRESCRITA: redefinindo comportamento da superclasse
    // LIGACAO DINAMICA: o metodo executado depende do tipo real do objeto
    
    @Override
    public String exibirResumo() {
        String status = ativo ? "ATIVO" : "INATIVO";
        String conv = (convenio != null) ? convenio.getNome() : convenioNome;
        if (conv == null || conv.isEmpty()) conv = "Nenhum";
        
        return "PACIENTE: " + getNome() + " | CPF: " + getCpf() + 
               " | Idade: " + idade + " | Telefone: " + getTelefone() +
               " | Convenio: " + conv + " | Status: " + status;
    }
}

