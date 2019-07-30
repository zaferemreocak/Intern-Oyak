import java.util.ArrayList;
import java.util.List;

public class Kredi {

	private int 	sira;
	private String 	odeme_tarihi;
	private int 	taksit_tutari;

	public String getOdeme_tarihi() {
		return odeme_tarihi;
	}

	public void setOdeme_tarihi(String odeme_tarihi) {
		this.odeme_tarihi = odeme_tarihi;
	}

	public int getTaksit_tutari() {
		return taksit_tutari;
	}

	public void setTaksit_tutari(int taksit_tutari) {
		this.taksit_tutari = taksit_tutari;
	}

	public int getSira() {
		return sira;
	} 

	public void setSira(int sira) {
		this.sira = sira;
	}
	
	public static List<Integer> getMaxInstallment(ArrayList<Kredi> credits){
		int maxAmount=0;
		List<Integer> siralist = new ArrayList<>();
		for(int a=0;a<credits.size();a++){
			if(credits.get(a).getTaksit_tutari() > maxAmount){
				maxAmount = credits.get(a).getTaksit_tutari();

			}
		}
		
		for(int b=0;b<credits.size();b++){
			if(credits.get(b).getTaksit_tutari() == maxAmount){
				siralist.add(credits.get(b).getSira());
			}
		}
		
		return siralist;
	}
}
