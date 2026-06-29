

public class ClinicoGeral extends Profissional {
    
    private String encaminhamento;
    
    // ========== CONSTRUTORES ==========
    
    public ClinicoGeral(String nome, String cpf) {
        super(nome, cpf, "clinica geral");
        this.encaminhamento = "Nenhum";
    }
    
    public ClinicoGeral(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "clinica geral", registroProfissional, valorConsulta);
        this.encaminhamento = "Nenhum";
    }
    
    public ClinicoGeral(String nome, String cpf, String registroProfissional, 
                        double valorConsulta, String encaminhamento) {
        super(nome, cpf, "clinica geral", registroProfissional, valorConsulta);
        setEncaminhamento(encaminhamento);
    }
// ========== GETTERS E SETTERS ==========
    
    public String getEncaminhamento() {
        return encaminhamento;
    }
    
    public void setEncaminhamento(String encaminhamento) {
        this.encaminhamento = (encaminhamento != null) ? encaminhamento : "Nenhum";
    }
    
    // ========== SOBRESCRITA ==========
    
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + 
               " | Encaminhamento: " + encaminhamento + 
               " [CLINICO GERAL]";
    }
    
    @Override
    public String registrarEspecifico(Atendimento atendimento) {
        if (!encaminhamento.equals("Nenhum")) {
            atendimento.adicionarInfoEspecialidade("ENCAMINHAMENTO: " + encaminhamento);
            return "Paciente encaminhado para: " + encaminhamento;
        }
        return "Nenhum encaminhamento necessário.";
    }
}

