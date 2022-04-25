package com.osler.entity;

public class Status {

	private byte authorisation;
	private byte training;
	private byte admin;
	
	public Status() {
		super();
	}

	public Status(byte authorisation, byte training, byte admin) {
		super();
		this.authorisation = authorisation;
		this.training = training;
		this.admin = admin;
	}


	public byte getAuthorisation() {
		return authorisation;
	}


	public void setAuthorisation(byte authorisation) {
		this.authorisation = authorisation;
	}


	public byte getTraining() {
		return training;
	}


	public void setTraining(byte training) {
		this.training = training;
	}


	public byte getAdmin() {
		return admin;
	}


	public void setAdmin(byte admin) {
		this.admin = admin;
	}


	public String toString() {
		String auth;
		String adm;
		String train;

		if (authorisation == 1)
			auth = "AuthorisedAdmin";
		else
			auth = "AuthorisedOperator";
		if (admin == 1)
			adm = "DisabledOperator";
		else
			adm = "DisabledAdmin";
		if (training == 1)
			train = "Trained";
		else
			train = "Untrained";

		return "Status [authorisation=" + auth + ", admin=" + adm + "]" + "[ training=" + training + "]";
	}

}
