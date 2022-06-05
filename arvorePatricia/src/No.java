public class No{
    private String info;
    private No vLig[];
    private boolean flag;
    private int tl;

    public No() {
        this.info = "";
        this.vLig = new No[26];
        this.flag = false;
        this.tl = 0;
    }

    public No(String info) {
        this();
        this.info = info;
        this.flag = true;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public No getVLig(int p) {
        return vLig[p];
    }

    public void setVLig(int p, No vLig) {
        this.vLig[p] = vLig;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public void remaneja(int pos) {
        for(int i = tl; i > pos; i--) {
            vLig[i] = vLig[i-1];
        }
    }
}
