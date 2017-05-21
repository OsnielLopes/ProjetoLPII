
import java.io.*;
import static java.lang.System.in;
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

        int qtdRodadas = 0;
        if (qtdTimesEPar) {
            qtdRodadas = 2 * qtdTimes - 2;
        } else {
            qtdRodadas = 2 * qtdTimes;
        }

        int qtdPartidasPorRodada = 0;
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
        //cria rodadas do turno
        for (int i = 0; i < qtdRodadas; i++) {

            rodadas[i] = new Rodada(qtdPartidasPorRodada);
            rodadas[i].setData((Calendar) data.clone());
            data.add(Calendar.WEEK_OF_MONTH, 1);

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
        for (int i = 0; i < qtdRodadas; i++) {
            Partida p = rodadas[i].getPartida(0);
            for (int k = 1; k < qtdPartidasPorRodada; k += 2) {

                if (p.getAdversários()[1] == null) {
                    p.setLocal(null);
                } else {
                    p.setLocal(p.getAdversários()[1].getCasa());
                }
            }
            for (int k = 0; k < qtdPartidasPorRodada; k += 2) {

                if (p.getAdversários()[0] == null) {
                    p.setLocal(null);
                } else {
                    p.setLocal(p.getAdversários()[0].getCasa());
                }
            }
            if (i > qtdRodadas / 2) {
                p.trocaLocal();
            }
        }

        OpcMenu opcMenu = null;
        do {
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
                                Time[] adversarios = p.getAdversários();
                                if (adversarios[0] == time || adversarios[1] == time) {
                                    if (adversarios[0] == null) {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[1].getNome() + " não jogará";
                                    } else if (adversarios[1] == null) {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " não jogará";
                                    } else {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " vs "
                                                + adversarios[1].getNome() + " - "
                                                + p.getLocal().getCidade() + "/"
                                                + p.getLocal().getEstado();
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
                                Time[] adversarios = p.getAdversários();
                                if (p.getLocal().getCidade() == cidade) {
                                    if (adversarios[0] == null) {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[1].getNome() + " não jogará";
                                    } else if (adversarios[1] == null) {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " não jogará";
                                    } else {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " vs "
                                                + adversarios[1].getNome() + " - "
                                                + p.getLocal().getCidade() + "/"
                                                + p.getLocal().getEstado();
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
                                Time[] adversarios = p.getAdversários();
                                if (p.getLocal().getEstado() == estado) {
                                    if (adversarios[0] == null) {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[1].getNome() + " não jogará";
                                    } else if (adversarios[1] == null) {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " não jogará";
                                    } else {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " vs "
                                                + adversarios[1].getNome() + " - "
                                                + p.getLocal().getCidade() + "/"
                                                + p.getLocal().getEstado();
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
                                    Time[] adversarios = p.getAdversários();
                                    if (adversarios[0] == null) {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[1].getNome() + " não jogará";
                                    } else if (adversarios[1] == null) {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " não jogará";
                                    } else {
                                        resposta += "\nRodada " + (i + 1) + " - "
                                                + adversarios[0].getNome() + " vs "
                                                + adversarios[1].getNome() + " - "
                                                + p.getLocal().getCidade() + "/"
                                                + p.getLocal().getEstado();
                                    }
                                }
                            }
                        }
                }
                Tela.exibeResultado(resposta);
            }else if(opcMenu == OpcMenu.gravarArquivo){
                
            }
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
//            String lin1 = "";
//            for (Time t: linha1){
//                if(t!=null){
//                    lin1 += t.getNome()+ " --- ";
//                }else{
//                    lin1 += "vazio"+ " --- ";
//                }
//            }
//            String lin2 = "";
//            for (Time t: linha2){
//                if(t!=null){
//                    lin2 += t.getNome()+ " --- ";
//                }else{
//                    lin2 += "vazio"+ " --- ";
//                }
//            }
//            System.out.println(lin1);
//            System.out.println(lin2);
