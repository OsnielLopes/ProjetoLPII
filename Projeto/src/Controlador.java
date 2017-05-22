
import java.io.*;
import java.util.Calendar;

public class Controlador {

    public static void main(String[] args) throws IOException {
        OpcEntrada opc = Tela.opcLeitura();
        Time[] times = null;
        if (opc.equals(OpcEntrada.Console)) {
            times = leitura();
        } else if (opc.equals(OpcEntrada.Arquivo)) {
            String[] infoLeituraArquivo = Tela.getInfoLeituraArquivo();
            times = leitura(infoLeituraArquivo[0], infoLeituraArquivo[1]);
        }

        int qtdTimes = times.length;
        boolean qtdTimesEPar = qtdTimes % 2 == 0;
        Calendar data = Tela.inicioDosJogos();

        int qtdRodadas;
        if (qtdTimesEPar) {
            qtdRodadas = 2 * qtdTimes - 2;
        } else {
            qtdRodadas = 2 * qtdTimes;
        }

        int qtdPartidasPorRodada;
        if (qtdTimesEPar) {
            qtdPartidasPorRodada = qtdTimes / 2;
        } else {
            qtdPartidasPorRodada = (qtdTimes + 1) / 2;
        }

        Rodada[] rodadas = new Rodada[qtdRodadas];
        //Divide o vetor de times em dois vetores
        Time[] linha1 = new Time[qtdPartidasPorRodada];
        Time[] linha2 = new Time[qtdPartidasPorRodada];
        for (int j = 0; j < qtdPartidasPorRodada; j++) {
            linha1[j] = times[j];
            if (j + qtdPartidasPorRodada < qtdTimes) {
                linha2[j] = times[j + qtdPartidasPorRodada];
            } else {
                linha2[j] = null;
            }
        }
        //cria rodadas
        for (int i = 0; i < qtdRodadas; i++) {

            rodadas[i] = new Rodada(qtdPartidasPorRodada);
            rodadas[i].setData((Calendar) data.clone());
            int ano = data.get(Calendar.YEAR);
            int mes = data.get(Calendar.MONTH);
            int dia = data.get(Calendar.DAY_OF_MONTH);
            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12 && 31 - dia < 7) {
                data.set(Calendar.DAY_OF_MONTH, dia - 24);
                data.add(Calendar.MONTH, 1);
            } else if (mes != 2 && 30 - dia < 7) {
                data.set(Calendar.DAY_OF_MONTH, dia - 23);
                data.add(Calendar.MONTH, 1);
            } else if (ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0) && mes == 2 && 29 - dia < 7) {
                data.set(Calendar.DAY_OF_MONTH, dia - 22);
                data.add(Calendar.MONTH, 1);
            } else if (mes == 2 && 28-dia<7){
                data.set(Calendar.DAY_OF_MONTH, dia - 21);
                data.add(Calendar.MONTH, 1);
            }
            else {
                data.add(Calendar.WEEK_OF_MONTH, 1);
            }

            //cria partidas e adiciona na rodada
            for (int j = 0; j < qtdPartidasPorRodada; j++) {
                Partida p = new Partida(linha1[j], linha2[j]);
                rodadas[i].add(p);
            }

            //altera a ordem dos times
            Time pivo1 = linha1[qtdPartidasPorRodada - 1];
            Time pivo2 = linha2[0];

