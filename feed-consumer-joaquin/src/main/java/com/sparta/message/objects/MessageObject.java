package com.sparta.message.objects;

import java.io.IOException;

public abstract class MessageObject {

	private byte[] bytes;
	
	protected abstract byte[] toByteArray() throws IOException;
	
	public byte[] getBytes() throws IOException {
		if(this.bytes == null) {
			this.bytes = this.toByteArray();
		}
		return bytes;
	}
	
}
