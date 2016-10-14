package course.examples.Services.KeyService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import course.examples.Services.KeyCommon.KeyGenerator;

public class KeyGeneratorImpl extends Service {
	private Set<UUID> keys = new HashSet<UUID>();

	private final KeyGenerator.Stub binder = new KeyGenerator.Stub() {
		public String getKey() {
			UUID id;
			synchronized (keys) {
				do {
					id = UUID.randomUUID();
				} while (keys.contains(id));
				keys.add(id);
			}
			return id.toString();
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return this.binder;
	}
}
