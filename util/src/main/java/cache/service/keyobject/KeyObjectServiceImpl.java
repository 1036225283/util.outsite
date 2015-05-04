package cache.service.keyobject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class KeyObjectServiceImpl extends UnicastRemoteObject implements
		KeyObjectService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> map = new HashMap<String, Object>();

	public KeyObjectServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Boolean put(String key, Object value) throws RemoteException {
		map.put(key, value);
		return true;
	}

	@Override
	public Object get(String key) throws RemoteException {
		return map.get(key);
	}

	@Override
	public Boolean remove(String key) throws RemoteException {
		Object value = map.remove(key);
		if (value != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean containsKey(String key) throws RemoteException {
		return map.containsKey(key);
	}

}
