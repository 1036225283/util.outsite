package cache.service.keyobject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KeyObjectService extends Remote {

	/**
	 * 存放key-value
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	Boolean put(String key, Object value) throws RemoteException;

	/**
	 * 根据key获取value
	 * 
	 * @param key
	 * @return
	 */
	Object get(String key) throws RemoteException;

	/**
	 * 移除key
	 * 
	 * @param key
	 * @return
	 */
	Boolean remove(String key) throws RemoteException;

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return
	 */
	Boolean containsKey(String key) throws RemoteException;
}
