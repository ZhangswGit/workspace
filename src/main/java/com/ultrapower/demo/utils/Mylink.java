package com.ultrapower.demo.utils;

public class Mylink<T> {
	public static void main(String[] args) {
		Mylink<String> mylink = new Mylink<>();
		mylink.insert("1");
		mylink.insert("2");
		mylink.insert("3");
		mylink.delete("2");
		System.out.println(mylink.length());
		System.out.println(mylink.get(2));
	}

	// 头元素
	final private Node<T> head;
	// 储存模板
	private Node<T> temp = null;

	/**
	 * 链表形式,每一个元素记录相邻元素的引用
	 * @author zsw
	 * @param <T> 泛型
	 */
	private final class Node<T> {
		Node<T> last = null; // 上一个元素的引用
		Node<T> next = null; // 下一个元素的引用
		T data = null; // 当前元素的值

		/**
		 * 构造方法 
		 * @param data 当前元素的数据
		 */
		public Node(T data) {
			this.data = data;
		}

	}

	/**
	 * 构造方法,默认生成头元素,下标从1开始
	 */
	public Mylink() {
		head = new Node<T>((T) new Object());
	}

	/**
	 * 获取链表长度
	 * 
	 * @return
	 */
	public int length() {
		int length = 0;
		temp = head;
		while (temp.next != null) {
			length++;
			temp = temp.next;
		}
		return length;
	}

	/**
	 * 新增元素
	 * 
	 * @param data
	 * @return
	 */
	public T insert(T data) {
		Node newnode = new Node(data);
		temp = head;
		while (temp.next != null) {
			if(data.equals(temp.data)){
				//存在相同的元素，不添加
				return null;
			}
			temp = temp.next;
		}
		newnode.last = temp;
		temp.next = newnode;
		return data;
	}

	/**
	 * 删除某个元素
	 * 
	 * @param data
	 * @return
	 */
	public T delete(T data) {
		temp = head;
		while (temp != null) {
			if (data.equals(temp.data)) {
				//将当前元素的相邻元素关联
				temp.last.next = temp.next;
				return data;
			}
			temp = temp.next;
		}
		return null;
	}

	/**
	 * 获取第n个元素
	 * 
	 * @param index
	 * @return
	 */
	public T get(int index) {
		if(index < 1){
			//默认从1开始
			return null;
		}
		temp = head;
		int num = 0;
		while (temp != null) {
			if (index == num) {
				return temp.data;
			}
			temp = temp.next;
			num++;
		}
		return null;
	}

}
