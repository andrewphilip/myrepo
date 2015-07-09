package andy.nio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.util.Iterator;

public class AndyNIOServer {
	private int port=8777;
	private String host="127.0.0.1";
	private Selector selector;
	private byte[] data=null;
	private ServerSocketChannel serverChannel;
	
	public AndyNIOServer(){
		this.selector= init();
		process();
	}
	
	private Selector init(){
		Selector sel=null;
		try {
			 sel= SelectorProvider.provider().openSelector();
			 this.serverChannel=ServerSocketChannel.open();
			 serverChannel.configureBlocking(false); // create non-blocking server
			 InetSocketAddress inet=new InetSocketAddress(host, port);
			 serverChannel.bind(inet);
			 serverChannel.register(sel,SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sel;
	}
	
	private void process() {
		try{
			while(true){
				this.selector.select();
				Iterator<?> iter = this.selector.selectedKeys().iterator();
				while(iter.hasNext()){
					SelectionKey aKey=(SelectionKey)iter.next();
					iter.remove();
					
					
					if(!aKey.isValid()){
						continue;
					}
					
					if(aKey.isAcceptable()){
						ServerSocketChannel ssc=(ServerSocketChannel)aKey.channel();
						SocketChannel sc=ssc.accept();
						sc.configureBlocking(false);
						System.out.println("Connection accepted from "+ sc);
						//register this socket for read operation
						sc.register(this.selector, SelectionKey.OP_READ);
						//sc.register(this.selector, SelectionKey.OP_WRITE);
					}
					else if(aKey.isReadable()){
						SocketChannel sc=(SocketChannel)aKey.channel();
						ByteBuffer buf =ByteBuffer.allocate(512);
						int numRead;
						StringBuilder sb=new StringBuilder();
						
						//BufferedInputStream bin=new BufferedInputStream();
						try{
							numRead=sc.read(buf);
//							if(numRead == -1){
//								aKey.cancel();
//								sc.close();
//							}
							while(numRead != -1){
								buf.flip();
								//System.out.print("Received: ");
								String txt=Charset.defaultCharset().decode(buf).toString();
								if(txt.equals("\n")){
									break;
								}
//								for(int i=0; i < buf.limit(); i++){
//									//System.out.print((char) buf.get());
//									sb.append((char) buf.get());
//								}
								//byte[] data = buf.array();
								buf.clear();
								
//								if(txt.trim().equals("quit")){
//									sc.write(Charset.defaultCharset().encode(CharBuffer.wrap("Bye.")));
//									aKey.cancel();
//									sc.close();
//								}
								
								//sc.write(Charset.defaultCharset().encode(CharBuffer.wrap(txt)));								numRead=sc.read(buf);
								sb.append(txt);
								//System.out.print(sb.toString()+"\n");
								//break;
								numRead=sc.read(buf);
							}
							System.out.println("Storing data for write operation ... "+numRead);
							this.data=sb.toString().getBytes();
							aKey.interestOps(SelectionKey.OP_WRITE);
						}catch(IOException e){
							aKey.cancel();
							sc.close();
							return;
						}	
//						if(numRead <= 0){
//							//aKey.channel().close();
//							//aKey.cancel();
//							aKey.interestOps(SelectionKey.OP_WRITE);
//							return;
//						}

					}
					else if(aKey.isWritable()){
						SocketChannel sc=(SocketChannel)aKey.channel();
						ByteBuffer buf=ByteBuffer.allocate(512);
						if(this.data != null){
							System.out.println("Server>>: "+ Charset.defaultCharset().decode(buf.wrap(data)));
							sc.write(ByteBuffer.wrap(data));
						}
						aKey.interestOps(SelectionKey.OP_READ);
						//sc.close();
					}
					
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new AndyNIOServer();
	}

	
}
