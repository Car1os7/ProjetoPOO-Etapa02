import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    
    // ========== COLEÇÕES PRINCIPAIS ==========
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Profissional> profissionais = new ArrayList<>();
    private static List<Consulta> consultas = new ArrayList<>();
    private static List<Atendimento> atendimentos = new ArrayList<>();
    private static List<Pagamento> pagamentos = new ArrayList<>();
    private static List<Double> multas = new ArrayList<>();
    private static Set<String> cpfsCadastrados = new HashSet<>();
    private static Map<String, Paciente> mapaPacientes = new HashMap<>();
    private static Map<String, Profissional> mapaProfissionais = new HashMap<>();

    public static void main(String[] args) {
        int opcao = -1;
        inicializarDadosTeste();
        
        while (opcao != 0) {
            try {
                System.out.println("\n=== CLINICA VIDAPLENA ===");
                System.out.println("1 - Pacientes");
                System.out.println("2 - Profissionais");
                System.out.println("3 - Consultas");
                System.out.println("4 - Atendimentos");
                System.out.println("5 - Pagamentos");
                System.out.println("6 - Relatorios");
                System.out.println("7 - Exportacao de Dados");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                opcao = lerInteiro();
                
                switch (opcao) {
                    case 1: menuPacientes(); break;
                    case 2: menuProfissionais(); break;
                    case 3: menuConsultas(); break;
                    case 4: menuAtendimentos(); break;
                    case 5: menuPagamentos(); break;
                    case 6: menuRelatorios(); break;
                    case 7: menuExportacao(); break;
                    case 0: System.out.println("Sistema encerrado."); break;
                    default: System.out.println("Opcao invalida!"); break;
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
        sc.close();
    }

    // ========== METODOS AUXILIARES ==========
    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.print("Digite um numero valido: ");
            }
        }
    }
    
    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.err.print("Digite um numero valido: ");
            }
        }
    }
    
    private static String lerString() {
        return sc.nextLine().trim();
    }
    
    private static String lerStringNaoVazia() {
        while (true) {
            String s = lerString();
            if (!s.isEmpty()) return s;
            System.out.print("Campo obrigatorio: ");
        }
    }

     // ========== DADOS DE TESTE ==========
    
    private static void inicializarDadosTeste() {
        try {
            // Convenios
            Convenio saudePlus = new Convenio("SaudePlus", 0.40);
            saudePlus.adicionarEspecialidade("clinica geral");
            saudePlus.adicionarEspecialidade("fisioterapia");
            
            Convenio vidaMais = new Convenio("VidaMais", 0.30);
            vidaMais.adicionarEspecialidade("clinica geral");
            vidaMais.adicionarEspecialidade("psicologia");
            
            Convenio bemEstar = new Convenio("BemEstar", 0.50);
            bemEstar.adicionarEspecialidade("clinica geral");
            bemEstar.adicionarEspecialidade("fisioterapia");
            bemEstar.adicionarEspecialidade("nutricao");
            
            // Pacientes
            Paciente p1 = new Paciente("Joao Silva", "12345678901", 35, "(11) 99999-1111", saudePlus);
            Paciente p2 = new Paciente("Maria Santos", "98765432100", 28, "(11) 99999-2222", vidaMais);
            Paciente p3 = new Paciente("Pedro Costa", "11122233344", 42, "(11) 99999-3333", bemEstar);
            Paciente p4 = new Paciente("Ana Oliveira", "55566677788", 25, "(11) 99999-4444", "Nenhum");
            
            pacientes.add(p1); pacientes.add(p2); pacientes.add(p3); pacientes.add(p4);
            cpfsCadastrados.add("12345678901"); cpfsCadastrados.add("98765432100");
            cpfsCadastrados.add("11122233344"); cpfsCadastrados.add("55566677788");
            mapaPacientes.put("12345678901", p1); mapaPacientes.put("98765432100", p2);
            mapaPacientes.put("11122233344", p3); mapaPacientes.put("55566677788", p4);
            
            // Horarios
            HorarioDisponivel h1 = new HorarioDisponivel("segunda", "08:00");
            HorarioDisponivel h2 = new HorarioDisponivel("segunda", "09:00");
            HorarioDisponivel h3 = new HorarioDisponivel("terca", "08:00");
            HorarioDisponivel h4 = new HorarioDisponivel("quarta", "10:00");
            HorarioDisponivel h5 = new HorarioDisponivel("quinta", "14:00");
            HorarioDisponivel h6 = new HorarioDisponivel("sexta", "08:00");
            
            // Profissionais
            ClinicoGeral drCarlos = new ClinicoGeral("Dr. Carlos", "11122233344", "CRM-12345", 200.00);
            drCarlos.setHorarios(new ArrayList<>(Arrays.asList(h1, h2, h3, h4, h5, h6)));
            
            Fisioterapeuta draAna = new Fisioterapeuta("Dra. Ana", "22233344455", "CREFITO-67890", 150.00, 12);
            draAna.setHorarios(new ArrayList<>(Arrays.asList(h1, h3, h5)));
            
            Psicologo drPaulo = new Psicologo("Dr. Paulo", "33344455566", "CRP-09876", 180.00, "TCC");
            drPaulo.setHorarios(new ArrayList<>(Arrays.asList(h2, h4, h6)));
            
            Nutricionista draMariana = new Nutricionista("Dra. Mariana", "44455566677", "CRN-54321", 160.00, "Reeducacao alimentar");
            draMariana.setHorarios(new ArrayList<>(Arrays.asList(h1, h4)));
            
            profissionais.add(drCarlos); profissionais.add(draAna); profissionais.add(drPaulo); profissionais.add(draMariana);
            mapaProfissionais.put("Dr. Carlos", drCarlos); mapaProfissionais.put("Dra. Ana", draAna);
            mapaProfissionais.put("Dr. Paulo", drPaulo); mapaProfissionais.put("Dra. Mariana", draMariana);
            
            System.out.println("=== DADOS DE TESTE CARREGADOS! ===");
            System.out.println("4 pacientes e 4 profissionais cadastrados.");
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    // ========== MENU PACIENTES ==========
    
    private static void menuPacientes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Complementar cadastro");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Desativar");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInteiro();
            switch (op) {
                case 1: cadastrarPaciente(); break;
                case 2: complementarPaciente(); break;
                case 3: buscarPaciente(); break;
                case 4: listarPacientes(); break;
                case 5: desativarPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void cadastrarPaciente() {
        try {
            System.out.println("\n--- CADASTRO DE PACIENTE ---");
            System.out.print("Nome: ");
            String nome = lerStringNaoVazia();
            System.out.print("CPF (11 digitos): ");
            String cpf = lerStringNaoVazia();
            
            if (cpfsCadastrados.contains(cpf)) {
                System.out.println("CPF ja cadastrado!");
                return;
            }
            
            System.out.print("Idade: ");
            int idade = lerInteiro();
            System.out.print("Telefone: ");
            String telefone = lerString();
            System.out.print("Convenio (ou 'Nenhum'): ");
            String convenioNome = lerString();
            
            Paciente p;
            if (convenioNome.equalsIgnoreCase("Nenhum") || convenioNome.isEmpty()) {
                p = new Paciente(nome, cpf, idade, telefone);
            } else {
                p = new Paciente(nome, cpf, idade, telefone, convenioNome);
            }
            
            pacientes.add(p);
            cpfsCadastrados.add(cpf);
            mapaPacientes.put(cpf, p);
            System.out.println("Paciente cadastrado com sucesso!");
            
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void complementarPaciente() {
        try {
            System.out.print("CPF: ");
            String cpf = lerString();
            Paciente p = mapaPacientes.get(cpf);
            if (p == null) {
                System.out.println("Paciente nao encontrado!");
                return;
            }
            System.out.print("Nova idade: ");
            int idade = lerInteiro();
            System.out.print("Novo telefone: ");
            String telefone = lerString();
            p.complementar(idade, telefone);
            System.out.println("Cadastro complementado!");
            
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void buscarPaciente() {
        System.out.print("CPF: ");
        String cpf = lerString();
        Paciente p = mapaPacientes.get(cpf);
        if (p == null) {
            System.out.println("Paciente nao encontrado!");
        } else {
            System.out.println(p.exibirResumo());
        }
    }

    private static void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        System.out.println("\n=== LISTA DE PACIENTES ===");
        for (Paciente p : pacientes) {
            System.out.println(p.exibirResumo());
        }
    }

    private static void desativarPaciente() {
        System.out.print("CPF: ");
        String cpf = lerString();
        Paciente p = mapaPacientes.get(cpf);
        if (p == null) {
            System.out.println("Paciente nao encontrado!");
            return;
        }
        p.desativar();
        System.out.println("Paciente desativado!");
    }

    // ========== MENU PROFISSIONAIS ==========
    
    private static void menuProfissionais() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PROFISSIONAIS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Filtrar por especialidade");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInteiro();
            switch (op) {
                case 1: cadastrarProfissional(); break;
                case 2: listarProfissionais(); break;
                case 3: filtrarProfissionais(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void cadastrarProfissional() {
        try {
            System.out.println("\n--- CADASTRO DE PROFISSIONAL ---");
            System.out.print("Nome: ");
            String nome = lerStringNaoVazia();
            System.out.print("CPF: ");
            String cpf = lerStringNaoVazia();
            
            System.out.println("Especialidades:");
            System.out.println("1 - Clinico Geral");
            System.out.println("2 - Fisioterapeuta");
            System.out.println("3 - Psicologo");
            System.out.println("4 - Nutricionista");
            System.out.print("Escolha: ");
            int esp = lerInteiro();
            
            System.out.print("Registro profissional: ");
            String registro = lerString();
            System.out.print("Valor da consulta: ");
            double valor = lerDouble();
            
            Profissional prof = null;
            switch (esp) {
                case 1:
                    System.out.print("Encaminhamento (opcional): ");
                    String enc = lerString();
                    prof = new ClinicoGeral(nome, cpf, registro, valor, enc);
                    break;
                case 2:
                    System.out.print("Total de sessoes previstas: ");
                    int sessoes = lerInteiro();
                    prof = new Fisioterapeuta(nome, cpf, registro, valor, sessoes);
                    break;
                case 3:
                    System.out.print("Abordagem terapeutica: ");
                    String abordagem = lerStringNaoVazia();
                    prof = new Psicologo(nome, cpf, registro, valor, abordagem);
                    break;
                case 4:
                    System.out.print("Plano alimentar: ");
                    String plano = lerStringNaoVazia();
                    prof = new Nutricionista(nome, cpf, registro, valor, plano);
                    break;
                default: System.out.println("Especialidade invalida!"); return;
            }
            
            System.out.print("Quantos horarios? ");
            int qtd = lerInteiro();
            List<HorarioDisponivel> horarios = new ArrayList<>();
            for (int i = 0; i < qtd; i++) {
                System.out.print("Dia (segunda, terca, ...): ");
                String dia = lerString();
                System.out.print("Horario (HH:MM): ");
                String hora = lerString();
                horarios.add(new HorarioDisponivel(dia, hora));
            }
            prof.setHorarios(horarios);
            
            profissionais.add(prof);
            mapaProfissionais.put(nome, prof);
            System.out.println("Profissional cadastrado!");
            System.out.println(prof.exibirResumo());
            
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void listarProfissionais() {
        if (profissionais.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado.");
            return;
        }
        System.out.println("\n=== LISTA DE PROFISSIONAIS ===");
        for (Profissional p : profissionais) {
            System.out.println(p.exibirResumo());
        }
    }

    private static void filtrarProfissionais() {
        System.out.print("Especialidade: ");
        String esp = lerString().toLowerCase();
        boolean achou = false;
        for (Profissional p : profissionais) {
            if (p.getEspecialidade().equalsIgnoreCase(esp)) {
                System.out.println(p.exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum profissional encontrado.");
    }

    // ========== MENU CONSULTAS ==========
    
    private static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Agendar por profissional");
            System.out.println("2 - Agendar por especialidade");
            System.out.println("3 - Cancelar");
            System.out.println("4 - Remarcar");
            System.out.println("5 - Listar todas");
            System.out.println("6 - Buscar por CPF");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInteiro();
            switch (op) {
                case 1: agendarConsulta(); break;
                case 2: agendarPorEspecialidade(); break;
                case 3: cancelarConsulta(); break;
                case 4: remarcarConsulta(); break;
                case 5: listarConsultas(); break;
                case 6: buscarConsultasPorPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void agendarConsulta() {
        try {
            System.out.print("CPF do paciente: ");
            String cpf = lerString();
            Paciente p = mapaPacientes.get(cpf);
            if (p == null) {
                System.out.println("Paciente nao encontrado!");
                return;
            }
            if (!p.isAtivo()) {
                System.out.println("Paciente inativo!");
                return;
            }
            
            System.out.print("Nome do profissional: ");
            String nome = lerString();
            Profissional prof = mapaProfissionais.get(nome);
            if (prof == null) {
                System.out.println("Profissional nao encontrado!");
                return;
            }
            
            System.out.print("Data (DD/MM/AAAA): ");
            String data = lerString();
            System.out.print("Horario (HH:MM): ");
            String horario = lerString();
            System.out.print("Tipo (inicial/retorno/avaliacao): ");
            String tipo = lerString();
            
            // Verifica conflito
            String dia = descobrirDiaSemana(data);
            for (Consulta c : consultas) {
                if (c.getNomeProfissional().equals(nome) && 
                    c.getData().equals(data) && 
                    c.getHorario().equals(horario) && 
                    c.getStatus().equals("agendada")) {
                    System.out.println("Horario ocupado!");
                    return;
                }
            }
            
            Consulta consulta = new Consulta(cpf, nome, data, horario, tipo);
            consultas.add(consulta);
            System.out.println("Consulta agendada!");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void agendarPorEspecialidade() {
        try {
            System.out.print("CPF do paciente: ");
            String cpf = lerString();
            Paciente p = mapaPacientes.get(cpf);
            if (p == null || !p.isAtivo()) {
                System.out.println("Paciente invalido ou inativo!");
                return;
            }
            
            System.out.print("Especialidade: ");
            String esp = lerString().toLowerCase();
            System.out.print("Data (DD/MM/AAAA): ");
            String data = lerString();
            System.out.print("Horario (HH:MM): ");
            String horario = lerString();
            
            String dia = descobrirDiaSemana(data);
            for (Profissional prof : profissionais) {
                if (prof.getEspecialidade().equalsIgnoreCase(esp) && prof.atendeNoDia(dia)) {
                    boolean conflito = false;
                    for (Consulta c : consultas) {
                        if (c.getNomeProfissional().equals(prof.getNome()) && 
                            c.getData().equals(data) && 
                            c.getHorario().equals(horario) && 
                            c.getStatus().equals("agendada")) {
                            conflito = true;
                            break;
                        }
                    }
                    if (!conflito) {
                        Consulta consulta = new Consulta(cpf, prof.getNome(), data, horario);
                        consultas.add(consulta);
                        System.out.println("Consulta agendada com " + prof.getNome());
                        return;
                    }
                }
            }
            System.out.println("Nenhum profissional disponivel!");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void cancelarConsulta() {
        try {
            System.out.print("CPF: ");
            String cpf = lerString();
            System.out.print("Data (DD/MM/AAAA): ");
            String data = lerString();
            System.out.print("Horario (HH:MM): ");
            String horario = lerString();
            
            for (Consulta c : consultas) {
                if (c.getCpfPaciente().equals(cpf) && 
                    c.getData().equals(data) && 
                    c.getHorario().equals(horario)) {
                    
                    if (c.getStatus().equals("realizada")) {
                        System.out.println("Consulta ja realizada!");
                        return;
                    }
                    if (c.getStatus().equals("cancelada")) {
                        System.out.println("Consulta ja cancelada!");
                        return;
                    }
                    
                    System.out.print("Motivo: ");
                    String motivo = lerString();
                    c.cancelar(motivo);
                    System.out.println("Consulta cancelada!");
                    return;
                }
            }
            System.out.println("Consulta nao encontrada!");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void remarcarConsulta() {
        try {
            System.out.print("CPF: ");
            String cpf = lerString();
            System.out.print("Data original: ");
            String dataOrig = lerString();
            System.out.print("Horario original: ");
            String horarioOrig = lerString();
            
            for (Consulta c : consultas) {
                if (c.getCpfPaciente().equals(cpf) && 
                    c.getData().equals(dataOrig) && 
                    c.getHorario().equals(horarioOrig) && 
                    c.getStatus().equals("agendada")) {
                    
                    System.out.print("Nova data: ");
                    String novaData = lerString();
                    System.out.print("Novo horario: ");
                    String novoHorario = lerString();
                    
                    c.remarcar(novaData, novoHorario);
                    Consulta nova = new Consulta(cpf, c.getNomeProfissional(), novaData, novoHorario, c.getTipo());
                    consultas.add(nova);
                    System.out.println("Consulta remarcada!");
                    return;
                }
            }
            System.out.println("Consulta nao encontrada!");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta.");
            return;
        }
        System.out.println("\n=== LISTA DE CONSULTAS ===");
        for (Consulta c : consultas) {
            System.out.println(c.exibirResumo());
        }
    }

    private static void buscarConsultasPorPaciente() {
        System.out.print("CPF: ");
        String cpf = lerString();
        boolean achou = false;
        for (Consulta c : consultas) {
            if (c.getCpfPaciente().equals(cpf)) {
                System.out.println(c.exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma consulta encontrada.");
    }

    // ========== MENU ATENDIMENTOS ==========
    
    private static void menuAtendimentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- ATENDIMENTOS ---");
            System.out.println("1 - Registrar atendimento");
            System.out.println("2 - Listar atendimentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInteiro();
            switch (op) {
                case 1: registrarAtendimento(); break;
                case 2: listarAtendimentos(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void registrarAtendimento() {
        try {
            System.out.print("Indice da consulta: ");
            int idx = lerInteiro();
            if (idx < 0 || idx >= consultas.size()) {
                System.out.println("Indice invalido!");
                return;
            }
            
            Consulta c = consultas.get(idx);
            if (!c.getStatus().equals("agendada")) {
                System.out.println("Consulta nao esta agendada!");
                return;
            }
            
            System.out.print("Observacoes: ");
            String obs = lerString();
            System.out.print("Diagnostico: ");
            String diag = lerString();
            
            Atendimento a = new Atendimento(idx, obs, diag);
            System.out.print("Quantos procedimentos? ");
            int qtd = lerInteiro();
            for (int i = 0; i < qtd; i++) {
                System.out.print("Procedimento " + (i+1) + ": ");
                a.adicionarProcedimento(lerString());
            }
            
            c.realizar();
            a.concluir();
            atendimentos.add(a);
            System.out.println("Atendimento registrado!");
            System.out.println(a.exibirResumo());
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void listarAtendimentos() {
        if (atendimentos.isEmpty()) {
            System.out.println("Nenhum atendimento.");
            return;
        }
        System.out.println("\n=== LISTA DE ATENDIMENTOS ===");
        for (Atendimento a : atendimentos) {
            System.out.println(a.exibirResumo());
        }
    }