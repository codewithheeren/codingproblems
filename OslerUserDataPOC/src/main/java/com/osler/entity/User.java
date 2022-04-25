package com.osler.entity;

public class User {

	private String id;
	private String deviceId;
	private Status status;

	public User() {
		super();
	}

	public User(String id, String deviceId, Status status) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + (this.id != null ? this.id.hashCode() : 0);

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;

		if (!id.equals(other.getId()))
			return false;

		return true;
	}

}
