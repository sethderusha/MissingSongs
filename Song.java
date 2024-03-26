public class Song implements Comparable {
	public String name;
	private String ISRC;
	public Song(String name, String ISRC) {
		this.name = name;
		this.ISRC = ISRC;
	}
	
	public String getISRC() {
		return ISRC;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Song)) {
			return false;
		}
		
		boolean eq = false;
		
		Song obj = (Song) o;
		
		if ((this.ISRC.equals(obj.getISRC())) || (this.name.equals(obj.name))) {
			eq = true;
		}

		return eq;
	}

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Song)) {
			return 0;
		}
		Song song = (Song) o;
		return this.name.compareTo(song.name);
	}
}
