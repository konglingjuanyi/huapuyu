package com.anders.ethan.sharding.jta.common;

import com.anders.ethan.sharding.jta.TransactionImpl;

public class AssociatedContext {
	// private Thread thread;
	private TransactionImpl transaction;

	// private boolean expired;

	// public Thread getThread() {
	// return thread;
	// }
	//
	// public void setThread(Thread thread) {
	// this.thread = thread;
	// }

	public TransactionImpl getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionImpl transaction) {
		this.transaction = transaction;
	}

	// public boolean isExpired() {
	// return expired;
	// }
	//
	// public void setExpired(boolean expired) {
	// this.expired = expired;
	// }

}
