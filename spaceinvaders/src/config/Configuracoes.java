package config;

public class Configuracoes {

	private int larguraTela;
	private int alturaTela;
	private int resolucao;

	private static Configuracoes instance;
	
	private Configuracoes() {
		
	}
	
	public static Configuracoes getInstance(){
		if(instance == null)
			instance = new Configuracoes();
		return instance;
	}

	public int getLargura() {
		return larguraTela;
	}

	public void setLargura(int largura) {
		this.larguraTela = largura;
	}

	public int getAltura() {
		return alturaTela;
	}

	public void setAltura(int altura) {
		this.alturaTela = altura;
	}
	
	public int getResolucao() {
		return resolucao;
	}

	public void setResolucao(int resolucao) {
		this.resolucao = resolucao;
	}
	
}
