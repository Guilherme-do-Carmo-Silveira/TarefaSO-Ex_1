package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}
	
	//Identifica o Sistema Operacional
	public String os() {
		
		String os = System.getProperty("os.name");
		String [] vtso = os.split(" ");
		
		if(os.contains("Windows")) {
			os = vtso[0];
		}else {
			if(os.contains("Linux")) {
				os = vtso [0];
			}
		}
		return os;
	}
	
	//Executa o codigo para descobrir o nome dos adaptadores com ipv4
	public void Ip () {
		
		String sistema = os();
		String adap = "";
		
		if(sistema.contains("Windows")) {
			sistema = "IPCONFIG";
		}else {
			sistema = "IFCONFIG";
		}
		
		try {
				Process p = Runtime.getRuntime().exec(sistema);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
				if(linha.contains("Adaptador")) {
					adap = linha;
				}else {
					if(linha.contains("IPv4")) {
						System.out.println(adap);
						System.out.println(linha);
					}
				}
				linha = buffer.readLine();
			}				
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void Ping() {
		
		String sistema = os();
		String CodPing = "";
		
		if(sistema.contains("Windows")) {
			CodPing = "PING -4 -n 10 www.google.com.br";
		}else {
			CodPing = "PING -4 -c 10 www.google.com.br";
		}
		
		try {
			Process p = Runtime.getRuntime().exec(CodPing);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				if(sistema.contains("Windows")) {
					if(linha.contains("ms, ")) {
						String [] vt = linha.split("=");
						System.out.println("Media = " + vt[3]);
					}	
				}
				if(sistema.contains("Linux")) {
					if(linha.contains("rtt")) {
						String [] vt = linha.split("/");
						System.out.println(vt[4]);
					}
				}
				linha = buffer.readLine();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}