package andy.rmi.service;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ExponentServiceImpl extends UnicastRemoteObject implements IExponentService {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExponentServiceImpl() throws RemoteException {
		super();
	}

	public BigInteger doSquare(int n) throws RemoteException {
		String nStr=String.valueOf(n);
		BigInteger bint=new BigInteger(nStr);
		bint.multiply(bint);
		return bint;
	}

	public BigInteger doPower(int base, int power) throws RemoteException {
		String nStr=String.valueOf(base);
		BigInteger bint=new BigInteger(nStr);
		bint.pow(power);
		return bint;
	}

	public static void main(String s[]) {
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}

		try {
		
				ExponentServiceImpl srvc= new ExponentServiceImpl();
				Naming.bind("ExponentService", srvc);
				System.out.println("Exponent Service registered...");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
	
}
