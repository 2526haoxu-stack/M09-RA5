package iticbcn.xifratge;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {
    @Override
    public Xifrador getXifrador() {
        return new XifradorMonoalfabetic();
    }
    
}