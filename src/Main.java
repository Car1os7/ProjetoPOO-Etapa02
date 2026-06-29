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
