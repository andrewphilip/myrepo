package andy.rmi.service;

import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IExponentService extends Remote {

		public BigInteger doSquare(int n) throws  RemoteException;
		public BigInteger doPower(int base, int power) throws RemoteException;
		
}
