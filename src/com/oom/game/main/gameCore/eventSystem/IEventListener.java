package com.oom.game.main.gameCore.eventSystem;

public interface IEventListener {

	/**
	 * Wird von einem {@linkplain IEventManager} bei entsprechender Registierung
	 * aufgerufen.
	 * 
	 * @param event das eingehende Event
	 */
	public void onEvent(IEvent event);
}
