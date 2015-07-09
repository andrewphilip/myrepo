package andy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ClientSocketApp {

	public static void main(String[] args) {

		try {
			SocketChannel client=SocketChannel.open();
			client.configureBlocking(false);
			client.connect(new InetSocketAddress("127.0.0.1", 8601));
			while(!client.finishConnect()){
				System.out.println("Connecting...");
			}
			while(true){
				ByteBuffer buf=ByteBuffer.allocate(256);
				int count=0;
				String msg="";
				while((count=client.read(buf)) != -1){
					buf.flip();
					msg += Charset.defaultCharset().decode(buf);
				}
				
				if(msg.length() > 0){
					System.out.println("Client Received: "+ msg);
					CharBuffer wbuf=CharBuffer.wrap("Status: 200");
					while(wbuf.hasRemaining()){
						client.write(Charset.defaultCharset().encode(wbuf));
					}
					msg="";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
