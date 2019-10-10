
public class Map {
	
	public void showMap() {
		for(int i = 0; i < 10; i++) {
			System.out.print(" ___ ");
		}
		System.out.println();
		int counter = 0;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i == 0 && j == 0) {
					System.out.print("|_S_|");
				} else if(counter % 6 == 0) {
					System.out.print("|_H_|");
				} else {
					System.out.print("|___|");
				}
				counter++;
			}
			System.out.println();
		}
	}
	
}
