package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory {
    @Override
    public Xifrador getXifrador() {
        return new XifradorPolialfabetic();
    }
    
}