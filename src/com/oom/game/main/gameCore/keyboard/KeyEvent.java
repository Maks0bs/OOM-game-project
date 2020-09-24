package com.oom.game.main.gameCore.keyboard;


import com.oom.game.main.gameCore.eventSystem.Event;

public abstract class KeyEvent extends Event {

	private final int keyCode;

	public KeyEvent(int keyCode) {
		this.keyCode = keyCode;
	}

	/**
	 * Gibt den KeyCode zur�ck.
	 * 
	 * @return den KeyCode
	 */
	public int getKeyCode() {
		return keyCode;
	}
}
