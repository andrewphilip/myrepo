package andy.nio;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class NotifyApp {

	public static void main(String[] args) {

		Path dir= Paths.get("C:\\inputfiles\\laustoo");
		System.out.println("Watching laustoo directory under inputfiles....");
		
		try {
			WatchService ws=dir.getFileSystem().newWatchService();
			dir.register(ws, StandardWatchEventKinds.ENTRY_CREATE);
			WatchKey wkey=ws.take();
			List<WatchEvent<?>> events= wkey.pollEvents();
			for(WatchEvent evt:events){
				System.out.println("New File has been created..."+evt.context().toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
