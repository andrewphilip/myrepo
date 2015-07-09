package andy.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileRead {

	public static void main(String[] args) {

		try {
			FileInputStream fin= new FileInputStream("C:\\inputfiles\\laustoo\\AL_2014.txt");
			FileChannel fc=fin.getChannel();
			
			ByteBuffer buf=ByteBuffer.allocate(1024);
			int num=fc.read(buf);
			
			while(num != -1){
				buf.flip();
				for(int i=0; i < buf.limit(); i++){
					System.out.print((char) buf.get());
				}
				System.out.println();
				buf.clear();
				num= fc.read(buf);
			}
			fc.close();
			fin.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
