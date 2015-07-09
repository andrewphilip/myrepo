package andy.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOSelEcho {

	private int port[] ={8501,8502};
	private ByteBuffer buf=ByteBuffer.allocate(1024);

	public NIOSelEcho(){
		try{
			initSelectors();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void initSelectors() throws Exception{
		
		Selector sel=Selector.open();
		
		// create selector for each listener-port
		for(int n=0; n<port.length; n++){
			
			ServerSocketChannel ssc=ServerSocketChannel.open();
			ssc.configureBlocking(false);
			
			ServerSocket ss = ssc.socket();
			ss.bind(new InetSocketAddress(port[n]));
			
			SelectionKey skey=ssc.register(sel, SelectionKey.OP_ACCEPT);
			System.out.println("Listener listening on "+port[n]);
		}
		
		while(true){
			int num=sel.select();
			Iterator iter=sel.selectedKeys().iterator();
			while(iter.hasNext()){
				SelectionKey aKey= (SelectionKey)iter.next();
				if((aKey.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
					ServerSocketChannel ssc=(ServerSocketChannel)aKey.channel();
					SocketChannel sc=ssc.accept();
					sc.configureBlocking(false);
					
					//register selector for read
					SelectionKey rKey=sc.register(sel, SelectionKey.OP_READ);
					iter.remove();
					System.out.println("Channel listening to read data..."+ sc);
				}
				else if((aKey.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
					SocketChannel sc= (SocketChannel)aKey.channel();
					int nbytes=0;
					
					while(true){
						buf.clear();
						int n= sc.read(buf);
						if(n == -1){
							break;
						}
						buf.flip();
						sc.write(buf);
						nbytes += n;
					}
					System.out.println("Echoed "+nbytes+ "  from "+ sc);
					iter.remove();
				}
				
			}
		}
		
	}	
	
	public static void main(String[] args) {
		
		new NIOSelEcho();
	}

	
}
