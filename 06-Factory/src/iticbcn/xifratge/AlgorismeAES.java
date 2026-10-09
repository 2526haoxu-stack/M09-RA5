package iticbcn.xifratge;

public class AlgorismeAES extends AlgorismeFactory {
    @Override
    public Xifrador getXifrador() {
        return new XifradorAES();
    }
    
}