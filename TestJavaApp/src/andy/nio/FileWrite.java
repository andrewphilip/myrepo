package andy.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileWrite {

	public static void main(String[] args) {

		try {
			FileInputStream fin= new FileInputStream("C:\\inputfiles\\laustoo\\AL_2014.txt");
			FileOutputStream fout=new FileOutputStream("C:\\inputfiles\\laustoo\\nio_AL_2014.txt");
			FileChannel fc=fin.getChannel();
			FileChannel fco=fout.getChannel();
			ByteBuffer buf=ByteBuffer.allocate(1024);
			int num=fc.read(buf);
			
			while(num != -1){
				buf.flip();
				fco.write(buf);
				buf.clear();
				num= fc.read(buf);
			}
			fc.close();
			fco.close();
			fin.close();
			fout.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