            for (int j = qtdPartidasPorRodada - 1; j > 1; j--) {
                linha1[j] = linha1[j - 1];
            }
            for (int j = 0; j < qtdPartidasPorRodada - 1; j++) {
                linha2[j] = linha2[j + 1];
            }
            linha1[1] = pivo2;
            linha2[qtdPartidasPorRodada - 1] = pivo1;

        }

        //define mando de campo
        for (int ir = 0; ir < qtdRodadas; ir++) {
            for (int ip = 0; ip < qtdPartidasPorRodada; ip++) {
                Partida p = rodadas[ir].getPartida(ip);
                if (ip % 2 != 0) {
                    p.trocaMando();
                }
                if (ir % 2 != 0 && ip == 0) {
                    p.trocaMando();
                }
                Time timeDaCasa = p.getAdversarios()[0];
                if (timeDaCasa != null) {
                    p.setLocal(p.getAdversarios()[0].getCasa());
                } else {
                    p.setLocal(null);
                }
            }
        }

        int maiorNome = 0;
        for (Time t : times) {
            if (t.getNome().length() > maiorNome) {
                maiorNome = t.getNome().length();
            }
        }
        OpcMenu opcMenu = null;
        do {
            opcMenu = OpcMenu.gravarArquivo;
            opcMenu = Tela.menu();
            if (opcMenu == OpcMenu.pesquisar) {
                String resposta = "";
                Filtro opcPesquisa = Tela.escolheFormaPesquisa();
                switch (opcPesquisa) {
                    case time:
                        int indiceTime = Tela.escolhaTime(times);
                        Time time = times[indiceTime];
                        for (int i = 0; i < qtdRodadas; i++) {
                            for (int j = 0; j < qtdPartidasPorRodada; j++) {
                                Partida p = rodadas[i].getPartida(j);
                                Time[] adversarios = p.getAdversarios();
                                if (adversarios[0] == time || adversarios[1] == time) {
                                    if (adversarios[0] == null) {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[1].getNome() + " não jogará\n";
                                    } else if (adversarios[1] == null) {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " não jogará\n";
                                    } else {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " vs "
                                                + adversarios[1].getNome() + " - "
                                                + p.getLocal().getCidade() + "/"
                                                + p.getLocal().getEstado() + "\n";
                                    }
                                }
                            }
                        }
                        break;
                    case cidade:
                        String cidade = Tela.leCidade();
                        for (int i = 0; i < qtdRodadas; i++) {
                            for (int j = 0; j < qtdPartidasPorRodada; j++) {
                                Partida p = rodadas[i].getPartida(j);
                                Time[] adversarios = p.getAdversarios();
                                if (p.getLocal().getCidade().equals(cidade)) {
                                    if (adversarios[0] == null) {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[1].getNome() + " não jogará\n";
                                    } else if (adversarios[1] == null) {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " não jogará\n";
                                    } else {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " vs "
                                                + adversarios[1].getNome() + " - "
                                                + p.getLocal().getCidade() + "/"
                                                + p.getLocal().getEstado() + "\n";
                                    }
                                }
                            }
                        }
                        break;
                    case estado:
                        String estado = Tela.leEstado();
                        for (int i = 0; i < qtdRodadas; i++) {
                            for (int j = 0; j < qtdPartidasPorRodada; j++) {
                                Partida p = rodadas[i].getPartida(j);
                                Time[] adversarios = p.getAdversarios();
                                if (p.getLocal().getEstado().equals(estado)) {
                                    if (adversarios[0] == null) {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[1].getNome() + " não jogará\n";
                                    } else if (adversarios[1] == null) {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " não jogará\n";
                                    } else {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " vs "
                                                + adversarios[1].getNome() + " - "
                                                + p.getLocal().getCidade() + "/"
                                                + p.getLocal().getEstado() + "\n";
                                    }
                                }
                            }
                        }
                        break;
                    case mes:
                        int mes = Tela.escolheMes();
                        for (int i = 0; i < qtdRodadas; i++) {
                            if (rodadas[i].getData().get(Calendar.MONTH) == mes) {
                                for (int j = 0; j < qtdPartidasPorRodada; j++) {
                                    Partida p = rodadas[i].getPartida(j);
                                    Time[] adversarios = p.getAdversarios();
                                    if (adversarios[0] == null) {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[1].getNome() + " não jogará\n";
                                    } else if (adversarios[1] == null) {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " não jogará\n";
                                    } else {
                                        resposta += "Rodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " vs "
                                                + adversarios[1].getNome() + " - "
                                                + p.getLocal().getCidade() + "/"
                                                + p.getLocal().getEstado() + "\n";
                                    }
                                }
                            }
                        }
                }
                Tela.exibeResultado(resposta);
            } else if (opcMenu == OpcMenu.gravarArquivo) {
                //String nomeArquivo = "teste.txt";
                String nomeArquivo = Tela.informaNomeArquivo();
                if (!nomeArquivo.contains(".txt")) {
                    nomeArquivo += ".txt";
                }

                PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), "UTF-8"));
                for (int i = 0; i < qtdRodadas; i++) {
                    pw.write("Rodada " + (i + 1) + "   " + rodadas[i].getDataFormatada() + ":\n");
                    String naoJoga = "";
                    for (int j = 0; j < qtdPartidasPorRodada; j++) {
                        String resposta = "";
                        Partida p = rodadas[i].getPartida(j);
                        resposta += p.toString(maiorNome) + "\n";
                        if (resposta.contains("não jogará")) {
                            naoJoga = resposta;
                        } else {
                            pw.write(resposta);
                        }
                    }
                    if (!naoJoga.isEmpty()) {
                        pw.write(naoJoga);
                    }
                    pw.write("\n");
                }
                pw.close();

            }
            //opcMenu = OpcMenu.sair;
        } while (opcMenu != OpcMenu.sair);

    }

    //Instanciar Times Manualmente
    private static Time[] leitura() {
        int qtdTimes = Tela.qtdTimes();
        Time times[] = new Time[qtdTimes];
        int i = 0;
        do {
            String time[] = Tela.digitaTime();
            if (time != null) {
                times[i] = new Time(time[0], time[1], time[2]);
                i += 1;
            }
        } while (i < qtdTimes);
        return times;
    }

    //Instanciando times a partir de um arquivo
    private static Time[] leitura(String filename, String separador) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader in = new BufferedReader(fr);
        int qtdTimes = Integer.parseInt(in.readLine());
        Time times[] = new Time[qtdTimes];
        for (int i = 0; i < qtdTimes; i++) {
            String linha[] = in.readLine().split(separador);
            times[i] = new Time(linha[0], linha[1], linha[2]);
        }
        fr.close();
        return times;
    }
}
