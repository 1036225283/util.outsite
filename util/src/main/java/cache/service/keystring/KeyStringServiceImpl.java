package cache.service.keystring;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class KeyStringServiceImpl extends UnicastRemoteObject implements
		KeyStringService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, String> map = new HashMap<String, String>();

	public KeyStringServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Boolean put(String key, String value) {
		map.put(key, value);
		return true;
	}

	@Override
	public String get(String key) {
		return map.get(key);
	}

	@Override
	public Boolean remove(String key) {
		String value = map.remove(key);
		if (value != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean containsKey(String key) {
		return map.containsKey(key);
	}

}
