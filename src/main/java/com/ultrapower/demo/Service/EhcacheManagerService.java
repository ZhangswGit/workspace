package com.ultrapower.demo.Service;

/**
 * 本地缓存
 * 
 * @author 
 * @date 2019年10月22日
 *
 */
public interface EhcacheManagerService<T> {
	/**
	 * 
	 * @param id
	 * @param code
	 * @return
	 */
	public T addPara(String id, T t);

	/**
	 * 取缓存
	 * 
	 * @param id
	 * @return
	 */
	public T findByName(String id);
}
