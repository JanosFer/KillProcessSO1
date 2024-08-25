package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import java.io.BufferedReader;

public class KillController {
	public KillController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void listaProcessos() {
		String os = os();
		String processo = "";
		
		if(os.contains("Windows")) {
			processo = "TASKLIST /FO TABLE";
		}else if(os.contains("Linux")) {
			processo = "ps -ef";
		}
		try {
			Process p = Runtime.getRuntime().exec(processo);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while(linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void mataPid() {
		String os = os();
		String comando = "";
		int pid = 0;
		
		if(os.contains("Windows")) {
			comando = "TASKKILL /PID";
		}else if(os.contains("Linux")) {
			comando = "kill -9";
		}
		
		StringBuffer buffer = new StringBuffer();
		while(true) {
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog("Informe o Número PID do processo a ser encerrado: "));
			}catch (NumberFormatException e) {
				if(e.getMessage().contains("null")) {
					break;
				}else {
					JOptionPane.showMessageDialog(null, "Por favor informe um NÚMERO!");
				}
				
			}
			if(pid > 0) {
				break;
			}
		}
		
		try {
			buffer.append(comando);
			buffer.append(" ");
			buffer.append(pid);
			
			try {
				Runtime.getRuntime().exec(buffer.toString());
			}catch (IOException e) {
				String msgErro = e.getMessage();
				if(msgErro.contains("740")) {
					StringBuffer buffer2 = new StringBuffer();
					buffer2.append("cmd /c");
					buffer2.append(" ");
					buffer2.append(buffer.toString());
					try {
						Runtime.getRuntime().exec(buffer2.toString());
					}catch (IOException e1) {
						System.err.println(msgErro);
					}
				}else {
					e.printStackTrace();
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mataNome() {
		String os = os();
		String comando = "";
		String nome = "";
		
		if(os.contains("Windows")) {
			comando = "TASKKILL /IM";
		}else if(os.contains("Linux")) {
			comando = "pkill -f";
		}
		
		StringBuffer buffer = new StringBuffer();
		nome = JOptionPane.showInputDialog("Informe o NOME do processo a ser encerrado: ");
		
		try {
			buffer.append(comando);
			buffer.append(" ");
			buffer.append(nome);
			
			try {
				Runtime.getRuntime().exec(buffer.toString());
			}catch (IOException e) {
				String msgErro = e.getMessage();
				if(msgErro.contains("740")) {
					StringBuffer buffer2 = new StringBuffer();
					buffer2.append("cmd /c");
					buffer2.append(" ");
					buffer2.append(buffer.toString());
					try {
						Runtime.getRuntime().exec(buffer2.toString());
					}catch (IOException e1) {
						System.err.println(msgErro);
					}
				}else {
					e.printStackTrace();
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}