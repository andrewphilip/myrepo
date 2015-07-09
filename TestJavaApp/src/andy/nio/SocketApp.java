package andy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SocketApp {
	private static final String CHANNEL_KEY="CHANNEL_TYP";
	private static final String SERVER_CHANNEL="CH4SERVER";
	private static final String CLIENT_CHANNEL="CH4CLIENT";
	
	public static void main(String[] args) {

		int port=8601;
		
		try {
			ServerSocketChannel ssc=ServerSocketChannel.open();
			ssc.bind(new InetSocketAddress("127.0.0.1", port));
			ssc.configureBlocking(false);
			
			Selector sel= Selector.open();
			SelectionKey serverKey=ssc.register(sel, SelectionKey.OP_ACCEPT);
			Map<String, String> aMap=new HashMap<String, String>();
			aMap.put(CHANNEL_KEY, SERVER_CHANNEL);
			serverKey.attach(aMap);
			
			System.out.println("Server is listening on port "+ port);
			while(true){
				if(sel.select() > 0){
					System.out.println("Are there any channels ready for I/O operations :"+sel.select());
					Iterator<?> iter=sel.selectedKeys().iterator();
					while(iter.hasNext()){
						SelectionKey aKey=(SelectionKey)iter.next();
						if(aKey.isAcceptable())
						{	
							ServerSocketChannel serverCh=(ServerSocketChannel)aKey.channel();
							SocketChannel sc=serverCh.accept();
							System.out.println("Accepted connection from "+sc);
							if(sc != null){
								sc.configureBlocking(false);
								//sc.register(sel, SelectionKey.OP_READ,SelectionKey.OP_WRITE);
								SelectionKey wkey=sc.register(sel, SelectionKey.OP_READ,SelectionKey.OP_WRITE);
								CharBuffer buf=CharBuffer.wrap("Laus Dei..");
								System.out.println("Serving sending ....");
//								while(buf.hasRemaining()){
//									sc.write(Charset.defaultCharset().encode(buf));
//								}
//								buf.clear();
								wkey.attach(buf);
							}
							else{
								ByteBuffer buf=ByteBuffer.allocate(256);
								SocketChannel client=(SocketChannel)aKey.channel();
								int cnt=0;
								while((cnt = client.read(buf)) != -1){
									buf.flip();
									System.out.print("Server Received:"+ Charset.defaultCharset().decode(buf));
									buf.clear();
								}
								System.out.println();
								client.close();
							}
						}
						else if(aKey.isWritable()){
							
							ByteBuffer buf = ByteBuffer.allocate(256);
							SocketChannel sc= (SocketChannel)aKey.channel();
							
//							int nRead=0;
//							if(aKey.isReadable()){
//								if((nRead = sc.read(buf)) > 0){
//									buf.flip();
//									System.out.println("Client: "+Charset.defaultCharset().decode(buf));
//									buf.clear();
//								}
//								sc.close();
//							}
							CharBuffer outByte=(CharBuffer)aKey.attachment();
							
							if(!outByte.hasRemaining()){
								outByte.rewind();
							}
							sc.write(Charset.defaultCharset().encode(outByte));
						}
						else if(aKey.isReadable()){
							ByteBuffer buf=ByteBuffer.allocate(256);
							SocketChannel sc=(SocketChannel)aKey.channel();
							int cnt=0;
							if((cnt = sc.read(buf)) != -1){
								buf.flip();
								System.out.println("Server Received:"+ Charset.defaultCharset().decode(buf));
								buf.clear();
							}
							//sc.close();
						}
						iter.remove();
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
