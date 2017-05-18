package cache.visitor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import cache.service.keyobject.KeyObjectService;
import cache.service.keystring.KeyStringServiceImpl;

public class KeyObjectVisitor {

	public KeyObjectVisitor() {
		// TODO Auto-generated constructor stub
		init();
	}

	private KeyObjectService keyObjectService;

	private String url = "//localhost:8029/SAMPLE-SERVER";

	public Boolean put(String key, Object value) {
		try {
			keyObjectService.put(key, value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Object get(String key) {
		Object value = null;
		try {
			value = keyObjectService.get(key);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return value;
	}

	public Boolean remove(String key) {
		Boolean result = false;
		try {
			result = keyObjectService.remove(key);
		} catch (RemoteException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public Boolean containsKey(String key) {
		Boolean result = false;
		try {
			result = keyObjectService.containsKey(key);
		} catch (RemoteException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	private void init() {
		try {
			keyObjectService = (KeyObjectService) Naming.lookup(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			try {
				LocateRegistry.createRegistry(8029);
				KeyStringServiceImpl impl = new KeyStringServiceImpl();
				Naming.rebind("//localhost:8029/SAMPLE-SERVER", impl);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws RemoteException,
			MalformedURLException {
		KeyStringVisitor visitor = new KeyStringVisitor();
		System.out.println(visitor.get("hello"));
	}
}
