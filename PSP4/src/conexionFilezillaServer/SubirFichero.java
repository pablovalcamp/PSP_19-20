package conexionFilezillaServer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class SubirFichero {

	public static void main(String[] args) throws IOException {
		String servidor = "localhost";
		String user = "psp";
		String pass = "psp";
		String directorio = "/prueba";
		String fichero = "sunny.zip";

		FTPClient cliente = new FTPClient();
		cliente.connect(servidor);
		cliente.enterLocalPassiveMode();
		boolean login = cliente.login(user, pass);

		if (login) {
			System.out.println("Conexi�n correcta");
			// Si no existe el directorio devuelve false, en ese caso creamos el directorio
			if (!cliente.changeWorkingDirectory(directorio)) {
				cliente.makeDirectory(directorio);
				System.out.println("Carpeta creada " + directorio);
				cliente.changeWorkingDirectory(directorio);
			}

			while (true) {
				System.out.println("1.Subir\n2.Borrar\n3.Renombrar");
				Scanner scanner = new Scanner(System.in);
				String key = scanner.nextLine();
				switch (key) {
				case "1":
					cliente.setFileType(FTP.BINARY_FILE_TYPE);
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(fichero));
					if (cliente.storeFile(fichero, in))
						System.out.println("Fichero subido");
					else
						System.out.println("Fallo al subir");
					break;
				case "2":
					
					
					break;
					
				default:
					break;
				}

			}

		} else
			System.out.println("Incorrecto");

		cliente.logout();
		cliente.disconnect();
	}

}
