


public class Consulta implements Agendavel, Exportavel {
    
    private String cpfPaciente;
    private String nomeProfissional;
    private String data;
    private String horario;
    private String tipo;
    private String status;
    private String motivoCancelamento;
    
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = "inicial";
        this.status = "agendada";
        this.motivoCancelamento = "";
    }
    
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = "agendada";
        this.motivoCancelamento = "";
    }
    
    public Consulta(String cpfPaciente, String nomeProfissional, String data, 
                    String horario, String tipo, String status) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = status;
        this.motivoCancelamento = "";
    }
    
    public String getCpfPaciente() { return cpfPaciente; }
    public String getNomeProfissional() { return nomeProfissional; }
    public String getData() { return data; }
    public String getHorario() { return horario; }
    public String getTipo() { return tipo; }
    public String getStatus() { return status; }
    public String getMotivoCancelamento() { return motivoCancelamento; }
    
    public void setData(String data) {
        if (data != null && !data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("Data deve estar no formato DD/MM/AAAA!");
        }
        this.data = data;
    }
    
    public void setHorario(String horario) {
        if (horario != null && !horario.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Horário deve estar no formato HH:MM!");
        }
        this.horario = horario;
    }
    
    @Override
    public void agendar() {
        if (status.equals("agendada")) {
            System.out.println("Consulta já está agendada.");
            return;
        }
        this.status = "agendada";
        this.motivoCancelamento = "";
        System.out.println("Consulta reagendada com sucesso!");
    }
    
    @Override
    public String cancelar() {
        if (status.equals("realizada")) {
            throw new OperacaoInvalidaException("Não é possível cancelar uma consulta já realizada!");
        }
        if (status.equals("cancelada")) {
            throw new OperacaoInvalidaException("Esta consulta já foi cancelada!");
        }
        this.status = "cancelada";
        this.motivoCancelamento = "Cancelado pelo paciente";
        return "Consulta cancelada.";
    }
    
    @Override
    public String cancelar(String motivo) {
        if (status.equals("realizada")) {
            throw new OperacaoInvalidaException("Não é possível cancelar uma consulta já realizada!");
        }
        if (status.equals("cancelada")) {
            throw new OperacaoInvalidaException("Esta consulta já foi cancelada!");
        }
        this.status = "cancelada";
        this.motivoCancelamento = motivo;
        return "Consulta cancelada. Motivo: " + motivo;
    }
    
    @Override
    public String remarcar(String novaData, String novoHorario) {
        if (status.equals("cancelada")) {
            throw new OperacaoInvalidaException("Não é possível remarcar uma consulta cancelada!");
        }
        if (status.equals("realizada")) {
            throw new OperacaoInvalidaException("Não é possível remarcar uma consulta já realizada!");
        }
        String oldData = this.data;
        String oldHorario = this.horario;
        this.data = novaData;
        this.horario = novoHorario;
        this.status = "remarcada";
        return "Consulta remarcada de " + oldData + " " + oldHorario + 
               " para " + novaData + " " + novoHorario;
    }
    
    @Override
    public String exportarDados() {
        String export = "=== DADOS DA CONSULTA ===\n";
        export += "Paciente CPF: " + cpfPaciente + "\n";
        export += "Profissional: " + nomeProfissional + "\n";
        export += "Data: " + data + "\n";
        export += "Horário: " + horario + "\n";
        export += "Tipo: " + tipo + "\n";
        export += "Status: " + status + "\n";
        if (!motivoCancelamento.isEmpty()) {
            export += "Motivo Cancelamento: " + motivoCancelamento + "\n";
        }
        export += "===========================";
        return export;
    }
    
    public void realizar() {
        if (status.equals("cancelada")) {
            throw new OperacaoInvalidaException("Não é possível realizar uma consulta cancelada!");
        }
        if (status.equals("realizada")) {
            System.out.println("Consulta já foi realizada.");
            return;
        }
        this.status = "realizada";
    }
    
    public String exibirResumo() {
        return "Paciente(CPF): " + cpfPaciente + " | Prof: " + nomeProfissional
                + " | Data: " + data + " | Hora: " + horario
                + " | Tipo: " + tipo + " | Status: " + status;
    }
    
    @Override
    public String toString() {
        return exibirResumo();
    }
}

