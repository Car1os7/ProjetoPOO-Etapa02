// ARQUIVO: src/service/ClinicaServico.java



import java.util.*;

/**
 * CLASSE CLINICA SERVICO
 * 
 * Conceitos aplicados:
 * - SEPARAÇÃO DE RESPONSABILIDADES: lógica de negócio separada da interface (Main)
 * - ENCAPSULAMENTO: regras de negócio encapsuladas
 * - TRATAMENTO DE EXCEÇÕES: try/catch/finally/throws
 * 
 * Responsável por todas as operações de negócio da clínica
 */
public class ClinicaServico {
    
    // Coleções
    private List<Pessoa> todasPessoas;
    private Map<String, Paciente> pacienteMap;
    private Map<String, Profissional> profissionalMap;
    private Set<String> cpfSet;
    private List<Consulta> consultas;
    private List<Atendimento> atendimentos;
    private List<Pagamento> pagamentos;
    private List<Double> multas;
    
    public ClinicaServico() {
        this.todasPessoas = new ArrayList<>();
        this.pacienteMap = new HashMap<>();
        this.profissionalMap = new HashMap<>();
        this.cpfSet = new HashSet<>();
        this.consultas = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
        this.multas = new ArrayList<>();
    }
    
    // ========== MÉTODOS DE PACIENTE ==========
    
    /**
     * Cadastra paciente com validação de CPF único
     * @throws PacienteNaoEncontradoException se CPF já existir
     */
    public void cadastrarPaciente(Paciente paciente) throws PacienteNaoEncontradoException {
        // TRY: verifica se CPF já existe
        try {
            if (cpfSet.contains(paciente.getCpf())) {
                throw new PacienteNaoEncontradoException("CPF já cadastrado: " + paciente.getCpf());
            }
            
            pacienteMap.put(paciente.getCpf(), paciente);
            cpfSet.add(paciente.getCpf());
            todasPessoas.add(paciente);
            
        } catch (PacienteNaoEncontradoException e) {
            System.err.println("ERRO: " + e.getMessage());
            throw e; // repassa para o chamador
        } finally {
            // FINALLY: sempre executado
            System.out.println("--- Operação de cadastro de paciente finalizada ---");
        }
    }
    
