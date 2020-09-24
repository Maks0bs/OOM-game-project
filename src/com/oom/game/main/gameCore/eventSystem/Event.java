package com.oom.game.main.gameCore.eventSystem;

import java.util.UUID;

public class Event implements IEvent {

	private final UUID ID;

	public Event() {
		this.ID = UUID.randomUUID();
	}

	@Override
	public final UUID getID() {
		return ID;
	}
}
