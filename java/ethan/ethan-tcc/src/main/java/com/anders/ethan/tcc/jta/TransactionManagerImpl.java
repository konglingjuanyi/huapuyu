package com.anders.ethan.tcc.jta;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.InvalidTransactionException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

public class TransactionManagerImpl implements TransactionManager {

	@Override
	public void begin() throws NotSupportedException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() throws RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SecurityException,
			IllegalStateException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStatus() throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Transaction getTransaction() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resume(Transaction tobj) throws InvalidTransactionException,
			IllegalStateException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() throws IllegalStateException, SecurityException,
			SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRollbackOnly() throws IllegalStateException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTransactionTimeout(int seconds) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transaction suspend() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
