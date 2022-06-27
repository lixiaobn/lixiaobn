import java.net.*;

class TestUdp {
    public static void main(String[] args) throws Exception {
        //recv();
        send();
      /*  String str = "12345";
        String strLast;
        if(str.length()>4){
            strLast = str.substring(str.length()-4);
        }else {
            strLast = str;
        }
        System.out.println(strLast);*/
    }

    public static void send() throws Exception {
        DatagramSocket s = new DatagramSocket(null);
        s.setReuseAddress(true);
        s.bind(new InetSocketAddress(9999));
        String sss = "TEMP?";
        byte[] bytes = sss.getBytes();
        int length = sss.length();
        DatagramPacket p = new DatagramPacket(bytes, length, new InetSocketAddress("10.6.32.1", 8000));
        s.send(p);
        s.close();
    }

    public static void recv() throws Exception {
        DatagramSocket s = new DatagramSocket(null);
        s.setReuseAddress(true);
        s.bind(new InetSocketAddress(9999));
        byte[] b = new byte[1024];
        DatagramPacket p = new DatagramPacket(b, 0, b.length);
        s.receive(p);
        System.out.println(p.getAddress() + ":" + p.getPort() + " " + p.getLength());
        InetAddress address = p.getAddress();
        byte[] data = p.getData();
        int len = p.getLength();
        System.out.println(address);
        System.out.println(new String(data, 0, len));
        System.out.println(String.valueOf(data[0]));
        String sm=new String(data, 0, len);
        String [] a =sm.split(",");
        System.out.println(a[0]);
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuffer sb = new StringBuffer(bArr.length);
        String sTmp;

        for (int i = 0; i < bArr.length; i++) {
            sTmp = Integer.toHexString(0xFF & bArr[i]);
            if (sTmp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTmp.toUpperCase());
        }
        return sb.toString();
    }

}