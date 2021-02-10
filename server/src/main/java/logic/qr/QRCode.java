package logic.qr;

import structures.IQRCode;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class QRCode implements IQRCode
{

    private String data;

    public QRCode(String data)
    {
        this.data = data;
    }

    public String toString()
    {
        return data;
    }

    public static QRCode random()
    {
        SecureRandom random = new SecureRandom();

        int len= 128;

        byte bytes[] = new byte[128];

        final String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWX1234567890";

        String s = "";

        for(int i=0; i< len; i++)
        {
            int n = random.nextInt(symbols.length());
            s += symbols.charAt(n);
        }

        return new QRCode(s);
    }

}
