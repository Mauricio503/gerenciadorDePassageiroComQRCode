package br.edu.unoesc.model;

public enum Meses {

	
	Janeiro,Fevereiro,Março,Abril,Maio,Junho,Julho,Agosto,Setembro,Outubro,Novembro,Dezembro;
	
	Integer mesNumero;
	String chave;

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	public String getValue(){
		return this.toString();
	}
	
	public String numeroToString(Integer numero){
		if(numero == 1){
			return Janeiro.toString();
		} else if (numero == 2){
			return Fevereiro.toString();
		}else if (numero == 3){
			return Março.toString();
		}else if (numero == 4){
			return Abril.toString();
		}else if (numero == 5){
			return Maio.toString();
		}else if (numero == 6){
			return Junho.toString();
		}else if (numero == 7){
			return Julho.toString();
		}else if (numero == 8){
			return Agosto.toString();
		}else if (numero == 9){
			return Setembro.toString();
		}else if (numero == 10){
			return Outubro.toString();
		}else if (numero == 11){
			return Novembro.toString();
		}else if (numero == 12){
			return Dezembro.toString();
		}else{
			return null;
		}
	}
}
