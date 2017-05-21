import java.util.*;
public class Partida {
    
    private Time timeA;
    private Time timeB;
    private Local local;
    
    public Partida(Time timeA, Time timeB){
        this.timeA = timeA;
        this.timeB = timeB;
    }
    
    public void setLocal(Local l){
        this.local = l;
    }
    
    public void trocaLocal(){
        if(this.local != null){
            if(timeA.getCasa() == this.local){
                this.local = timeB.getCasa();
            }else{
                this.local = timeA.getCasa();
            }
        }
    }
    
    public Local getLocal(){
        return this.local;
    }
    public Time[] getAdversários(){
        Time adversarios[] = {timeA, timeB};
        return adversarios;
    }
    
    @Override
    public String toString(){
        if(this.timeA==null)
            return this.timeB.getNome()+" nao joga";
        else if(this.timeB==null)
            return this.timeA.getNome()+" nao joga";
        else
            return this.timeA.getNome()+" vs "+this.timeB.getNome();
    }
}
