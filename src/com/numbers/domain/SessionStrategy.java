package com.numbers.domain;

import java.io.Serializable;

/**
 * Strategy for creating exercises
 * 
 * @author mario
 *
 */
public interface SessionStrategy extends Serializable {
	public Exercise next();
}
