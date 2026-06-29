package models;

public class Psicologo extends Profissional {
    
    // ATRIBUTO ESPECÍFICO
    private String abordagem;
    
    // ========== CONSTRUTORES ==========
    
    public Psicologo(String nome, String cpf) {
        super(nome, cpf, "psicologia");
        this.abordagem = "Não informada";
    }
    
    public Psicologo(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "psicologia", registroProfissional, valorConsulta);
        this.abordagem = "Não informada";
    }
    
    public Psicologo(String nome, String cpf, String registroProfissional, 
                     double valorConsulta, String abordagem) {
        super(nome, cpf, "psicologia", registroProfissional, valorConsulta);
        setAbordagem(abordagem);
    }
    
    // ========== GETTERS E SETTERS ==========
    
    public String getAbordagem() {
        return abordagem;
    }
    
    public void setAbordagem(String abordagem) {
        // VALIDAÇÃO: abordagem não pode ser vazia
        if (abordagem == null || abordagem.trim().isEmpty()) {
            throw new IllegalArgumentException("Abordagem terapêutica não pode ser vazia!");
        }
        this.abordagem = abordagem;
    }
 // ========== SOBRESCRITA DE MÉTODOS ==========
    
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + 
               " | Abordagem: " + abordagem + 
               " [PSICOLOGO]";
    }
    
    @Override
    public String registrarEspecifico(Atendimento atendimento) {
        String info = "PSICOLOGIA: Abordagem = " + abordagem;
        atendimento.adicionarInfoEspecialidade(info);
        return "Registro psicológico com abordagem " + abordagem + ".";
    }
}
