// ARQUIVO: src/models/Pessoa.java


/**
 * CLASSE ABSTRATA: Pessoa
 * Conceitos aplicados:
 * - ABSTRACAO: classe que nao pode ser instanciada, serve como base para subclasses
 * - ENCAPSULAMENTO: atributos privados com getters/setters
 * - METODO ABSTRATO: exibirResumo - obriga subclasses a implementarem
 * - HERANCA: sera estendida por Paciente e Profissional
 */
public abstract class Pessoa {
    
    // ATRIBUTOS PRIVADOS (ENCAPSULAMENTO)
    private String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento;
    
    //  CONSTRUTORES (SOBRECARGA) 
    // SOBRECARGA: mesmo nome, parametros diferentes - resolvido em tempo de compilacao
    
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        setCpf(cpf); // usando setter com validacao
        this.telefone = "";
        this.dataNascimento = "";
    }
    
    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        setCpf(cpf);
        setTelefone(telefone);
        this.dataNascimento = "";
    }
    
    public Pessoa(String nome, String cpf, String telefone, String dataNascimento) {
        this.nome = nome;
        setCpf(cpf);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
    }
    
    //  GETTERS E SETTERS (COM VALIDACOES) 
    // ENCAPSULAMENTO: acesso controlado aos atributos
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        // VALIDACAO: nome nao pode ser vazio
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome nao pode ser vazio!");
        }
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        // VALIDACAO: CPF nao pode ser vazio e deve ter 11 digitos
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF nao pode ser vazio!");
        }
        // Remove caracteres nao numericos para validacao
        String cpfLimpo = cpf.replaceAll("\\D", "");
        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 digitos!");
        }
        this.cpf = cpfLimpo;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        // VALIDACAO: telefone deve ter pelo menos 8 digitos
        if (telefone != null && !telefone.trim().isEmpty()) {
            String telLimpo = telefone.replaceAll("\\D", "");
            if (telLimpo.length() < 8) {
                throw new IllegalArgumentException("Telefone deve ter pelo menos 8 digitos!");
            }
        }
        this.telefone = telefone;
    }
    
    public String getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(String dataNascimento) {
        // VALIDACAO: formato DD/MM/AAAA
        if (dataNascimento != null && !dataNascimento.trim().isEmpty()) {
            if (!dataNascimento.matches("\\d{2}/\\d{2}/\\d{4}")) {
                throw new IllegalArgumentException("Data deve estar no formato DD/MM/AAAA!");
            }
        }
        this.dataNascimento = dataNascimento;
    }
    
    //  METODO ABSTRATO 
    // METODO ABSTRATO: obriga subclasses a implementarem
    // LIGACAO DINAMICA: sera resolvido em tempo de execucao baseado no tipo real do objeto
    public abstract String exibirResumo();
    
    //  METODO CONCRETO 
    // Metodo comum a todas as pessoas
    public String getDadosBasicos() {
        return "Nome: " + nome + " | CPF: " + cpf;
    }
    
    @Override
    public String toString() {
        return exibirResumo();
    }
}
