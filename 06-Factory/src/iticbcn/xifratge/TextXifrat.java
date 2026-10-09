package iticbcn.xifratge;

public class TextXifrat {
    private byte[] arrayDeBytes;

    public String toString() { return new String(arrayDeBytes); }

    public byte[] getBytes() { return arrayDeBytes; }

    public TextXifrat(byte[] arrayDeBytes) { this.arrayDeBytes = arrayDeBytes; }
}