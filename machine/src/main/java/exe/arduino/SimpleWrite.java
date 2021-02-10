package exe.arduino;

import gnu.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

public class SimpleWrite {

    static Enumeration portList;
    static CommPortIdentifier portId;
    static SerialPort serialPort;
    static OutputStream outputStream;


    //for windows
    private static final String PORT_NAMES[] = { "COM1" , "COM2" , "COM3" , "COM6" , "COM7" , "COM8", "COM9", "COM10", "COM11" };
    //private BufferedReader input;
    private static OutputStream output;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;

    /**
     * Initialize communication.
     * To do at the begining, when the graphic interface is created.
     */
    public static void initialize()
    {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        while (portEnum.hasMoreElements())
        {
            CommPortIdentifier currentPortID = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES)
            {
                if(currentPortID.getName().equals(portName))
                {
                    portId = currentPortID;
                    break;
                }
            }
        }

        if (portId == null)
        {
            System.out.println("Could not found COM port.");
            return;
        }

        try {
            System.out.println(portId.getName());

            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(SimpleWrite.class.getClass().getSimpleName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the stream
            output = serialPort.getOutputStream();

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * This should be called when you stop using the port, when closing the graphic interface.
     * This will prevent port locking.
     */
    public static void close() {
        if (serialPort != null) {
            serialPort.close();
        }
    }

    public static void sendMessage(char message) throws IOException {

        output.write(message);
        output.flush();

    }

    public static void depot() throws IOException, InterruptedException {
        sendMessage('0');
        System.out.println("0");
        synchronized (SimpleWrite.class) {
            SimpleWrite.class.wait(2000);
        }
    }

    public static void retrait() throws IOException, InterruptedException {
        sendMessage('1');
        System.out.println("1");
        synchronized (SimpleWrite.class) {
            SimpleWrite.class.wait(5000);
        }
    }
}
