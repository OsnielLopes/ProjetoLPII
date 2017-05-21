
import java.util.Calendar;

/**
 *
 * @author osniellopesteixeira
 */
public class Rodada {
    
    private Calendar data;
    private Partida[] partidas;
    private int proxIndice;
    
    public Rodada(int qtdPartidas){
        this.partidas = new Partida[qtdPartidas];
        this.proxIndice = 0;
    }
    
    public void add(Partida p){
        this.partidas[proxIndice] = p;
        this.proxIndice++;
    }
    
    public void setData(Calendar c){
        this.data = c;
    }
    
    public Calendar getData(){
        return this.data;
    }
    
    public Partida getPartida(int i){
        return this.partidas[i];
    }
    
    public int getQtdPartidas(){
        return this.partidas.length;
    }
}