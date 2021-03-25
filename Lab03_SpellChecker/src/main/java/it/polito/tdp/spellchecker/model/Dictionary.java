package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import it.polito.tdp.spellchecker.RichWord;

public class Dictionary {

	HashSet<String> dizionario;
	
	public Dictionary() {
		
		dizionario = new HashSet<String>();
	}
	
	public void loadDictionary(String language) {
		
		if(language.compareTo("Italian")==0) {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null)
					dizionario.add(word);
				br.close();
				fr.close();
			}catch(IOException io) {
				System.out.println("Errore lettura file!");
			}
		}
		else if(language.compareTo("English")==0) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null)
					dizionario.add(word);
				br.close();
				fr.close();
			}catch(IOException io) {
				System.out.println("Errore lettura file!");
			}
		} else System.out.println("Lingua non riconosciuta!");
	}
	
	public List<RichWord> spellCheckText(List<String> input){
		
		List<RichWord> lista = new ArrayList<RichWord>();
		for(String p: input)
			if(dizionario.contains(p))
				lista.add(new RichWord(p, true));
			else lista.add(new RichWord(p, false));
		
		return lista;			
	}
	
	public int contaErrori(List<RichWord> lista) {
		
		int contatore = 0;
		for(RichWord x: lista)
			if(!x.corretta)
				contatore++;
		return contatore;
	}
	
	public String mostraErrori(List<RichWord> lista) {
		
		String result = "";
    	for(RichWord x: lista)
    		if(!x.corretta)
    			result = result + x.parola + "\n";
    	return result;
	}
	
	public void clear() {
		
		dizionario.clear();
	}
}
