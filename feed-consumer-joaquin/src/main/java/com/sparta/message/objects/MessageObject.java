package com.sparta.message.objects;

import java.io.IOException;

public abstract class MessageObject {

	public abstract byte[] getBytes() throws IOException;
	
}
