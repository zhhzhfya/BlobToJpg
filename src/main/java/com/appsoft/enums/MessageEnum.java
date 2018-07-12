package com.appsoft.enums;
public enum MessageEnum {
	ALL_COUNT("1", ""),
	QUERY_TIME("2", ""),
	WRITE_TIME("3", ""),
	CONSOLE("4", ""),
	OK("5", ""),
	;
	private final String id;
	private final String name;
	private MessageEnum(String id, String name){
		this.id = id;
		this.name = name;
	}

	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public static MessageEnum getById(String id){
		for (MessageEnum temp: MessageEnum.values()) {
			if (temp.getId().equals(id)) {
				return temp;
			}
		}
		throw new RuntimeException("[" + id + "]枚举ID错误");
	}
}
