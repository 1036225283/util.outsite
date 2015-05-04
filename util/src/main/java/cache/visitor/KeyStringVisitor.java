package cache.visitor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import cache.service.keystring.KeyStringService;
import cache.service.keystring.KeyStringServiceImpl;

public class KeyStringVisitor {

	public KeyStringVisitor() {
		init();
	}

	private KeyStringService keyStringService;

	private String url = "//localhost:8029/SAMPLE-SERVER";

	public Boolean put(String key, String value) {
		try {
			keyStringService.put(key, value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return true;
	}

	public String get(String key) {
		String value = null;
		try {
			value = keyStringService.get(key);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return value;
	}

	public Boolean remove(String key) {
		Boolean result = false;
		try {
			result = keyStringService.remove(key);
		} catch (RemoteException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public Boolean containsKey(String key) {
		Boolean result = false;
		try {
			result = keyStringService.containsKey(key);
		} catch (RemoteException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	private void init() {
		try {
			keyStringService = (KeyStringService) Naming.lookup(url);
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
		} finally {
			try {
				keyStringService = (KeyStringService) Naming.lookup(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws RemoteException,
			MalformedURLException {
		KeyStringVisitor visitor = new KeyStringVisitor();
		visitor.put("hello", "wordl");
		System.out.println(visitor.get("hello"));
	}
}
