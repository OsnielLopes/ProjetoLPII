
public class Partida {

    private Time timeA;
    private Time timeB;
    private Local local;

    public Partida(Time timeA, Time timeB) {
        this.timeA = timeA;
        this.timeB = timeB;
    }

    public void setLocal(Local l) {
        this.local = l;
    }

    public void trocaMando() {
        Time pivo = timeA;
        timeA = timeB;
        timeB = pivo;
    }

    public Local getLocal() {
        return this.local;
    }

    public Time[] getAdversarios() {
        Time adversarios[] = {timeA, timeB};
        return adversarios;
    }

    public String toString(int maior) {
        if (this.timeA == null) {
            return "   "+this.timeB.getNome() + " não jogará";
        } else if (this.timeB == null) {
            return "   "+this.timeA.getNome() + " não jogará";
        } else if (this.local == null){
            return String.format("   %-" + maior + "s", this.timeA.getNome())
                    + String.format(" vs %-" + maior + "s", this.timeB.getNome())
                    + " - local nulo";
        }
        else {
            return String.format("   %-" + maior + "s", this.timeA.getNome())
                    + String.format(" vs %-" + maior + "s", this.timeB.getNome())
                    + " - "
                    + this.local.getCidade() + "/"
                    + this.local.getEstado();
        }

    }
}
