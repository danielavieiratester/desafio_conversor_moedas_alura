package br.com.alura.conversormoedas;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
public class Principal {
    private static java.net.http.HttpClient HttpClient;

    public static void main(String[] args) throws IOException, InterruptedException {

        String menu = """
                ***************************************************************
                Seja bem-vindo(a) ao Conversor de Moedas =]
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileiro
                4) Real brasileiro =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Sair""";

        Scanner leitura = new Scanner(System.in);

        System.out.println(menu);
        System.out.println("Escolha uma opção válida: ");
        int opcao = leitura.nextInt();

        while (opcao != 7) {

            Object BodyHandlers;
            BodyHandlers = null;

//Opção 1 - Dólar [USD] =>> Peso Argentino [ARS]
            if (opcao == 1) {
                System.out.println("Digite o valor que deseja converter");
                double valor = leitura.nextDouble();

                String endereco = "https://v6.exchangerate-api.com/v6/40c9ecabf00d9e958faa9e27/latest/USD";

                // Cria uma instância do HttpClient
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                // Cria a requisição HTTP GET
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //System.out.println(response.body());
                String json = response.body();

                Gson gson = new GsonBuilder().create();

                // Converte a string JSON para o objeto DadosConversao
                DadosConversao dados = gson.fromJson(json, DadosConversao.class);

                // Extrai o valor de "ARS" do mapa
                double valorConversao = dados.conversion_rates.get("ARS");
                double resultado = valor * valorConversao;

                System.out.println("Valor " + valor + " [USD] corresponde ao valor final de =>> " + resultado + "[ARS]");
                try {
                    // Converte a string JSON para o objeto DadosConversao
                    // Esta linha pode lançar JsonSyntaxException se o JSON for inválido
                    dados = gson.fromJson(json, DadosConversao.class);

                    // Verifica se o mapa de taxas de conversão não é nulo e se contém a chave "ARS"
                    if (dados.conversion_rates != null && dados.conversion_rates.containsKey("ARS")) {
                        // Extrai o valor de "ARS" do mapa
                        valorConversao = dados.conversion_rates.get("ARS");
                        System.out.println("O valor da taxa de conversão para ARS é: " + valorConversao);

                        // Aqui você pode continuar com seus cálculos, por exemplo:
                        // double resultado = valor * valorConversao;
                        // System.out.println("Valor " + valor +" [USD] corresponde ao valor final de =>> "  + resultado + "[ARS]");

                    } else {
                        // Caso a chave "ARS" não seja encontrada no JSON ou o mapa seja nulo
                        System.out.println("A taxa de conversão para ARS não foi encontrada ou o formato do JSON está incompleto.");
                    }

                } catch (JsonSyntaxException e) {
                    // Captura exceção se o JSON for malformado ou se o valor de "ARS" não for um número
                    System.err.println("Erro ao processar o JSON. O valor de ARS pode não ser um número válido ou a estrutura está incorreta: " + e.getMessage());
                    // Opcional: imprimir o JSON para depuração
                    // System.err.println("JSON recebido: " + json);
                } catch (Exception e) {
                    // Captura qualquer outra exceção inesperada que possa ocorrer durante o processamento
                    System.err.println("Ocorreu um erro inesperado ao lidar com os dados de conversão: " + e.getMessage());
                }

                System.out.println();
                System.out.println();

                System.out.println(menu);
                System.out.println("Escolha uma opção válida: ");
                opcao = leitura.nextInt();

            }
//Opção 2 - Peso Argentino [ARS] =>> Dólar [USD]
            else if (opcao == 2) {
                System.out.println("Digite o valor que deseja converter");
                double valor = leitura.nextDouble();

                String endereco = "https://v6.exchangerate-api.com/v6/40c9ecabf00d9e958faa9e27/latest/ARS";

                // Cria uma instância do HttpClient
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                // Cria a requisição HTTP GET
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //System.out.println(response.body());
                String json = response.body();

                Gson gson = new GsonBuilder().create();

                // Converte a string JSON para o objeto DadosConversao
                DadosConversao dados = gson.fromJson(json, DadosConversao.class);

                // Extrai o valor de "USD" do mapa
                double valorConversao = dados.conversion_rates.get("USD");
                double resultado = valor * valorConversao;

                System.out.println("Valor " + valor + " [ARS] corresponde ao valor final de =>> " + resultado + "[USD]");
                try {
                    // Converte a string JSON para o objeto DadosConversao
                    // Esta linha pode lançar JsonSyntaxException se o JSON for inválido
                    dados = gson.fromJson(json, DadosConversao.class);

                    // Verifica se o mapa de taxas de conversão não é nulo e se contém a chave "USD"
                    if (dados.conversion_rates != null && dados.conversion_rates.containsKey("USD")) {
                        // Extrai o valor de "USD" do mapa
                        valorConversao = dados.conversion_rates.get("USD");
                        System.out.println("O valor da taxa de conversão para USD é: " + valorConversao);

                        // Aqui você pode continuar com seus cálculos, por exemplo:
                        // double resultado = valor * valorConversao;
                        // System.out.println("Valor " + valor +" [ARS] corresponde ao valor final de =>> "  + resultado + "[USD]");

                    } else {
                        // Caso a chave "USD" não seja encontrada no JSON ou o mapa seja nulo
                        System.out.println("A taxa de conversão para USD não foi encontrada ou o formato do JSON está incompleto.");
                    }

                } catch (JsonSyntaxException e) {
                    // Captura exceção se o JSON for malformado ou se o valor de "USD" não for um número
                    System.err.println("Erro ao processar o JSON. O valor de USD pode não ser um número válido ou a estrutura está incorreta: " + e.getMessage());
                    // Opcional: imprimir o JSON para depuração
                    // System.err.println("JSON recebido: " + json);
                } catch (Exception e) {
                    // Captura qualquer outra exceção inesperada que possa ocorrer durante o processamento
                    System.err.println("Ocorreu um erro inesperado ao lidar com os dados de conversão: " + e.getMessage());
                }

                System.out.println();
                System.out.println();

                System.out.println(menu);
                System.out.println("Escolha uma opção válida: ");
                opcao = leitura.nextInt();

            }
//Opcao 3 - Dólar [USD] =>> Real brasileiro [BRL]
            else if (opcao == 3) {
                System.out.println("Digite o valor que deseja converter");
                double valor = leitura.nextDouble();

                String endereco = "https://v6.exchangerate-api.com/v6/40c9ecabf00d9e958faa9e27/latest/USD";

                // Cria uma instância do HttpClient
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                // Cria a requisição HTTP GET
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //System.out.println(response.body());
                String json = response.body();

                Gson gson = new GsonBuilder().create();

                // Converte a string JSON para o objeto DadosConversao
                DadosConversao dados = gson.fromJson(json, DadosConversao.class);

                // Extrai o valor de "BRL" do mapa
                double valorConversao = dados.conversion_rates.get("BRL");
                double resultado = valor * valorConversao;

                System.out.println("Valor " + valor + " [USD] corresponde ao valor final de =>> " + resultado + "[BRL]");
                try {
                    // Converte a string JSON para o objeto DadosConversao
                    // Esta linha pode lançar JsonSyntaxException se o JSON for inválido
                    dados = gson.fromJson(json, DadosConversao.class);

                    // Verifica se o mapa de taxas de conversão não é nulo e se contém a chave "BRL"
                    if (dados.conversion_rates != null && dados.conversion_rates.containsKey("BRL")) {
                        // Extrai o valor de "BRL" do mapa
                        valorConversao = dados.conversion_rates.get("BRL");
                        System.out.println("O valor da taxa de conversão para BRL é: " + valorConversao);

                        // Aqui você pode continuar com seus cálculos, por exemplo:
                        // double resultado = valor * valorConversao;
                        // System.out.println("Valor " + valor +" [USD] corresponde ao valor final de =>> "  + resultado + "[BRL]");

                    } else {
                        // Caso a chave "BRL" não seja encontrada no JSON ou o mapa seja nulo
                        System.out.println("A taxa de conversão para BRL não foi encontrada ou o formato do JSON está incompleto.");
                    }

                } catch (JsonSyntaxException e) {
                    // Captura exceção se o JSON for malformado ou se o valor de "BRL" não for um número
                    System.err.println("Erro ao processar o JSON. O valor de BRL pode não ser um número válido ou a estrutura está incorreta: " + e.getMessage());
                    // Opcional: imprimir o JSON para depuração
                    // System.err.println("JSON recebido: " + json);
                } catch (Exception e) {
                    // Captura qualquer outra exceção inesperada que possa ocorrer durante o processamento
                    System.err.println("Ocorreu um erro inesperado ao lidar com os dados de conversão: " + e.getMessage());
                }

                System.out.println();
                System.out.println();

                System.out.println(menu);
                System.out.println("Escolha uma opção válida: ");
                opcao = leitura.nextInt();

            }
//Opcao 4 - Real brasileiro [BRL] =>> Dólar [USD]
            else if (opcao == 4) {
                System.out.println("Digite o valor que deseja converter");
                double valor = leitura.nextDouble();

                String endereco = "https://v6.exchangerate-api.com/v6/40c9ecabf00d9e958faa9e27/latest/BRL";

                // Cria uma instância do HttpClient
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                // Cria a requisição HTTP GET
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //System.out.println(response.body());
                String json = response.body();

                Gson gson = new GsonBuilder().create();

                // Converte a string JSON para o objeto DadosConversao
                DadosConversao dados = gson.fromJson(json, DadosConversao.class);

                // Extrai o valor de "USD" do mapa
                double valorConversao = dados.conversion_rates.get("USD");
                double resultado = valor * valorConversao;

                System.out.println("Valor " + valor + " [BRL] corresponde ao valor final de =>> " + resultado + "[USD]");
                try {
                    // Converte a string JSON para o objeto DadosConversao
                    // Esta linha pode lançar JsonSyntaxException se o JSON for inválido
                    dados = gson.fromJson(json, DadosConversao.class);

                    // Verifica se o mapa de taxas de conversão não é nulo e se contém a chave "USD"
                    if (dados.conversion_rates != null && dados.conversion_rates.containsKey("USD")) {
                        // Extrai o valor de "USD" do mapa
                        valorConversao = dados.conversion_rates.get("USD");
                        System.out.println("O valor da taxa de conversão para USD é: " + valorConversao);

                        // Aqui você pode continuar com seus cálculos, por exemplo:
                        // double resultado = valor * valorConversao;
                        // System.out.println("Valor " + valor +" [BRL] corresponde ao valor final de =>> "  + resultado + "[USD]");

                    } else {
                        // Caso a chave "USD" não seja encontrada no JSON ou o mapa seja nulo
                        System.out.println("A taxa de conversão para USD não foi encontrada ou o formato do JSON está incompleto.");
                    }

                } catch (JsonSyntaxException e) {
                    // Captura exceção se o JSON for malformado ou se o valor de "USD" não for um número
                    System.err.println("Erro ao processar o JSON. O valor de USD pode não ser um número válido ou a estrutura está incorreta: " + e.getMessage());
                    // Opcional: imprimir o JSON para depuração
                    // System.err.println("JSON recebido: " + json);
                } catch (Exception e) {
                    // Captura qualquer outra exceção inesperada que possa ocorrer durante o processamento
                    System.err.println("Ocorreu um erro inesperado ao lidar com os dados de conversão: " + e.getMessage());
                }

                System.out.println();
                System.out.println();

                System.out.println(menu);
                System.out.println("Escolha uma opção válida: ");
                opcao = leitura.nextInt();

            }
//Opcao 5 - Dólar [USD] =>> Peso colombiano [COP]
            else if (opcao == 5) {
                System.out.println("Digite o valor que deseja converter");
                double valor = leitura.nextDouble();

                String endereco = "https://v6.exchangerate-api.com/v6/40c9ecabf00d9e958faa9e27/latest/USD";

                // Cria uma instância do HttpClient
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                // Cria a requisição HTTP GET
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //System.out.println(response.body());
                String json = response.body();

                Gson gson = new GsonBuilder().create();

                // Converte a string JSON para o objeto DadosConversao
                DadosConversao dados = gson.fromJson(json, DadosConversao.class);

                // Extrai o valor de "COP" do mapa
                double valorConversao = dados.conversion_rates.get("COP");
                double resultado = valor * valorConversao;

                System.out.println("Valor " + valor + " [USD] corresponde ao valor final de =>> " + resultado + "[COP]");
                try {
                    // Converte a string JSON para o objeto DadosConversao
                    // Esta linha pode lançar JsonSyntaxException se o JSON for inválido
                    dados = gson.fromJson(json, DadosConversao.class);

                    // Verifica se o mapa de taxas de conversão não é nulo e se contém a chave "COP"
                    if (dados.conversion_rates != null && dados.conversion_rates.containsKey("COP")) {
                        // Extrai o valor de "COP" do mapa
                        valorConversao = dados.conversion_rates.get("COP");
                        System.out.println("O valor da taxa de conversão para COP é: " + valorConversao);

                        // Aqui você pode continuar com seus cálculos, por exemplo:
                        // double resultado = valor * valorConversao;
                        // System.out.println("Valor " + valor +" [USD] corresponde ao valor final de =>> "  + resultado + "[COP]");

                    } else {
                        // Caso a chave "COP" não seja encontrada no JSON ou o mapa seja nulo
                        System.out.println("A taxa de conversão para COP não foi encontrada ou o formato do JSON está incompleto.");
                    }

                } catch (JsonSyntaxException e) {
                    // Captura exceção se o JSON for malformado ou se o valor de "COP" não for um número
                    System.err.println("Erro ao processar o JSON. O valor de COP pode não ser um número válido ou a estrutura está incorreta: " + e.getMessage());
                    // Opcional: imprimir o JSON para depuração
                    // System.err.println("JSON recebido: " + json);
                } catch (Exception e) {
                    // Captura qualquer outra exceção inesperada que possa ocorrer durante o processamento
                    System.err.println("Ocorreu um erro inesperado ao lidar com os dados de conversão: " + e.getMessage());
                }

                System.out.println();
                System.out.println();

                System.out.println(menu);
                System.out.println("Escolha uma opção válida: ");
                opcao = leitura.nextInt();

            }
//Opcao 6 - Peso colombiano [COP] =>> Dólar [USD]
            else if (opcao == 6) {
                System.out.println("Digite o valor que deseja converter");
                double valor = leitura.nextDouble();

                String endereco = "https://v6.exchangerate-api.com/v6/40c9ecabf00d9e958faa9e27/latest/COP";

                // Cria uma instância do HttpClient
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                // Cria a requisição HTTP GET
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //System.out.println(response.body());
                String json = response.body();

                Gson gson = new GsonBuilder().create();

                // Converte a string JSON para o objeto DadosConversao
                DadosConversao dados = gson.fromJson(json, DadosConversao.class);

                // Extrai o valor de "USD" do mapa
                double valorConversao = dados.conversion_rates.get("USD");
                double resultado = valor * valorConversao;

                System.out.println("Valor " + valor + " [COP] corresponde ao valor final de =>> " + resultado + "[USD]");
                try {
                    // Converte a string JSON para o objeto DadosConversao
                    // Esta linha pode lançar JsonSyntaxException se o JSON for inválido
                    dados = gson.fromJson(json, DadosConversao.class);

                    // Verifica se o mapa de taxas de conversão não é nulo e se contém a chave "USD"
                    if (dados.conversion_rates != null && dados.conversion_rates.containsKey("USD")) {
                        // Extrai o valor de "USD" do mapa
                        valorConversao = dados.conversion_rates.get("USD");
                        System.out.println("O valor da taxa de conversão para USD é: " + valorConversao);

                        // Aqui você pode continuar com seus cálculos, por exemplo:
                        // double resultado = valor * valorConversao;
                        // System.out.println("Valor " + valor +" [COP] corresponde ao valor final de =>> "  + resultado + "[USD]");

                    } else {
                        // Caso a chave "USD" não seja encontrada no JSON ou o mapa seja nulo
                        System.out.println("A taxa de conversão para USD não foi encontrada ou o formato do JSON está incompleto.");
                    }

                } catch (JsonSyntaxException e) {
                    // Captura exceção se o JSON for malformado ou se o valor de "USD" não for um número
                    System.err.println("Erro ao processar o JSON. O valor de USD pode não ser um número válido ou a estrutura está incorreta: " + e.getMessage());
                    // Opcional: imprimir o JSON para depuração
                    // System.err.println("JSON recebido: " + json);
                } catch (Exception e) {
                    // Captura qualquer outra exceção inesperada que possa ocorrer durante o processamento
                    System.err.println("Ocorreu um erro inesperado ao lidar com os dados de conversão: " + e.getMessage());
                }

                System.out.println();
                System.out.println();

                System.out.println(menu);
                System.out.println("Escolha uma opção válida: ");
                opcao = leitura.nextInt();

            }
        }
        leitura.close();
        System.out.println("Obrigada por utilizar nosso Conversor de Moedas!");
    }
}
