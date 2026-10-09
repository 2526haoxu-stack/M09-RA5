package iticbcn.xifratge;

public class AlgorismeRotX extends AlgorismeFactory {
    @Override
    public Xifrador getXifrador() {
        return new XifradorRotX();
    }
    
}