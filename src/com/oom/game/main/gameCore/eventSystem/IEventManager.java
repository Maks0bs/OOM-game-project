package com.oom.game.main.gameCore.eventSystem;

public interface IEventManager {

	/**
	 * Registriert das �bergebene Objekt f�r alle Events.
	 * @param eventListener das zu registrierende Objekt
	 */
	public void register(IEventListener eventListener);

	/**
	 * Meldet das �bergebene Objekt f�r alle Events ab.
	 * @param eventListener das abzumeldende Objekt
	 */
	public void unregister(IEventListener eventListener);

	/**
	 * Feuert das �bergebene Event.
	 * @param event das zu feuernde Event
	 */
	public void fire(IEvent event);
}