    public Paciente buscarPacientePorCpf(String cpf) throws PacienteNaoEncontradoException {
        // TRY: busca no Map
        try {
            Paciente paciente = pacienteMap.get(cpf);
            if (paciente == null) {
                throw new PacienteNaoEncontradoException("Paciente com CPF " + cpf + " não encontrado!");
            }
            return paciente;
        } catch (PacienteNaoEncontradoException e) {
            System.err.println("ERRO: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("--- Busca de paciente finalizada ---");
        }
    }
    
    public void desativarPaciente(String cpf) throws PacienteNaoEncontradoException {
        Paciente p = buscarPacientePorCpf(cpf);
        p.desativar();
        System.out.println("Paciente " + p.getNome() + " desativado com sucesso.");
    }
    
    // ========== MÉTODOS DE PROFISSIONAL ==========
    
    public void cadastrarProfissional(Profissional profissional) throws ProfissionalNaoEncontradoException {
        try {
            if (profissionalMap.containsKey(profissional.getNome())) {
                throw new ProfissionalNaoEncontradoException("Profissional já cadastrado: " + profissional.getNome());
            }
            
            profissionalMap.put(profissional.getNome(), profissional);
            todasPessoas.add(profissional);
            
        } catch (ProfissionalNaoEncontradoException e) {
            System.err.println("ERRO: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("--- Operação de cadastro de profissional finalizada ---");
        }
    }
    
    public Profissional buscarProfissionalPorNome(String nome) throws ProfissionalNaoEncontradoException {
        try {
            Profissional p = profissionalMap.get(nome);
            if (p == null) {
                throw new ProfissionalNaoEncontradoException("Profissional não encontrado: " + nome);
            }
            return p;
        } finally {
            System.out.println("--- Busca de profissional finalizada ---");
        }
    }
    
    // ========== MÉTODOS DE CONSULTA ==========
    
    public void agendarConsulta(Consulta consulta) throws HorarioIndisponivelException, PacienteInativoException {
        // TRY: validações antes de agendar
        try {
            // Verifica se paciente existe e está ativo
            Paciente paciente = buscarPacientePorCpf(consulta.getCpfPaciente());
            if (!paciente.isAtivo()) {
                throw new PacienteInativoException("Paciente inativo! Não é possível agendar.");
            }
            
            // Verifica se profissional existe
            Profissional profissional = buscarProfissionalPorNome(consulta.getNomeProfissional());
            
            // Verifica disponibilidade
            String diaSemana = descobrirDiaSemana(consulta.getData());
            if (!profissional.horarioDisponivel(diaSemana, consulta.getHorario())) {
                throw new HorarioIndisponivelException("Horário indisponível para o profissional!");
            }
            
            // Verifica conflito
            if (temConflito(consulta.getNomeProfissional(), consulta.getData(), consulta.getHorario())) {
                throw new HorarioIndisponivelException("Já existe consulta agendada nesse horário!");
            }
            
            consultas.add(consulta);
            
        } catch (PacienteInativoException | HorarioIndisponivelException e) {
            System.err.println("ERRO: " + e.getMessage());
            throw e;
        } catch (PacienteNaoEncontradoException | ProfissionalNaoEncontradoException e) {
            System.err.println("ERRO: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            System.out.println("--- Operação de agendamento finalizada ---");
        }
    }
    
    public void cancelarConsulta(String cpf, String data, String horario, String motivo, int horasAntecedencia) 
            throws ConsultaNaoEncontradaException, OperacaoInvalidaException {
        // TRY: localiza e cancela
        try {
            Consulta consulta = buscarConsulta(cpf, data, horario);
            
            if (consulta.getStatus().equals("realizada")) {
                throw new OperacaoInvalidaException("Consulta já realizada! Não pode ser cancelada.");
            }
            
            if (consulta.getStatus().equals("cancelada")) {
                throw new OperacaoInvalidaException("Consulta já está cancelada!");
            }
            
            // Verifica multa (menos de 2 horas de antecedência)
            if (horasAntecedencia < 2) {
                multas.add(50.0);
                System.out.println("Multa de R$50,00 aplicada por cancelamento em cima da hora!");
            }
            
            consulta.cancelar(motivo);
            
        } catch (ConsultaNaoEncontradaException | OperacaoInvalidaException e) {
            System.err.println("ERRO: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("--- Operação de cancelamento finalizada ---");
        }
    }
    
    // ========== MÉTODOS DE PAGAMENTO ==========
    
    public void processarPagamento(Pagamento pagamento) {
        // TRY: processa o pagamento
        try {
            if (pagamento instanceof PagamentoConvenio) {
                // Já valida no construtor
            }
            pagamento.processar();
            pagamentos.add(pagamento);
            
        } catch (ConvenioNaoCobreException | PagamentoInvalidoException e) {
            System.err.println("ERRO: " + e.getMessage());
            throw e;
        } finally {
            // FINALLY: sempre executado
            System.out.println("--- Operação de pagamento finalizada ---");
        }
    }
    
    // ========== MÉTODOS DE ATENDIMENTO ==========
    
    public void registrarAtendimento(Atendimento atendimento) throws OperacaoInvalidaException {
        try {
            int idxConsulta = atendimento.getIndiceConsulta();
            if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
                throw new OperacaoInvalidaException("Índice de consulta inválido!");
            }
            
            Consulta consulta = consultas.get(idxConsulta);
            if (!consulta.getStatus().equals("agendada")) {
                throw new OperacaoInvalidaException("Só é possível registrar atendimento em consulta agendada!");
            }
            
            consulta.realizar();
            atendimento.concluir();
            atendimentos.add(atendimento);
            
        } finally {
            System.out.println("--- Operação de registro de atendimento finalizada ---");
        }
    }
    
    // ========== MÉTODOS DE RELATÓRIO ==========
    
    // POLIMORFISMO: List<Pessoa> contém diferentes tipos
    // LIGAÇÃO DINÂMICA: exibirResumo() executado de acordo com o tipo real
    public void gerarRelatorioUnificado() {
        System.out.println("\n=== RELATÓRIO UNIFICADO DE PESSOAS ===");
        System.out.println("Total de pessoas: " + todasPessoas.size());
        System.out.println("----------------------------------------");
        
        int totalPacientes = 0;
        int totalProfissionais = 0;
        
        // POLIMORFISMO: percorrendo lista de Pessoa
        for (Pessoa p : todasPessoas) {
            System.out.println("[" + (todasPessoas.indexOf(p) + 1) + "] " + p.exibirResumo());
            System.out.println("----------------------------------------");
            
            // CASTING SEGURO com instanceof
            if (p instanceof Paciente) {
                totalPacientes++;
            } else if (p instanceof Profissional) {
                totalProfissionais++;
            }
        }
        
        System.out.println("Totais:");
        System.out.println("  Pacientes: " + totalPacientes);
        System.out.println("  Profissionais: " + totalProfissionais);
        System.out.println("========================================");
    }
    
    // POLIMORFISMO com Pagamentos
    public void gerarRelatorioPagamentos() {
        System.out.println("\n=== RELATÓRIO DE PAGAMENTOS ===");
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }
        
        double totalGeral = 0;
        for (Pagamento p : pagamentos) {
            // LIGAÇÃO DINÂMICA: calcularValorFinal() depende do tipo real
            double valorFinal = p.calcularValorFinal();
            totalGeral += valorFinal;
            System.out.println(p.exibirResumo());
        }
        
        System.out.println("----------------------------------------");
        System.out.println("TOTAL GERAL: R$" + String.format("%.2f", totalGeral));
        System.out.println("========================================");
    }
    
    // ========== MÉTODOS AUXILIARES ==========
    
    private Consulta buscarConsulta(String cpf, String data, String horario) 
            throws ConsultaNaoEncontradaException {
        for (Consulta c : consultas) {
            if (c.getCpfPaciente().equals(cpf) && 
                c.getData().equals(data) && 
                c.getHorario().equals(horario)) {
                return c;
            }
        }
        throw new ConsultaNaoEncontradaException("Consulta não encontrada para CPF " + cpf + 
                                                   " na data " + data + " às " + horario);
    }
    
    private boolean temConflito(String nomeProf, String data, String horario) {
        for (Consulta c : consultas) {
            if (c.getNomeProfissional().equals(nomeProf) &&
                c.getData().equals(data) &&
                c.getHorario().equals(horario) &&
                c.getStatus().equals("agendada")) {
                return true;
            }
        }
        return false;
    }
    
    private String descobrirDiaSemana(String data) {
        try {
            int dia = Integer.parseInt(data.substring(0, 2));
            int mes = Integer.parseInt(data.substring(3, 5));
            int ano = Integer.parseInt(data.substring(6, 10));
            
            if (mes < 3) {
                mes += 12;
                ano--;
            }
            
            int k = ano % 100;
            int j = ano / 100;
            int resultado = (dia + (13 * (mes + 1)) / 5 + k + k/4 + j/4 - 2*j) % 7;
            if (resultado < 0) resultado += 7;
            
            String[] nomes = {"sabado", "domingo", "segunda", "terca", "quarta", "quinta", "sexta"};
            return nomes[resultado];
        } catch (Exception e) {
            return "segunda"; // valor padrão em caso de erro
        }
    }
    
    // ========== GETTERS PARA O MAIN ==========
    
    public List<Consulta> getConsultas() { return consultas; }
    public List<Atendimento> getAtendimentos() { return atendimentos; }
    public List<Pagamento> getPagamentos() { return pagamentos; }
    public List<Double> getMultas() { return multas; }
    public Map<String, Paciente> getPacienteMap() { return pacienteMap; }
    public Map<String, Profissional> getProfissionalMap() { return profissionalMap; }
}
